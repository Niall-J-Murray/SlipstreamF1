package me.niallmurray.slipstream;

import me.niallmurray.slipstream.domain.Driver;
import me.niallmurray.slipstream.domain.League;
import me.niallmurray.slipstream.domain.Team;
import me.niallmurray.slipstream.domain.User;
import me.niallmurray.slipstream.repositories.TeamRepository;
import me.niallmurray.slipstream.service.DriverService;
import me.niallmurray.slipstream.service.LeagueService;
import me.niallmurray.slipstream.service.TeamService;
import me.niallmurray.slipstream.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {TeamService.class})
@ExtendWith(SpringExtension.class)
class TeamServiceTest {

  @MockBean
  private DriverService driverService;

  @MockBean
  private LeagueService leagueService;

  @MockBean
  private TeamRepository teamRepository;

  @Autowired
  private TeamService teamService;

  @MockBean
  private UserService userService;

  @Test
  void testCreateTeam() {
    League league = mock(League.class);
    when(league.getTeams()).thenReturn(new ArrayList<>());
    doNothing().when(league).setActiveTimestamp(Mockito.any());
    doNothing().when(league).setCreationTimestamp(Mockito.any());
    doNothing().when(league).setIsActive(Mockito.<Boolean>any());
    doNothing().when(league).setLeagueId(Mockito.<Long>any());
    doNothing().when(league).setLeagueName(Mockito.any());
    doNothing().when(league).setTeams(Mockito.any());
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());
    doNothing().when(leagueService).save(Mockito.any());
    when(leagueService.findAvailableLeague()).thenReturn(league);

    League league2 = new League();
    league2.setActiveTimestamp("Active Timestamp");
    league2.setCreationTimestamp("Creation Timestamp");
    league2.setIsActive(true);
    league2.setLeagueId(1L);
    league2.setLeagueName("League Name");
    league2.setTeams(new ArrayList<>());

    League league3 = new League();
    league3.setActiveTimestamp("Active Timestamp");
    league3.setCreationTimestamp("Creation Timestamp");
    league3.setIsActive(true);
    league3.setLeagueId(1L);
    league3.setLeagueName("League Name");
    league3.setTeams(new ArrayList<>());

    User user = new User();
    user.setAuthorities(new HashSet<>());
    user.setEmail("jane.doe@example.org");
    user.setLastLogout("Last Logout");
    user.setPassword("password");
    user.setTeam(new Team());
    user.setUserId(1L);
    user.setUsername("janedoe");

    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setLeague(league3);
    team.setRanking(6);
    team.setSecondPickNumber(10);
    team.setStartingPoints(10.0d);
    team.setTeamId(1L);
    team.setTeamName("Team Name");
    team.setTeamPoints(10.0d);
    team.setUser(user);

    User user2 = new User();
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setLastLogout("Last Logout");
    user2.setPassword("password");
    user2.setTeam(team);
    user2.setUserId(1L);
    user2.setUsername("janedoe");

    Team team2 = new Team();
    team2.setDrivers(new ArrayList<>());
    team2.setFirstPickNumber(10);
    team2.setLeague(league2);
    team2.setRanking(6);
    team2.setSecondPickNumber(10);
    team2.setStartingPoints(10.0d);
    team2.setTeamId(1L);
    team2.setTeamName("Team Name");
    team2.setTeamPoints(10.0d);
    team2.setUser(user2);

    ArrayList<Team> teamList = new ArrayList<>();
    teamList.add(team2);
    when(teamRepository.findAll()).thenReturn(teamList);

    League league4 = new League();
    league4.setActiveTimestamp("Active Timestamp");
    league4.setCreationTimestamp("Creation Timestamp");
    league4.setIsActive(true);
    league4.setLeagueId(1L);
    league4.setLeagueName("League Name");
    league4.setTeams(new ArrayList<>());

    Team team3 = new Team();
    team3.setDrivers(new ArrayList<>());
    team3.setFirstPickNumber(10);
    team3.setLeague(new League());
    team3.setRanking(1);
    team3.setSecondPickNumber(10);
    team3.setStartingPoints(10.0d);
    team3.setTeamId(1L);
    team3.setTeamName("Team Name");
    team3.setTeamPoints(10.0d);
    team3.setUser(new User());

    User user3 = new User();
    user3.setAuthorities(new HashSet<>());
    user3.setEmail("jane.doe@example.org");
    user3.setLastLogout("Last Logout");
    user3.setPassword("password");
    user3.setTeam(team3);
    user3.setUserId(1L);
    user3.setUsername("janedoe");

    Team team4 = new Team();
    team4.setDrivers(new ArrayList<>());
    team4.setFirstPickNumber(10);
    team4.setLeague(league4);
    team4.setRanking(1);
    team4.setSecondPickNumber(10);
    team4.setStartingPoints(10.0d);
    team4.setTeamId(1L);
    team4.setTeamName("Team Name");
    team4.setTeamPoints(10.0d);
    team4.setUser(user3);

