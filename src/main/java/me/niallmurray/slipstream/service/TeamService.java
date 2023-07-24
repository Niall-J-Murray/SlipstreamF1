package me.niallmurray.slipstream.service;

import me.niallmurray.slipstream.domain.Driver;
import me.niallmurray.slipstream.domain.League;
import me.niallmurray.slipstream.domain.Team;
import me.niallmurray.slipstream.domain.User;
import me.niallmurray.slipstream.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.random.RandomGenerator;

@Service
public class TeamService {
  @Autowired
  private TeamRepository teamRepository;
  @Autowired
  private UserService userService;
  @Autowired
  private LeagueService leagueService;
  @Autowired
  private DriverService driverService;

  public void createTeam(User user) {
    Team team = new Team();
    team.setUser(user);

    if (isUniqueTeamName(user.getTeam().getTeamName())) {
      team.setTeamName(user.getTeam().getTeamName());
      user.setTeam(team);
      user.setEmail(user.getEmail());
    }
    team.setFirstPickNumber(randomPickNumber());
    team.setSecondPickNumber(21 - team.getFirstPickNumber()); //So players get 1&20, 2&19 etc. up to 10&11.
    team.setRanking(team.getFirstPickNumber());
    team.setStartingPoints(0.0);
    team.setIsTestTeam(false);
    team.setLeague(leagueService.findAvailableLeague());
    addOneTeamToLeague(team);
  }

  public void createTestTeam(User user) {
    String leagueNumber = user.getTeam().getLeague().getLeagueName().substring(8);
    int leagueSize = getAllTeamsByLeague(user.getTeam().getLeague()).size();
    String teamName = "Team " + leagueNumber + "-" + (leagueSize + 1);
    User testUser = userService.createTestUser(teamName);

    Team team = new Team();
    team.setUser(testUser);
    team.setTeamName(teamName);
    team.setFirstPickNumber(randomPickNumber());
    team.setSecondPickNumber(21 - team.getFirstPickNumber());
    team.setRanking(team.getFirstPickNumber());
    team.setStartingPoints(0.0);
    team.setIsTestTeam(true);
    team.setLeague(leagueService.findAvailableLeague());
    testUser.setTeam(team);

    addOneTeamToLeague(team);
  }

  public void addOneTeamToLeague(Team team) {
    League league = leagueService.findAvailableLeague();
    List<Team> teams = league.getTeams();
    teams.add(team);
    league.setTeams(teams);
    leagueService.save(league);
  }

  private int randomPickNumber() {
    RandomGenerator random = RandomGenerator.getDefault();
    int pickNumber = random.nextInt(1, 11);

    for (Team team : leagueService.findAvailableLeague().getTeams()) {
      if (team.getFirstPickNumber() == pickNumber) {
        pickNumber = randomPickNumber();
      }
    }
    return pickNumber;
  }

  public boolean isUniqueTeamName(String teamName) {
    List<Team> allTeams = teamRepository.findAll();
    for (Team team : allTeams) {
      if (Objects.equals(team.getTeamName(), teamName))
        return false;
    }
    return true;
  }

  public void addDriverToTeam(Long userId, Long driverId) {
    User user = userService.findById(userId);
    Driver driver = driverService.findById(driverId);
    Team team = findById(user.getTeam().getTeamId());
    List<Driver> teamDrivers = user.getTeam().getDrivers();

    if (teamDrivers.size() < 2) {
      teamDrivers.add(driver);
      driver.getTeams().add(team);
      driver.setTeams(driver.getTeams());
    }
    Double startingPoints = team.getDrivers().stream()
            .mapToDouble(Driver::getPoints).sum();
    team.setDrivers(teamDrivers);
    team.setStartingPoints(startingPoints);
    team.setUser(user);
    user.setTeam(user.getTeam());

    userService.save(user);
    teamRepository.save(team);
    driverService.save(driver);
  }

  public void addDriverToTestTeam(Long userId, Long driverId) {
    User user = userService.findById(userId);
    User testUser = userService.findByUserName(getNextToPickName(user.getTeam().getLeague()));
    addDriverToTeam(testUser.getUserId(), driverId);
  }

  public Boolean isTestPick(League league) {
    return getNextToPickName(league) != null && (getNextToPickName(league).startsWith("Test User"));
  }

