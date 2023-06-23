package me.niallmurray.slipstream.service;

import me.niallmurray.slipstream.domain.League;
import me.niallmurray.slipstream.domain.Team;
import me.niallmurray.slipstream.domain.User;
import me.niallmurray.slipstream.repositories.UserRepository;
import me.niallmurray.slipstream.security.UserDetailsImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserDetailsServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserDetailsServiceImplTest {
  @Autowired
  private UserDetailsServiceImpl userDetailsServiceImpl;
  @MockBean
  private UserRepository userRepository;

  @Test
  void testLoadUserByUsername() throws UsernameNotFoundException {
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

    User user3 = new User();
    user3.setAuthorities(new HashSet<>());
    user3.setEmail("jane.doe@example.org");
    user3.setLastLogout("Last Logout");
    user3.setPassword("password");
    user3.setTeam(team2);
    user3.setUserId(1L);
    user3.setUsername("janedoe");
    when(userRepository.findByUsername(Mockito.any())).thenReturn(user3);
    UserDetails actualLoadUserByUsernameResult = userDetailsServiceImpl.loadUserByUsername("janedoe");
    assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
    assertEquals(1L, ((UserDetailsImpl) actualLoadUserByUsernameResult).getUserId().longValue());
    assertEquals("password", actualLoadUserByUsernameResult.getPassword());
    verify(userRepository).findByUsername(Mockito.any());
  }
}