    User user4 = new User();
    user4.setAuthorities(new HashSet<>());
    user4.setEmail("jane.doe@example.org");
    user4.setLastLogout("Last Logout");
    user4.setPassword("password");
    user4.setTeam(team4);
    user4.setUserId(1L);
    user4.setUsername("janedoe");
    teamService.createTeam(user4);
    verify(leagueService, atLeast(1)).findAvailableLeague();
    verify(leagueService).save(Mockito.any());
    verify(league, atLeast(1)).getTeams();
    verify(league).setActiveTimestamp(Mockito.any());
    verify(league).setCreationTimestamp(Mockito.any());
    verify(league).setIsActive(Mockito.<Boolean>any());
    verify(league).setLeagueId(Mockito.<Long>any());
    verify(league).setLeagueName(Mockito.any());
    verify(league, atLeast(1)).setTeams(Mockito.any());
    verify(teamRepository).findAll();
  }

  @Test
  void testCreateTeam2() {
    League league = mock(League.class);
    when(league.getTeams()).thenReturn(new ArrayList<>());
    doNothing().when(league).setActiveTimestamp(Mockito.any());
    doNothing().when(league).setCreationTimestamp(Mockito.any());
    doNothing().when(league).setIsActive(Mockito.<Boolean>any());
    doNothing().when(league).setLeagueId(Mockito.<Long>any());
    doNothing().when(league).setLeagueName(Mockito.any());
    doNothing().when(league).setTeams(Mockito.any());
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());
    doNothing().when(leagueService).save(Mockito.any());
    when(leagueService.findAvailableLeague()).thenReturn(league);

    League league2 = new League();
    league2.setActiveTimestamp("Active Timestamp");
    league2.setCreationTimestamp("Creation Timestamp");
    league2.setIsActive(true);
    league2.setLeagueId(1L);
    league2.setLeagueName("League Name");
    league2.setTeams(new ArrayList<>());

    League league3 = new League();
    league3.setActiveTimestamp("Active Timestamp");
    league3.setCreationTimestamp("Creation Timestamp");
    league3.setIsActive(true);
    league3.setLeagueId(1L);
    league3.setLeagueName("League Name");
    league3.setTeams(new ArrayList<>());

    User user = new User();
    user.setAuthorities(new HashSet<>());
    user.setEmail("jane.doe@example.org");
    user.setLastLogout("Last Logout");
    user.setPassword("password");
    user.setTeam(new Team());
    user.setUserId(1L);
    user.setUsername("janedoe");

    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setLeague(league3);
    team.setRanking(6);
    team.setSecondPickNumber(10);
    team.setStartingPoints(10.0d);
    team.setTeamId(1L);
    team.setTeamName("Team Name");
    team.setTeamPoints(10.0d);
    team.setUser(user);

    User user2 = new User();
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setLastLogout("Last Logout");
    user2.setPassword("password");
    user2.setTeam(team);
    user2.setUserId(1L);
    user2.setUsername("janedoe");

    Team team2 = new Team();
    team2.setDrivers(new ArrayList<>());
    team2.setFirstPickNumber(10);
    team2.setLeague(league2);
    team2.setRanking(6);
    team2.setSecondPickNumber(10);
    team2.setStartingPoints(10.0d);
    team2.setTeamId(1L);
    team2.setTeamName("Team Name");
    team2.setTeamPoints(10.0d);
    team2.setUser(user2);

    League league4 = new League();
    league4.setActiveTimestamp("me.niallmurray.slipstream9.domain.League");
    league4.setCreationTimestamp("me.niallmurray.slipstream9.domain.League");
    league4.setIsActive(false);
    league4.setLeagueId(2L);
    league4.setLeagueName("me.niallmurray.slipstream9.domain.League");
    league4.setTeams(new ArrayList<>());

    League league5 = new League();
    league5.setActiveTimestamp("me.niallmurray.slipstream9.domain.League");
    league5.setCreationTimestamp("me.niallmurray.slipstream9.domain.League");
    league5.setIsActive(false);
    league5.setLeagueId(2L);
    league5.setLeagueName("me.niallmurray.slipstream9.domain.League");
    league5.setTeams(new ArrayList<>());

    User user3 = new User();
    user3.setAuthorities(new HashSet<>());
    user3.setEmail("john.smith@example.org");
    user3.setLastLogout("42");
    user3.setPassword("Password");
    user3.setTeam(new Team());
    user3.setUserId(2L);
    user3.setUsername("Username");

    Team team3 = new Team();
    team3.setDrivers(new ArrayList<>());
    team3.setFirstPickNumber(6);
    team3.setLeague(league5);
    team3.setRanking(1);
    team3.setSecondPickNumber(6);
    team3.setStartingPoints(0.0d);
    team3.setTeamId(2L);
    team3.setTeamName("42");
    team3.setTeamPoints(0.0d);
    team3.setUser(user3);

    User user4 = new User();
    user4.setAuthorities(new HashSet<>());
    user4.setEmail("john.smith@example.org");
    user4.setLastLogout("42");
    user4.setPassword("Password");
    user4.setTeam(team3);
    user4.setUserId(2L);
    user4.setUsername("Username");

    Team team4 = new Team();
    team4.setDrivers(new ArrayList<>());
    team4.setFirstPickNumber(6);
    team4.setLeague(league4);
    team4.setRanking(1);
    team4.setSecondPickNumber(6);
    team4.setStartingPoints(0.0d);
    team4.setTeamId(2L);
    team4.setTeamName("42");
    team4.setTeamPoints(0.0d);
    team4.setUser(user4);

    ArrayList<Team> teamList = new ArrayList<>();
    teamList.add(team4);
    teamList.add(team2);
    when(teamRepository.findAll()).thenReturn(teamList);

    League league6 = new League();
    league6.setActiveTimestamp("Active Timestamp");
    league6.setCreationTimestamp("Creation Timestamp");
    league6.setIsActive(true);
    league6.setLeagueId(1L);
    league6.setLeagueName("League Name");
    league6.setTeams(new ArrayList<>());

    Team team5 = new Team();
    team5.setDrivers(new ArrayList<>());
    team5.setFirstPickNumber(10);
    team5.setLeague(new League());
    team5.setRanking(1);
    team5.setSecondPickNumber(10);
    team5.setStartingPoints(10.0d);
    team5.setTeamId(1L);
    team5.setTeamName("Team Name");
    team5.setTeamPoints(10.0d);
    team5.setUser(new User());

    User user5 = new User();
    user5.setAuthorities(new HashSet<>());
    user5.setEmail("jane.doe@example.org");
    user5.setLastLogout("Last Logout");
    user5.setPassword("password");
    user5.setTeam(team5);
    user5.setUserId(1L);
    user5.setUsername("janedoe");

    Team team6 = new Team();
    team6.setDrivers(new ArrayList<>());
    team6.setFirstPickNumber(10);
    team6.setLeague(league6);
    team6.setRanking(1);
    team6.setSecondPickNumber(10);
    team6.setStartingPoints(10.0d);
    team6.setTeamId(1L);
    team6.setTeamName("Team Name");
    team6.setTeamPoints(10.0d);
    team6.setUser(user5);

    User user6 = new User();
    user6.setAuthorities(new HashSet<>());
    user6.setEmail("jane.doe@example.org");
    user6.setLastLogout("Last Logout");
    user6.setPassword("password");
    user6.setTeam(team6);
    user6.setUserId(1L);
    user6.setUsername("janedoe");
    teamService.createTeam(user6);
    verify(leagueService, atLeast(1)).findAvailableLeague();
    verify(leagueService).save(Mockito.any());
    verify(league, atLeast(1)).getTeams();
    verify(league).setActiveTimestamp(Mockito.any());
    verify(league).setCreationTimestamp(Mockito.any());
    verify(league).setIsActive(Mockito.<Boolean>any());
    verify(league).setLeagueId(Mockito.<Long>any());
    verify(league).setLeagueName(Mockito.any());
    verify(league, atLeast(1)).setTeams(Mockito.any());
    verify(teamRepository).findAll();
  }

  @Test
  void testAddOneTeamToLeague() {
    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());
    doNothing().when(leagueService).save(Mockito.any());
    when(leagueService.findAvailableLeague()).thenReturn(league);

    League league2 = new League();
    league2.setActiveTimestamp("Active Timestamp");
    league2.setCreationTimestamp("Creation Timestamp");
    league2.setIsActive(true);
    league2.setLeagueId(1L);
    league2.setLeagueName("League Name");
    league2.setTeams(new ArrayList<>());

    League league3 = new League();
    league3.setActiveTimestamp("Active Timestamp");
    league3.setCreationTimestamp("Creation Timestamp");
    league3.setIsActive(true);
    league3.setLeagueId(1L);
    league3.setLeagueName("League Name");
    league3.setTeams(new ArrayList<>());

    User user = new User();
    user.setAuthorities(new HashSet<>());
    user.setEmail("jane.doe@example.org");
    user.setLastLogout("Last Logout");
    user.setPassword("password");
    user.setTeam(new Team());
    user.setUserId(1L);
    user.setUsername("janedoe");

    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setLeague(league3);
    team.setRanking(1);
    team.setSecondPickNumber(10);
    team.setStartingPoints(10.0d);
    team.setTeamId(1L);
    team.setTeamName("Team Name");
    team.setTeamPoints(10.0d);
    team.setUser(user);

    User user2 = new User();
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setLastLogout("Last Logout");
    user2.setPassword("password");
    user2.setTeam(team);
    user2.setUserId(1L);
    user2.setUsername("janedoe");

    Team team2 = new Team();
    team2.setDrivers(new ArrayList<>());
    team2.setFirstPickNumber(10);
    team2.setLeague(league2);
    team2.setRanking(1);
    team2.setSecondPickNumber(10);
    team2.setStartingPoints(10.0d);
    team2.setTeamId(1L);
    team2.setTeamName("Team Name");
    team2.setTeamPoints(10.0d);
    team2.setUser(user2);
    teamService.addOneTeamToLeague(team2);
    verify(leagueService).findAvailableLeague();
    verify(leagueService).save(Mockito.any());
  }

  @Test
  void testAddOneTeamToLeague2() {
    League league = mock(League.class);
    when(league.getTeams()).thenReturn(new ArrayList<>());
    doNothing().when(league).setActiveTimestamp(Mockito.any());
    doNothing().when(league).setCreationTimestamp(Mockito.any());
    doNothing().when(league).setIsActive(Mockito.<Boolean>any());
    doNothing().when(league).setLeagueId(Mockito.<Long>any());
    doNothing().when(league).setLeagueName(Mockito.any());
    doNothing().when(league).setTeams(Mockito.any());
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());
    doNothing().when(leagueService).save(Mockito.any());
    when(leagueService.findAvailableLeague()).thenReturn(league);

    League league2 = new League();
    league2.setActiveTimestamp("Active Timestamp");
    league2.setCreationTimestamp("Creation Timestamp");
    league2.setIsActive(true);
    league2.setLeagueId(1L);
    league2.setLeagueName("League Name");
    league2.setTeams(new ArrayList<>());

    League league3 = new League();
    league3.setActiveTimestamp("Active Timestamp");
    league3.setCreationTimestamp("Creation Timestamp");
    league3.setIsActive(true);
    league3.setLeagueId(1L);
    league3.setLeagueName("League Name");
    league3.setTeams(new ArrayList<>());

    User user = new User();
    user.setAuthorities(new HashSet<>());
    user.setEmail("jane.doe@example.org");
    user.setLastLogout("Last Logout");
    user.setPassword("password");
    user.setTeam(new Team());
    user.setUserId(1L);
    user.setUsername("janedoe");

    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setLeague(league3);
    team.setRanking(1);
    team.setSecondPickNumber(10);
    team.setStartingPoints(10.0d);
    team.setTeamId(1L);
    team.setTeamName("Team Name");
    team.setTeamPoints(10.0d);
    team.setUser(user);

    User user2 = new User();
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setLastLogout("Last Logout");
    user2.setPassword("password");
    user2.setTeam(team);
    user2.setUserId(1L);
    user2.setUsername("janedoe");

    Team team2 = new Team();
    team2.setDrivers(new ArrayList<>());
    team2.setFirstPickNumber(10);
    team2.setLeague(league2);
    team2.setRanking(1);
    team2.setSecondPickNumber(10);
    team2.setStartingPoints(10.0d);
    team2.setTeamId(1L);
    team2.setTeamName("Team Name");
    team2.setTeamPoints(10.0d);
    team2.setUser(user2);
    teamService.addOneTeamToLeague(team2);
    verify(leagueService).findAvailableLeague();
    verify(leagueService).save(Mockito.any());
    verify(league).getTeams();
    verify(league).setActiveTimestamp(Mockito.any());
    verify(league).setCreationTimestamp(Mockito.any());
    verify(league).setIsActive(Mockito.<Boolean>any());
    verify(league).setLeagueId(Mockito.<Long>any());
    verify(league).setLeagueName(Mockito.any());
    verify(league, atLeast(1)).setTeams(Mockito.any());
  }

  @Test
  void testAddDriverToTeam() {
    Driver driver = new Driver();
    driver.setCarNumber(10);
    driver.setConstructor("Constructor");
    driver.setDateOfBirth("2020-03-01");
    driver.setDriverId(1L);
    driver.setFirstName("Jane");
    driver.setNationality("Nationality");
    driver.setPoints(10.0d);
    driver.setShortName("Short Name");
    driver.setStanding(1);
    driver.setSurname("Doe");
    driver.setTeams(new ArrayList<>());
    driver.setWikiPage("Wiki Page");
    doNothing().when(driverService).save(Mockito.any());
    when(driverService.findById(Mockito.<Long>any())).thenReturn(driver);

    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());

    League league2 = new League();
    league2.setActiveTimestamp("Active Timestamp");
    league2.setCreationTimestamp("Creation Timestamp");
    league2.setIsActive(true);
    league2.setLeagueId(1L);
    league2.setLeagueName("League Name");
    league2.setTeams(new ArrayList<>());

    User user = new User();
    user.setAuthorities(new HashSet<>());
    user.setEmail("jane.doe@example.org");
    user.setLastLogout("Last Logout");
    user.setPassword("password");
    user.setTeam(new Team());
    user.setUserId(1L);
    user.setUsername("janedoe");

    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setLeague(league2);
    team.setRanking(1);
    team.setSecondPickNumber(10);
    team.setStartingPoints(10.0d);
    team.setTeamId(1L);
    team.setTeamName("Team Name");
    team.setTeamPoints(10.0d);
    team.setUser(user);

    User user2 = new User();
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setLastLogout("Last Logout");
    user2.setPassword("password");
    user2.setTeam(team);
    user2.setUserId(1L);
    user2.setUsername("janedoe");

    Team team2 = new Team();
    team2.setDrivers(new ArrayList<>());
    team2.setFirstPickNumber(10);
    team2.setLeague(league);
    team2.setRanking(1);
    team2.setSecondPickNumber(10);
    team2.setStartingPoints(10.0d);
    team2.setTeamId(1L);
    team2.setTeamName("Team Name");
    team2.setTeamPoints(10.0d);
    team2.setUser(user2);
    Optional<Team> ofResult = Optional.of(team2);

    League league3 = new League();
    league3.setActiveTimestamp("Active Timestamp");
    league3.setCreationTimestamp("Creation Timestamp");
    league3.setIsActive(true);
    league3.setLeagueId(1L);
    league3.setLeagueName("League Name");
    league3.setTeams(new ArrayList<>());

    League league4 = new League();
    league4.setActiveTimestamp("Active Timestamp");
    league4.setCreationTimestamp("Creation Timestamp");
    league4.setIsActive(true);
    league4.setLeagueId(1L);
    league4.setLeagueName("League Name");
    league4.setTeams(new ArrayList<>());

    Team team3 = new Team();
    team3.setDrivers(new ArrayList<>());
    team3.setFirstPickNumber(10);
    team3.setLeague(new League());
    team3.setRanking(1);
    team3.setSecondPickNumber(10);
    team3.setStartingPoints(10.0d);
    team3.setTeamId(1L);
    team3.setTeamName("Team Name");
    team3.setTeamPoints(10.0d);
    team3.setUser(new User());

    User user3 = new User();
    user3.setAuthorities(new HashSet<>());
    user3.setEmail("jane.doe@example.org");
    user3.setLastLogout("Last Logout");
    user3.setPassword("password");
    user3.setTeam(team3);
    user3.setUserId(1L);
    user3.setUsername("janedoe");

    Team team4 = new Team();
    team4.setDrivers(new ArrayList<>());
    team4.setFirstPickNumber(10);
    team4.setLeague(league4);
    team4.setRanking(1);
    team4.setSecondPickNumber(10);
    team4.setStartingPoints(10.0d);
    team4.setTeamId(1L);
    team4.setTeamName("Team Name");
    team4.setTeamPoints(10.0d);
    team4.setUser(user3);

    User user4 = new User();
    user4.setAuthorities(new HashSet<>());
    user4.setEmail("jane.doe@example.org");
    user4.setLastLogout("Last Logout");
    user4.setPassword("password");
    user4.setTeam(team4);
    user4.setUserId(1L);
    user4.setUsername("janedoe");

    Team team5 = new Team();
    team5.setDrivers(new ArrayList<>());
    team5.setFirstPickNumber(10);
    team5.setLeague(league3);
    team5.setRanking(1);
    team5.setSecondPickNumber(10);
    team5.setStartingPoints(10.0d);
    team5.setTeamId(1L);
    team5.setTeamName("Team Name");
    team5.setTeamPoints(10.0d);
    team5.setUser(user4);
    when(teamRepository.save(Mockito.any())).thenReturn(team5);
    when(teamRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    League league5 = new League();
    league5.setActiveTimestamp("Active Timestamp");
    league5.setCreationTimestamp("Creation Timestamp");
    league5.setIsActive(true);
    league5.setLeagueId(1L);
    league5.setLeagueName("League Name");
    league5.setTeams(new ArrayList<>());

    League league6 = new League();
    league6.setActiveTimestamp("Active Timestamp");
    league6.setCreationTimestamp("Creation Timestamp");
    league6.setIsActive(true);
    league6.setLeagueId(1L);
    league6.setLeagueName("League Name");
    league6.setTeams(new ArrayList<>());

    User user5 = new User();
    user5.setAuthorities(new HashSet<>());
    user5.setEmail("jane.doe@example.org");
    user5.setLastLogout("Last Logout");
    user5.setPassword("password");
    user5.setTeam(new Team());
    user5.setUserId(1L);
    user5.setUsername("janedoe");

    Team team6 = new Team();
    team6.setDrivers(new ArrayList<>());
    team6.setFirstPickNumber(10);
    team6.setLeague(league6);
    team6.setRanking(1);
    team6.setSecondPickNumber(10);
    team6.setStartingPoints(10.0d);
    team6.setTeamId(1L);
    team6.setTeamName("Team Name");
    team6.setTeamPoints(10.0d);
    team6.setUser(user5);

    User user6 = new User();
    user6.setAuthorities(new HashSet<>());
    user6.setEmail("jane.doe@example.org");
    user6.setLastLogout("Last Logout");
    user6.setPassword("password");
    user6.setTeam(team6);
    user6.setUserId(1L);
    user6.setUsername("janedoe");

    Team team7 = new Team();
    team7.setDrivers(new ArrayList<>());
    team7.setFirstPickNumber(10);
    team7.setLeague(league5);
    team7.setRanking(1);
    team7.setSecondPickNumber(10);
    team7.setStartingPoints(10.0d);
    team7.setTeamId(1L);
    team7.setTeamName("Team Name");
    team7.setTeamPoints(10.0d);
    team7.setUser(user6);

    User user7 = new User();
    user7.setAuthorities(new HashSet<>());
    user7.setEmail("jane.doe@example.org");
    user7.setLastLogout("Last Logout");
    user7.setPassword("password");
    user7.setTeam(team7);
    user7.setUserId(1L);
    user7.setUsername("janedoe");
    doNothing().when(userService).save(Mockito.any());
    when(userService.findById(Mockito.<Long>any())).thenReturn(user7);
    teamService.addDriverToTeam(1L, 1L);
    verify(driverService).findById(Mockito.<Long>any());
    verify(driverService).save(Mockito.any());
    verify(teamRepository).save(Mockito.any());
    verify(teamRepository).findById(Mockito.<Long>any());
    verify(userService).findById(Mockito.<Long>any());
    verify(userService).save(Mockito.any());
  }

  @Test
  void testGetNextToPick() {
    when(teamRepository.findAll()).thenReturn(new ArrayList<>());

    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());
    assertNull(teamService.getNextToPickName(league));
    verify(teamRepository).findAll();
  }

  @Test
  void testGetNextToPick2() {
    when(leagueService.getCurrentPickNumber(Mockito.any())).thenReturn(10);

    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());

    League league2 = new League();
    league2.setActiveTimestamp("Active Timestamp");
    league2.setCreationTimestamp("Creation Timestamp");
    league2.setIsActive(true);
    league2.setLeagueId(1L);
    league2.setLeagueName("League Name");
    league2.setTeams(new ArrayList<>());

    User user = new User();
    user.setAuthorities(new HashSet<>());
    user.setEmail("jane.doe@example.org");
    user.setLastLogout("Last Logout");
    user.setPassword("password");
    user.setTeam(new Team());
    user.setUserId(1L);
    user.setUsername("janedoe");

    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setLeague(league2);
    team.setRanking(1);
    team.setSecondPickNumber(10);
    team.setStartingPoints(10.0d);
    team.setTeamId(1L);
    team.setTeamName("Team Name");
    team.setTeamPoints(10.0d);
    team.setUser(user);

    User user2 = new User();
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setLastLogout("Last Logout");
    user2.setPassword("password");
    user2.setTeam(team);
    user2.setUserId(1L);
    user2.setUsername("janedoe");

    Team team2 = new Team();
    team2.setDrivers(new ArrayList<>());
    team2.setFirstPickNumber(10);
    team2.setLeague(league);
    team2.setRanking(1);
    team2.setSecondPickNumber(10);
    team2.setStartingPoints(10.0d);
    team2.setTeamId(1L);
    team2.setTeamName("Team Name");
    team2.setTeamPoints(10.0d);
    team2.setUser(user2);

    ArrayList<Team> teamList = new ArrayList<>();
    teamList.add(team2);

    League league3 = new League();
    league3.setActiveTimestamp("Active Timestamp");
    league3.setCreationTimestamp("Creation Timestamp");
    league3.setIsActive(true);
    league3.setLeagueId(1L);
    league3.setLeagueName("League Name");
    league3.setTeams(new ArrayList<>());

    League league4 = new League();
    league4.setActiveTimestamp("Active Timestamp");
    league4.setCreationTimestamp("Creation Timestamp");
    league4.setIsActive(true);
    league4.setLeagueId(1L);
    league4.setLeagueName("League Name");
    league4.setTeams(new ArrayList<>());

    User user3 = new User();
    user3.setAuthorities(new HashSet<>());
    user3.setEmail("jane.doe@example.org");
    user3.setLastLogout("Last Logout");
    user3.setPassword("password");
    user3.setTeam(new Team());
    user3.setUserId(1L);
    user3.setUsername("janedoe");

    Team team3 = new Team();
    team3.setDrivers(new ArrayList<>());
    team3.setFirstPickNumber(10);
    team3.setLeague(league4);
    team3.setRanking(1);
    team3.setSecondPickNumber(10);
    team3.setStartingPoints(10.0d);
    team3.setTeamId(1L);
    team3.setTeamName("Team Name");
    team3.setTeamPoints(10.0d);
    team3.setUser(user3);

    User user4 = new User();
    user4.setAuthorities(new HashSet<>());
    user4.setEmail("jane.doe@example.org");
    user4.setLastLogout("Last Logout");
    user4.setPassword("password");
    user4.setTeam(team3);
    user4.setUserId(1L);
    user4.setUsername("janedoe");

    Team team4 = new Team();
    team4.setDrivers(new ArrayList<>());
    team4.setFirstPickNumber(10);
    team4.setLeague(league3);
    team4.setRanking(1);
    team4.setSecondPickNumber(10);
    team4.setStartingPoints(10.0d);
    team4.setTeamId(1L);
    team4.setTeamName("Team Name");
    team4.setTeamPoints(10.0d);
    team4.setUser(user4);
    Optional<Team> ofResult = Optional.of(team4);
    when(teamRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    when(teamRepository.findAll()).thenReturn(teamList);

    League league5 = new League();
    league5.setActiveTimestamp("Active Timestamp");
    league5.setCreationTimestamp("Creation Timestamp");
    league5.setIsActive(true);
    league5.setLeagueId(1L);
    league5.setLeagueName("League Name");
    league5.setTeams(new ArrayList<>());
    assertEquals("janedoe", teamService.getNextToPickName(league5));
    verify(leagueService).getCurrentPickNumber(Mockito.any());
    verify(teamRepository).findAll();
    verify(teamRepository, atLeast(1)).findById(Mockito.<Long>any());
  }

  @Test
  void testGetNextToPick3() {
    when(leagueService.getCurrentPickNumber(Mockito.any())).thenReturn(1);

    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());

    League league2 = new League();
    league2.setActiveTimestamp("Active Timestamp");
    league2.setCreationTimestamp("Creation Timestamp");
    league2.setIsActive(true);
    league2.setLeagueId(1L);
    league2.setLeagueName("League Name");
    league2.setTeams(new ArrayList<>());

    User user = new User();
    user.setAuthorities(new HashSet<>());
    user.setEmail("jane.doe@example.org");
    user.setLastLogout("Last Logout");
    user.setPassword("password");
    user.setTeam(new Team());
    user.setUserId(1L);
    user.setUsername("janedoe");

    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setLeague(league2);
    team.setRanking(1);
    team.setSecondPickNumber(10);
    team.setStartingPoints(10.0d);
    team.setTeamId(1L);
    team.setTeamName("Team Name");
    team.setTeamPoints(10.0d);
    team.setUser(user);

    User user2 = new User();
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setLastLogout("Last Logout");
    user2.setPassword("password");
    user2.setTeam(team);
    user2.setUserId(1L);
    user2.setUsername("janedoe");

    Team team2 = new Team();
    team2.setDrivers(new ArrayList<>());
    team2.setFirstPickNumber(10);
    team2.setLeague(league);
    team2.setRanking(1);
    team2.setSecondPickNumber(10);
    team2.setStartingPoints(10.0d);
    team2.setTeamId(1L);
    team2.setTeamName("Team Name");
    team2.setTeamPoints(10.0d);
    team2.setUser(user2);

    ArrayList<Team> teamList = new ArrayList<>();
    teamList.add(team2);

    League league3 = new League();
    league3.setActiveTimestamp("Active Timestamp");
    league3.setCreationTimestamp("Creation Timestamp");
    league3.setIsActive(true);
    league3.setLeagueId(1L);
    league3.setLeagueName("League Name");
    league3.setTeams(new ArrayList<>());

    League league4 = new League();
    league4.setActiveTimestamp("Active Timestamp");
    league4.setCreationTimestamp("Creation Timestamp");
    league4.setIsActive(true);
    league4.setLeagueId(1L);
    league4.setLeagueName("League Name");
    league4.setTeams(new ArrayList<>());

    User user3 = new User();
    user3.setAuthorities(new HashSet<>());
    user3.setEmail("jane.doe@example.org");
    user3.setLastLogout("Last Logout");
    user3.setPassword("password");
    user3.setTeam(new Team());
    user3.setUserId(1L);
    user3.setUsername("janedoe");

    Team team3 = new Team();
    team3.setDrivers(new ArrayList<>());
    team3.setFirstPickNumber(10);
    team3.setLeague(league4);
    team3.setRanking(1);
    team3.setSecondPickNumber(10);
    team3.setStartingPoints(10.0d);
    team3.setTeamId(1L);
    team3.setTeamName("Team Name");
    team3.setTeamPoints(10.0d);
    team3.setUser(user3);

    User user4 = new User();
    user4.setAuthorities(new HashSet<>());
    user4.setEmail("jane.doe@example.org");
    user4.setLastLogout("Last Logout");
    user4.setPassword("password");
    user4.setTeam(team3);
    user4.setUserId(1L);
    user4.setUsername("janedoe");

    Team team4 = new Team();
    team4.setDrivers(new ArrayList<>());
    team4.setFirstPickNumber(10);
    team4.setLeague(league3);
    team4.setRanking(1);
    team4.setSecondPickNumber(10);
    team4.setStartingPoints(10.0d);
    team4.setTeamId(1L);
    team4.setTeamName("Team Name");
    team4.setTeamPoints(10.0d);
    team4.setUser(user4);
    Optional<Team> ofResult = Optional.of(team4);
    when(teamRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    when(teamRepository.findAll()).thenReturn(teamList);

    League league5 = new League();
    league5.setActiveTimestamp("Active Timestamp");
    league5.setCreationTimestamp("Creation Timestamp");
    league5.setIsActive(true);
    league5.setLeagueId(1L);
    league5.setLeagueName("League Name");
    league5.setTeams(new ArrayList<>());
    assertNull(teamService.getNextToPickName(league5));
    verify(leagueService, atLeast(1)).getCurrentPickNumber(Mockito.any());
    verify(teamRepository).findAll();
    verify(teamRepository, atLeast(1)).findById(Mockito.<Long>any());
  }

  @Test
  void testGetAllTeams() {
    ArrayList<Team> teamList = new ArrayList<>();
    when(teamRepository.findAll()).thenReturn(teamList);
    List<Team> actualAllTeams = teamService.getAllTeams();
    assertSame(teamList, actualAllTeams);
    assertTrue(actualAllTeams.isEmpty());
    verify(teamRepository).findAll();
  }

  @Test
  void testGetAllTeamsByLeague() {
    when(teamRepository.findAll()).thenReturn(new ArrayList<>());

    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());
    assertTrue(teamService.getAllTeamsByLeague(league).isEmpty());
    verify(teamRepository).findAll();
  }


  @Test
  void testGetAllTeamsByLeague2() {
    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());

    League league2 = new League();
    league2.setActiveTimestamp("Active Timestamp");
    league2.setCreationTimestamp("Creation Timestamp");
    league2.setIsActive(true);
    league2.setLeagueId(1L);
    league2.setLeagueName("League Name");
    league2.setTeams(new ArrayList<>());

    User user = new User();
    user.setAuthorities(new HashSet<>());
    user.setEmail("jane.doe@example.org");
    user.setLastLogout("Last Logout");
    user.setPassword("password");
    user.setTeam(new Team());
    user.setUserId(1L);
    user.setUsername("janedoe");

    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setLeague(league2);
    team.setRanking(1);
    team.setSecondPickNumber(10);
    team.setStartingPoints(10.0d);
    team.setTeamId(1L);
    team.setTeamName("Team Name");
    team.setTeamPoints(10.0d);
    team.setUser(user);

    User user2 = new User();
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setLastLogout("Last Logout");
    user2.setPassword("password");
    user2.setTeam(team);
    user2.setUserId(1L);
    user2.setUsername("janedoe");

    Team team2 = new Team();
    team2.setDrivers(new ArrayList<>());
    team2.setFirstPickNumber(10);
    team2.setLeague(league);
    team2.setRanking(1);
    team2.setSecondPickNumber(10);
    team2.setStartingPoints(10.0d);
    team2.setTeamId(1L);
    team2.setTeamName("Team Name");
    team2.setTeamPoints(10.0d);
    team2.setUser(user2);

    ArrayList<Team> teamList = new ArrayList<>();
    teamList.add(team2);
    when(teamRepository.findAll()).thenReturn(teamList);

    League league3 = new League();
    league3.setActiveTimestamp("Active Timestamp");
    league3.setCreationTimestamp("Creation Timestamp");
    league3.setIsActive(true);
    league3.setLeagueId(1L);
    league3.setLeagueName("League Name");
    league3.setTeams(new ArrayList<>());
    assertEquals(1, teamService.getAllTeamsByLeague(league3).size());
    verify(teamRepository).findAll();
  }

  @Test
  void testGetAllTeamsByLeague3() {
    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());

    League league2 = new League();
    league2.setActiveTimestamp("Active Timestamp");
    league2.setCreationTimestamp("Creation Timestamp");
    league2.setIsActive(true);
    league2.setLeagueId(1L);
    league2.setLeagueName("League Name");
    league2.setTeams(new ArrayList<>());

    User user = new User();
    user.setAuthorities(new HashSet<>());
    user.setEmail("jane.doe@example.org");
    user.setLastLogout("Last Logout");
    user.setPassword("password");
    user.setTeam(new Team());
    user.setUserId(1L);
    user.setUsername("janedoe");

    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setLeague(league2);
    team.setRanking(1);
    team.setSecondPickNumber(10);
    team.setStartingPoints(10.0d);
    team.setTeamId(1L);
    team.setTeamName("Team Name");
    team.setTeamPoints(10.0d);
    team.setUser(user);

    User user2 = new User();
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setLastLogout("Last Logout");
    user2.setPassword("password");
    user2.setTeam(team);
    user2.setUserId(1L);
    user2.setUsername("janedoe");

    Team team2 = new Team();
    team2.setDrivers(new ArrayList<>());
    team2.setFirstPickNumber(10);
    team2.setLeague(league);
    team2.setRanking(1);
    team2.setSecondPickNumber(10);
    team2.setStartingPoints(10.0d);
    team2.setTeamId(1L);
    team2.setTeamName("Team Name");
    team2.setTeamPoints(10.0d);
    team2.setUser(user2);

    League league3 = new League();
    league3.setActiveTimestamp("me.niallmurray.slipstream9.domain.League");
    league3.setCreationTimestamp("me.niallmurray.slipstream9.domain.League");
    league3.setIsActive(false);
    league3.setLeagueId(2L);
    league3.setLeagueName("me.niallmurray.slipstream9.domain.League");
    league3.setTeams(new ArrayList<>());

    League league4 = new League();
    league4.setActiveTimestamp("me.niallmurray.slipstream9.domain.League");
    league4.setCreationTimestamp("me.niallmurray.slipstream9.domain.League");
    league4.setIsActive(false);
    league4.setLeagueId(2L);
    league4.setLeagueName("me.niallmurray.slipstream9.domain.League");
    league4.setTeams(new ArrayList<>());

    User user3 = new User();
    user3.setAuthorities(new HashSet<>());
    user3.setEmail("john.smith@example.org");
    user3.setLastLogout("42");
    user3.setPassword("Password");
    user3.setTeam(new Team());
    user3.setUserId(2L);
    user3.setUsername("Username");

    Team team3 = new Team();
    team3.setDrivers(new ArrayList<>());
    team3.setFirstPickNumber(1);
    team3.setLeague(league4);
    team3.setRanking(5);
    team3.setSecondPickNumber(1);
    team3.setStartingPoints(0.5d);
    team3.setTeamId(2L);
    team3.setTeamName("42");
    team3.setTeamPoints(0.5d);
    team3.setUser(user3);

    User user4 = new User();
    user4.setAuthorities(new HashSet<>());
    user4.setEmail("john.smith@example.org");
    user4.setLastLogout("42");
    user4.setPassword("Password");
    user4.setTeam(team3);
    user4.setUserId(2L);
    user4.setUsername("Username");

    Team team4 = new Team();
    team4.setDrivers(new ArrayList<>());
    team4.setFirstPickNumber(1);
    team4.setLeague(league3);
    team4.setRanking(5);
    team4.setSecondPickNumber(1);
    team4.setStartingPoints(0.5d);
    team4.setTeamId(2L);
    team4.setTeamName("42");
    team4.setTeamPoints(0.5d);
    team4.setUser(user4);

    ArrayList<Team> teamList = new ArrayList<>();
    teamList.add(team4);
    teamList.add(team2);
    when(teamRepository.findAll()).thenReturn(teamList);

    League league5 = new League();
    league5.setActiveTimestamp("Active Timestamp");
    league5.setCreationTimestamp("Creation Timestamp");
    league5.setIsActive(true);
    league5.setLeagueId(1L);
    league5.setLeagueName("League Name");
    league5.setTeams(new ArrayList<>());
    assertEquals(1, teamService.getAllTeamsByLeague(league5).size());
    verify(teamRepository).findAll();
  }

  @Test
  void testGetAllTeamsByNextPick() {
    ArrayList<Team> teamList = new ArrayList<>();
    when(teamRepository.findAll()).thenReturn(teamList);
    List<Team> actualAllTeamsByNextPick = teamService.getAllTeamsByNextPick();
    assertSame(teamList, actualAllTeamsByNextPick);
    assertTrue(actualAllTeamsByNextPick.isEmpty());
    verify(teamRepository).findAll();
  }

  @Test
  void testGetAllTeamsByNextPick2() {
    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());

    League league2 = new League();
    league2.setActiveTimestamp("Active Timestamp");
    league2.setCreationTimestamp("Creation Timestamp");
    league2.setIsActive(true);
    league2.setLeagueId(1L);
    league2.setLeagueName("League Name");
    league2.setTeams(new ArrayList<>());

    User user = new User();
    user.setAuthorities(new HashSet<>());
    user.setEmail("jane.doe@example.org");
    user.setLastLogout("Last Logout");
    user.setPassword("password");
    user.setTeam(new Team());
    user.setUserId(1L);
    user.setUsername("janedoe");

    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setLeague(league2);
    team.setRanking(1);
    team.setSecondPickNumber(10);
    team.setStartingPoints(10.0d);
    team.setTeamId(1L);
    team.setTeamName("Team Name");
    team.setTeamPoints(10.0d);
    team.setUser(user);

    User user2 = new User();
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setLastLogout("Last Logout");
    user2.setPassword("password");
    user2.setTeam(team);
    user2.setUserId(1L);
    user2.setUsername("janedoe");

    Team team2 = new Team();
    team2.setDrivers(new ArrayList<>());
    team2.setFirstPickNumber(10);
    team2.setLeague(league);
    team2.setRanking(1);
    team2.setSecondPickNumber(10);
    team2.setStartingPoints(10.0d);
    team2.setTeamId(1L);
    team2.setTeamName("Team Name");
    team2.setTeamPoints(10.0d);
    team2.setUser(user2);

    League league3 = new League();
    league3.setActiveTimestamp("me.niallmurray.slipstream9.domain.League");
    league3.setCreationTimestamp("me.niallmurray.slipstream9.domain.League");
    league3.setIsActive(false);
    league3.setLeagueId(2L);
    league3.setLeagueName("me.niallmurray.slipstream9.domain.League");
    league3.setTeams(new ArrayList<>());

    League league4 = new League();
    league4.setActiveTimestamp("me.niallmurray.slipstream9.domain.League");
    league4.setCreationTimestamp("me.niallmurray.slipstream9.domain.League");
    league4.setIsActive(false);
    league4.setLeagueId(2L);
    league4.setLeagueName("me.niallmurray.slipstream9.domain.League");
    league4.setTeams(new ArrayList<>());

    User user3 = new User();
    user3.setAuthorities(new HashSet<>());
    user3.setEmail("john.smith@example.org");
    user3.setLastLogout("42");
    user3.setPassword("Password");
    user3.setTeam(new Team());
    user3.setUserId(2L);
    user3.setUsername("Username");

    Team team3 = new Team();
    team3.setDrivers(new ArrayList<>());
    team3.setFirstPickNumber(1);
    team3.setLeague(league4);
    team3.setRanking(0);
    team3.setSecondPickNumber(1);
    team3.setStartingPoints(0.5d);
    team3.setTeamId(2L);
    team3.setTeamName("42");
    team3.setTeamPoints(0.5d);
    team3.setUser(user3);

    User user4 = new User();
    user4.setAuthorities(new HashSet<>());
    user4.setEmail("john.smith@example.org");
    user4.setLastLogout("42");
    user4.setPassword("Password");
    user4.setTeam(team3);
    user4.setUserId(2L);
    user4.setUsername("Username");

    Team team4 = new Team();
    team4.setDrivers(new ArrayList<>());
    team4.setFirstPickNumber(1);
    team4.setLeague(league3);
    team4.setRanking(0);
    team4.setSecondPickNumber(1);
    team4.setStartingPoints(0.5d);
    team4.setTeamId(2L);
    team4.setTeamName("42");
    team4.setTeamPoints(0.5d);
    team4.setUser(user4);

    ArrayList<Team> teamList = new ArrayList<>();
    teamList.add(team4);
    teamList.add(team2);
    when(teamRepository.findAll()).thenReturn(teamList);
    List<Team> actualAllTeamsByNextPick = teamService.getAllTeamsByNextPick();
    assertSame(teamList, actualAllTeamsByNextPick);
    assertEquals(2, actualAllTeamsByNextPick.size());
    verify(teamRepository).findAll();
  }

  @Test
  void testDeleteTeam() {
    doNothing().when(leagueService).save(Mockito.any());
    doNothing().when(teamRepository).delete(Mockito.any());
    doNothing().when(userService).save(Mockito.any());

    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());

    League league2 = new League();
    league2.setActiveTimestamp("Active Timestamp");
    league2.setCreationTimestamp("Creation Timestamp");
    league2.setIsActive(true);
    league2.setLeagueId(1L);
    league2.setLeagueName("League Name");
    league2.setTeams(new ArrayList<>());

    User user = new User();
    user.setAuthorities(new HashSet<>());
    user.setEmail("jane.doe@example.org");
    user.setLastLogout("Last Logout");
    user.setPassword("password");
    user.setTeam(new Team());
    user.setUserId(1L);
    user.setUsername("janedoe");

    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setLeague(league2);
    team.setRanking(1);
    team.setSecondPickNumber(10);
    team.setStartingPoints(10.0d);
    team.setTeamId(1L);
    team.setTeamName("Team Name");
    team.setTeamPoints(10.0d);
    team.setUser(user);

    User user2 = new User();
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setLastLogout("Last Logout");
    user2.setPassword("password");
    user2.setTeam(team);
    user2.setUserId(1L);
    user2.setUsername("janedoe");

    Team team2 = new Team();
    team2.setDrivers(new ArrayList<>());
    team2.setFirstPickNumber(10);
    team2.setLeague(league);
    team2.setRanking(1);
    team2.setSecondPickNumber(10);
    team2.setStartingPoints(10.0d);
    team2.setTeamId(1L);
    team2.setTeamName("Team Name");
    team2.setTeamPoints(10.0d);
    team2.setUser(user2);
    teamService.deleteTeam(team2);
    verify(leagueService).save(Mockito.any());
    verify(teamRepository).delete(Mockito.any());
    verify(userService).save(Mockito.any());
    assertNull(team2.getUser().getTeam());
    assertTrue(team2.getLeague().getTeams().isEmpty());
  }

  @Test
  void testDeleteTeam2() {
    doNothing().when(driverService).save(Mockito.any());
    doNothing().when(leagueService).save(Mockito.any());
    doNothing().when(teamRepository).delete(Mockito.any());
    doNothing().when(userService).save(Mockito.any());

    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());

    League league2 = new League();
    league2.setActiveTimestamp("Active Timestamp");
    league2.setCreationTimestamp("Creation Timestamp");
    league2.setIsActive(true);
    league2.setLeagueId(1L);
    league2.setLeagueName("League Name");
    league2.setTeams(new ArrayList<>());

    User user = new User();
    user.setAuthorities(new HashSet<>());
    user.setEmail("jane.doe@example.org");
    user.setLastLogout("Last Logout");
    user.setPassword("password");
    user.setTeam(new Team());
    user.setUserId(1L);
    user.setUsername("janedoe");

    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setLeague(league2);
    team.setRanking(1);
    team.setSecondPickNumber(10);
    team.setStartingPoints(10.0d);
    team.setTeamId(1L);
    team.setTeamName("Team Name");
    team.setTeamPoints(10.0d);
    team.setUser(user);

    User user2 = new User();
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setLastLogout("Last Logout");
    user2.setPassword("password");
    user2.setTeam(team);
    user2.setUserId(1L);
    user2.setUsername("janedoe");

    Driver driver = new Driver();
    driver.setCarNumber(10);
    driver.setConstructor("Test:");
    driver.setDateOfBirth("2020-03-01");
    driver.setDriverId(1L);
    driver.setFirstName("Jane");
    driver.setNationality("Test:");
    driver.setPoints(10.0d);
    driver.setShortName("Test:");
    driver.setStanding(1);
    driver.setSurname("Doe");
    driver.setTeams(new ArrayList<>());
    driver.setWikiPage("Test:");

    ArrayList<Driver> driverList = new ArrayList<>();
    driverList.add(driver);

    League league3 = new League();
    league3.setActiveTimestamp("Active Timestamp");
    league3.setCreationTimestamp("Creation Timestamp");
    league3.setIsActive(true);
    league3.setLeagueId(1L);
    league3.setLeagueName("League Name");
    league3.setTeams(new ArrayList<>());

    League league4 = new League();
    league4.setActiveTimestamp("Active Timestamp");
    league4.setCreationTimestamp("Creation Timestamp");
    league4.setIsActive(true);
    league4.setLeagueId(1L);
    league4.setLeagueName("League Name");
    league4.setTeams(new ArrayList<>());

    League league5 = new League();
    league5.setActiveTimestamp("Active Timestamp");
    league5.setCreationTimestamp("Creation Timestamp");
    league5.setIsActive(true);
    league5.setLeagueId(1L);
    league5.setLeagueName("League Name");
    league5.setTeams(new ArrayList<>());

    User user3 = new User();
    user3.setAuthorities(new HashSet<>());
    user3.setEmail("jane.doe@example.org");
    user3.setLastLogout("Last Logout");
    user3.setPassword("password");
    user3.setTeam(new Team());
    user3.setUserId(1L);
    user3.setUsername("janedoe");

    Team team2 = new Team();
    team2.setDrivers(new ArrayList<>());
    team2.setFirstPickNumber(10);
    team2.setLeague(league5);
    team2.setRanking(1);
    team2.setSecondPickNumber(10);
    team2.setStartingPoints(10.0d);
    team2.setTeamId(1L);
    team2.setTeamName("Team Name");
    team2.setTeamPoints(10.0d);
    team2.setUser(user3);

    User user4 = new User();
    user4.setAuthorities(new HashSet<>());
    user4.setEmail("jane.doe@example.org");
    user4.setLastLogout("Last Logout");
    user4.setPassword("password");
    user4.setTeam(team2);
    user4.setUserId(1L);
    user4.setUsername("janedoe");

    Team team3 = new Team();
    team3.setDrivers(new ArrayList<>());
    team3.setFirstPickNumber(10);
    team3.setLeague(league4);
    team3.setRanking(1);
    team3.setSecondPickNumber(10);
    team3.setStartingPoints(10.0d);
    team3.setTeamId(1L);
    team3.setTeamName("Team Name");
    team3.setTeamPoints(10.0d);
    team3.setUser(user4);

    User user5 = new User();
    user5.setAuthorities(new HashSet<>());
    user5.setEmail("jane.doe@example.org");
    user5.setLastLogout("Last Logout");
    user5.setPassword("password");
    user5.setTeam(team3);
    user5.setUserId(1L);
    user5.setUsername("janedoe");
    Team team4 = mock(Team.class);
    when(team4.getDrivers()).thenReturn(driverList);
    when(team4.getLeague()).thenReturn(league3);
    when(team4.getUser()).thenReturn(user5);
    doNothing().when(team4).setDrivers(Mockito.any());
    doNothing().when(team4).setFirstPickNumber(Mockito.<Integer>any());
    doNothing().when(team4).setLeague(Mockito.any());
    doNothing().when(team4).setRanking(Mockito.<Integer>any());
    doNothing().when(team4).setSecondPickNumber(Mockito.<Integer>any());
    doNothing().when(team4).setStartingPoints(Mockito.<Double>any());
    doNothing().when(team4).setTeamId(Mockito.<Long>any());
    doNothing().when(team4).setTeamName(Mockito.any());
    doNothing().when(team4).setTeamPoints(Mockito.<Double>any());
    doNothing().when(team4).setUser(Mockito.any());
    team4.setDrivers(new ArrayList<>());
    team4.setFirstPickNumber(10);
    team4.setLeague(league);
    team4.setRanking(1);
    team4.setSecondPickNumber(10);
    team4.setStartingPoints(10.0d);
    team4.setTeamId(1L);
    team4.setTeamName("Team Name");
    team4.setTeamPoints(10.0d);
    team4.setUser(user2);
    teamService.deleteTeam(team4);
    verify(driverService).save(Mockito.any());
    verify(leagueService).save(Mockito.any());
    verify(teamRepository).delete(Mockito.any());
    verify(userService).save(Mockito.any());
    verify(team4).getDrivers();
    verify(team4).getLeague();
    verify(team4).getUser();
    verify(team4).setDrivers(Mockito.any());
    verify(team4).setFirstPickNumber(Mockito.<Integer>any());
    verify(team4).setLeague(Mockito.any());
    verify(team4).setRanking(Mockito.<Integer>any());
    verify(team4).setSecondPickNumber(Mockito.<Integer>any());
    verify(team4).setStartingPoints(Mockito.<Double>any());
    verify(team4).setTeamId(Mockito.<Long>any());
    verify(team4).setTeamName(Mockito.any());
    verify(team4).setTeamPoints(Mockito.<Double>any());
    verify(team4).setUser(Mockito.any());
  }

  @Test
  void testDeleteTeam3() {
    doNothing().when(driverService).save(Mockito.any());
    doNothing().when(leagueService).save(Mockito.any());
    doNothing().when(teamRepository).delete(Mockito.any());
    doNothing().when(userService).save(Mockito.any());

    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());

    League league2 = new League();
    league2.setActiveTimestamp("Active Timestamp");
    league2.setCreationTimestamp("Creation Timestamp");
    league2.setIsActive(true);
    league2.setLeagueId(1L);
    league2.setLeagueName("League Name");
    league2.setTeams(new ArrayList<>());

    User user = new User();
    user.setAuthorities(new HashSet<>());
    user.setEmail("jane.doe@example.org");
    user.setLastLogout("Last Logout");
    user.setPassword("password");
    user.setTeam(new Team());
    user.setUserId(1L);
    user.setUsername("janedoe");

    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setLeague(league2);
    team.setRanking(1);
    team.setSecondPickNumber(10);
    team.setStartingPoints(10.0d);
    team.setTeamId(1L);
    team.setTeamName("Team Name");
    team.setTeamPoints(10.0d);
    team.setUser(user);

    User user2 = new User();
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setLastLogout("Last Logout");
    user2.setPassword("password");
    user2.setTeam(team);
    user2.setUserId(1L);
    user2.setUsername("janedoe");

    Driver driver = new Driver();
    driver.setCarNumber(10);
    driver.setConstructor("Test:");
    driver.setDateOfBirth("2020-03-01");
    driver.setDriverId(1L);
    driver.setFirstName("Jane");
    driver.setNationality("Test:");
    driver.setPoints(10.0d);
    driver.setShortName("Test:");
    driver.setStanding(1);
    driver.setSurname("Doe");
    driver.setTeams(new ArrayList<>());
    driver.setWikiPage("Test:");

    ArrayList<Driver> driverList = new ArrayList<>();
    driverList.add(driver);
    League league3 = mock(League.class);
    when(league3.getTeams()).thenReturn(new ArrayList<>());
    doNothing().when(league3).setActiveTimestamp(Mockito.any());
    doNothing().when(league3).setCreationTimestamp(Mockito.any());
    doNothing().when(league3).setIsActive(Mockito.<Boolean>any());
    doNothing().when(league3).setLeagueId(Mockito.<Long>any());
    doNothing().when(league3).setLeagueName(Mockito.any());
    doNothing().when(league3).setTeams(Mockito.any());
    league3.setActiveTimestamp("Active Timestamp");
    league3.setCreationTimestamp("Creation Timestamp");
    league3.setIsActive(true);
    league3.setLeagueId(1L);
    league3.setLeagueName("League Name");
    league3.setTeams(new ArrayList<>());

    League league4 = new League();
    league4.setActiveTimestamp("Active Timestamp");
    league4.setCreationTimestamp("Creation Timestamp");
    league4.setIsActive(true);
    league4.setLeagueId(1L);
    league4.setLeagueName("League Name");
    league4.setTeams(new ArrayList<>());

    League league5 = new League();
    league5.setActiveTimestamp("Active Timestamp");
    league5.setCreationTimestamp("Creation Timestamp");
    league5.setIsActive(true);
    league5.setLeagueId(1L);
    league5.setLeagueName("League Name");
    league5.setTeams(new ArrayList<>());

    User user3 = new User();
    user3.setAuthorities(new HashSet<>());
    user3.setEmail("jane.doe@example.org");
    user3.setLastLogout("Last Logout");
    user3.setPassword("password");
    user3.setTeam(new Team());
    user3.setUserId(1L);
    user3.setUsername("janedoe");

    Team team2 = new Team();
    team2.setDrivers(new ArrayList<>());
    team2.setFirstPickNumber(10);
    team2.setLeague(league5);
    team2.setRanking(1);
    team2.setSecondPickNumber(10);
    team2.setStartingPoints(10.0d);
    team2.setTeamId(1L);
    team2.setTeamName("Team Name");
    team2.setTeamPoints(10.0d);
    team2.setUser(user3);

    User user4 = new User();
    user4.setAuthorities(new HashSet<>());
    user4.setEmail("jane.doe@example.org");
    user4.setLastLogout("Last Logout");
    user4.setPassword("password");
    user4.setTeam(team2);
    user4.setUserId(1L);
    user4.setUsername("janedoe");

    Team team3 = new Team();
    team3.setDrivers(new ArrayList<>());
    team3.setFirstPickNumber(10);
    team3.setLeague(league4);
    team3.setRanking(1);
    team3.setSecondPickNumber(10);
    team3.setStartingPoints(10.0d);
    team3.setTeamId(1L);
    team3.setTeamName("Team Name");
    team3.setTeamPoints(10.0d);
    team3.setUser(user4);

    User user5 = new User();
    user5.setAuthorities(new HashSet<>());
    user5.setEmail("jane.doe@example.org");
    user5.setLastLogout("Last Logout");
    user5.setPassword("password");
    user5.setTeam(team3);
    user5.setUserId(1L);
    user5.setUsername("janedoe");
    Team team4 = mock(Team.class);
    when(team4.getDrivers()).thenReturn(driverList);
    when(team4.getLeague()).thenReturn(league3);
    when(team4.getUser()).thenReturn(user5);
    doNothing().when(team4).setDrivers(Mockito.any());
    doNothing().when(team4).setFirstPickNumber(Mockito.<Integer>any());
    doNothing().when(team4).setLeague(Mockito.any());
    doNothing().when(team4).setRanking(Mockito.<Integer>any());
    doNothing().when(team4).setSecondPickNumber(Mockito.<Integer>any());
    doNothing().when(team4).setStartingPoints(Mockito.<Double>any());
    doNothing().when(team4).setTeamId(Mockito.<Long>any());
    doNothing().when(team4).setTeamName(Mockito.any());
    doNothing().when(team4).setTeamPoints(Mockito.<Double>any());
    doNothing().when(team4).setUser(Mockito.any());
    team4.setDrivers(new ArrayList<>());
    team4.setFirstPickNumber(10);
    team4.setLeague(league);
    team4.setRanking(1);
    team4.setSecondPickNumber(10);
    team4.setStartingPoints(10.0d);
    team4.setTeamId(1L);
    team4.setTeamName("Team Name");
    team4.setTeamPoints(10.0d);
    team4.setUser(user2);
    teamService.deleteTeam(team4);
    verify(driverService).save(Mockito.any());
    verify(leagueService).save(Mockito.any());
    verify(teamRepository).delete(Mockito.any());
    verify(userService).save(Mockito.any());
    verify(team4).getDrivers();
    verify(team4).getLeague();
    verify(team4).getUser();
    verify(team4).setDrivers(Mockito.any());
    verify(team4).setFirstPickNumber(Mockito.<Integer>any());
    verify(team4).setLeague(Mockito.any());
    verify(team4).setRanking(Mockito.<Integer>any());
    verify(team4).setSecondPickNumber(Mockito.<Integer>any());
    verify(team4).setStartingPoints(Mockito.<Double>any());
    verify(team4).setTeamId(Mockito.<Long>any());
    verify(team4).setTeamName(Mockito.any());
    verify(team4).setTeamPoints(Mockito.<Double>any());
    verify(team4).setUser(Mockito.any());
    verify(league3).getTeams();
    verify(league3).setActiveTimestamp(Mockito.any());
    verify(league3).setCreationTimestamp(Mockito.any());
    verify(league3).setIsActive(Mockito.<Boolean>any());
    verify(league3).setLeagueId(Mockito.<Long>any());
    verify(league3).setLeagueName(Mockito.any());
    verify(league3).setTeams(Mockito.any());
  }

  @Test
  void testFindById() {
    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());

    League league2 = new League();
    league2.setActiveTimestamp("Active Timestamp");
    league2.setCreationTimestamp("Creation Timestamp");
    league2.setIsActive(true);
    league2.setLeagueId(1L);
    league2.setLeagueName("League Name");
    league2.setTeams(new ArrayList<>());

    User user = new User();
    user.setAuthorities(new HashSet<>());
    user.setEmail("jane.doe@example.org");
    user.setLastLogout("Last Logout");
    user.setPassword("password");
    user.setTeam(new Team());
    user.setUserId(1L);
    user.setUsername("janedoe");

    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setLeague(league2);
    team.setRanking(1);
    team.setSecondPickNumber(10);
    team.setStartingPoints(10.0d);
    team.setTeamId(1L);
    team.setTeamName("Team Name");
    team.setTeamPoints(10.0d);
    team.setUser(user);

    User user2 = new User();
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setLastLogout("Last Logout");
    user2.setPassword("password");
    user2.setTeam(team);
    user2.setUserId(1L);
    user2.setUsername("janedoe");

    Team team2 = new Team();
    team2.setDrivers(new ArrayList<>());
    team2.setFirstPickNumber(10);
    team2.setLeague(league);
    team2.setRanking(1);
    team2.setSecondPickNumber(10);
    team2.setStartingPoints(10.0d);
    team2.setTeamId(1L);
    team2.setTeamName("Team Name");
    team2.setTeamPoints(10.0d);
    team2.setUser(user2);
    Optional<Team> ofResult = Optional.of(team2);
    when(teamRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    assertSame(team2, teamService.findById(1L));
    verify(teamRepository).findById(Mockito.<Long>any());
  }
}

