package me.niallmurray.slipstream.service;

import me.niallmurray.slipstream.domain.League;
import me.niallmurray.slipstream.repositories.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeagueService {
  @Autowired
  LeagueRepository leagueRepository;

  public League createLeague() {
    League league = new League();
    league.setLeagueName("League # " + (leagueRepository.findAll().size() + 1));
    league.setTeams(new ArrayList<>());
    league.setCreationTime(LocalDateTime.now());
    return leagueRepository.save(league);
  }

  public League findNewestLeague() {
    List<League> allLeagues = leagueRepository.findAll();
    if (allLeagues.size() < 1) {
      return createLeague();
    }
    // If leagues exist, get the latest created (last in list)
    return allLeagues.get(allLeagues.size() - 1);
  }

  public List<League> findAll() {
    return leagueRepository.findAll();
  }
}
