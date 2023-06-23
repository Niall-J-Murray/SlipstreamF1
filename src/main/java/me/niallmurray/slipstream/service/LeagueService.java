package me.niallmurray.slipstream.service;

import me.niallmurray.slipstream.domain.Driver;
import me.niallmurray.slipstream.domain.League;
import me.niallmurray.slipstream.repositories.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeagueService {
  @Autowired
  private LeagueRepository leagueRepository;
  @Autowired
  private DriverService driverService;

  public League createLeague() {
    deleteEmptyLeagues();
    League league = new League();
    league.setLeagueName("League # " + findNewestLeagueId());
    league.setTeams(new ArrayList<>());
    league.setIsTestLeague(false);
    league.setIsActive(false);
    league.setCreationTimestamp(
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yy HH:mm")));
    return leagueRepository.save(league);
  }

  public Long findNewestLeagueId() {
    List<League> allLeagues = leagueRepository.findAll();
    if (allLeagues.isEmpty()) {
      return 1L;
    }
    return allLeagues.get(allLeagues.size() - 1).getLeagueId() + 1;
  }

  public League findAvailableLeague() {
    List<League> allLeagues = findAll();

    if (allLeagues.isEmpty()) {
      return createLeague();
    }
    // If no empty leagues exist, get the latest created (last in list)
    League newestInactiveLeague = allLeagues.get(allLeagues.size() - 1);
    // Check if newest league is active, if true, return new league instead.
    if (Boolean.TRUE.equals(newestInactiveLeague.getIsActive())) {
      newestInactiveLeague = createLeague();
    }
    return newestInactiveLeague;
  }

  public int getCurrentPickNumber(League league) {
    List<Driver> undraftedDrivers = driverService.getUndraftedDrivers(league);
    if (league.getTeams().size() == 10 && undraftedDrivers.isEmpty()) {
      activateLeague(league);
    }
    return 21 - undraftedDrivers.size();
  }

  public void activateLeague(League league) {
    league.setIsActive(true);
    league.setActiveTimestamp(
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yy HH:mm")));
  }

  public void deleteEmptyLeagues() {
    List<League> allLeagues = leagueRepository.findAll();
    for (League league : allLeagues) {
      if (league.getTeams().isEmpty()) {
        leagueRepository.delete(league);
      }
    }
  }

  public List<League> findAll() {
    return leagueRepository.findAll();
  }

  public void save(League league) {
    leagueRepository.save(league);
  }
}