  public Boolean hasTestTeams(League league) {
    List<Team> teamsInLeague = getAllTeamsByLeague(league);
    for (Team team : teamsInLeague) {
      if (Boolean.TRUE.equals(team.getIsTestTeam()))
        return true;
    }
    return false;
  }

  public boolean timeToPick(League league, Long teamId) {
    int firstPickNumber = findById(teamId).getFirstPickNumber();
    int secondPickNumber = findById(teamId).getSecondPickNumber();

    return firstPickNumber == leagueService.getCurrentPickNumber(league)
            || secondPickNumber == leagueService.getCurrentPickNumber(league);
  }

  public User getNextToPick(League league) {
    User nextUserPick = null;
    List<Team> teamsInLeague = getAllTeamsByLeague(league);
    for (Team team : teamsInLeague) {
      if (timeToPick(league, team.getTeamId())) {
        nextUserPick = team.getUser();
      }
    }
    return nextUserPick;
  }

  public String getNextToPickName(League league) {
    String nextUserPick = null;
    List<Team> teamsInLeague = getAllTeamsByLeague(league);
    for (Team team : teamsInLeague) {
      if (timeToPick(league, team.getTeamId())) {
        nextUserPick = team.getUser().getUsername();
      }
    }
    return nextUserPick;
  }

  public List<Team> updateLeagueTeamsRankings(League league) {
    List<Team> teams = league.getTeams();
    for (Team team : teams) {
      // Update existing teams with drivers who have been replaced.
      // So far only 1 (de Vries) has been replaced (by Ricciardo).
      if (team.getDrivers().contains(driverService.findByCarNumber(21))) {
        team.getDrivers().remove(driverService.findByCarNumber(21));
        team.getDrivers().add(driverService.findByCarNumber(3));
      }
      Double totalDriverPoints = team.getDrivers().stream()
              .mapToDouble(Driver::getPoints).sum();
      team.setTeamPoints(totalDriverPoints - team.getStartingPoints());
    }
    // Sort by pick order until all picks made and league is active
    if (Boolean.FALSE.equals(league.getIsActive())) {
      teams.sort(Comparator.comparing(Team::getFirstPickNumber));
      return teams;
    }
    teams.sort(Comparator.comparing(Team::getFirstPickNumber).reversed());
    teams.sort(Comparator.comparing(Team::getTeamPoints).reversed());
    for (Team team : teams) {
      team.setRanking(teams.indexOf(team) + 1);
    }
    return teamRepository.saveAll(teams);
  }

  public List<Team> getAllTeams() {
    return teamRepository.findAll();
  }

  public List<Team> getAllTeamsByLeague(League league) {
    return teamRepository.findAll().stream()
            .filter(team -> team.getLeague().equals(league))
            .toList();
  }

  public List<Team> getAllTeamsByNextPick() {
    List<Team> allTeams = teamRepository.findAll();
    allTeams.sort(Comparator.comparing(Team::getFirstPickNumber));
    return allTeams;
  }

  public void deleteTeam(Team team) {
    League league = team.getLeague();
    List<Driver> drivers = team.getDrivers();
    User user = team.getUser();
    for (Driver driver : drivers) {
      driver.getTeams().remove(team);
      driverService.save(driver);
    }
    league.getTeams().remove(team);
    user.setTeam(null);
    teamRepository.delete(team);
    userService.save(user);
    leagueService.save(league);
  }

  public void deleteAllTestTeams(League league) {
    List<Team> teamsInLeague = getAllTeamsByLeague(league);
    for (Team team : teamsInLeague) {
      if (Boolean.TRUE.equals(team.getIsTestTeam())) {
        deleteTeam(team);
        userService.delete(team.getUser());
      }
    }
    if (Boolean.TRUE.equals(league.getIsTestLeague())) {
      league.setIsTestLeague(false);
    }
  }

  public void deleteExpiredTestTeams() {
    List<League> allLeagues = leagueService.findAll();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");
    for (League league : allLeagues) {
      LocalDateTime creationTime = LocalDateTime.parse(league.getCreationTimestamp(), formatter);
      if (LocalDateTime.now().minusHours(24).isAfter(creationTime)) {
        deleteAllTestTeams(league);
      }
    }
  }

  public Team findById(Long teamId) {
    return teamRepository.findById(teamId).orElse(null);
  }
}
