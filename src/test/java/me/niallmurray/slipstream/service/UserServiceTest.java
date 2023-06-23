package me.niallmurray.slipstream.service;

import me.niallmurray.slipstream.domain.Authority;
import me.niallmurray.slipstream.domain.League;
import me.niallmurray.slipstream.domain.Team;
import me.niallmurray.slipstream.domain.User;
import me.niallmurray.slipstream.repositories.UserRepository;
import me.niallmurray.slipstream.security.ActiveUserStore;
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

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
  @MockBean
  private ActiveUserStore activeUserStore;

  @MockBean
  private UserRepository userRepository;

  @Autowired
  private UserService userService;

  @Test
  void testCreateUser() {
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
    when(userRepository.save(Mockito.any())).thenReturn(user3);

    League league3 = new League();
    league3.setActiveTimestamp("Active Timestamp");
    league3.setCreationTimestamp("Creation Timestamp");
    league3.setIsActive(true);
    league3.setLeagueId(1L);
    league3.setLeagueName("League Name");
    league3.setTeams(new ArrayList<>());

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

    User user5 = new User();
    user5.setAuthorities(new HashSet<>());
    user5.setEmail("jane.doe@example.org");
    user5.setLastLogout("Last Logout");
    user5.setPassword("password");
    user5.setTeam(team4);
    user5.setUserId(1L);
    user5.setUsername("janedoe");
    userService.createUser(user5);
    verify(userRepository).save(Mockito.any());
    assertEquals(1, user5.getAuthorities().size());
    assertEquals("janedoe", user5.getUsername());
    assertEquals("0123-04-05T06:07", user5.getLastLogout());
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

    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setLeague(new League());
    team.setRanking(1);
    team.setSecondPickNumber(10);
    team.setStartingPoints(10.0d);
    team.setTeamId(1L);
    team.setTeamName("Team Name");
    team.setTeamPoints(10.0d);
    team.setUser(new User());

    User user = new User();
    user.setAuthorities(new HashSet<>());
    user.setEmail("jane.doe@example.org");
    user.setLastLogout("Last Logout");
    user.setPassword("password");
    user.setTeam(team);
    user.setUserId(1L);
    user.setUsername("janedoe");

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
    team2.setUser(user);

    User user2 = new User();
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setLastLogout("Last Logout");
    user2.setPassword("password");
    user2.setTeam(team2);
    user2.setUserId(1L);
    user2.setUsername("janedoe");
    Optional<User> ofResult = Optional.of(user2);
    when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    assertSame(user2, userService.findById(1L));
    verify(userRepository).findById(Mockito.<Long>any());
  }

  @Test
  void testUsernameExists() {
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
    assertTrue(userService.usernameExists("janedoe"));
    verify(userRepository).findByUsername(Mockito.any());
  }

  @Test
  void testEmailExists() {
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
    when(userRepository.findByEmail(Mockito.any())).thenReturn(user3);
    assertTrue(userService.emailExists("jane.doe@example.org"));
    verify(userRepository).findByEmail(Mockito.any());
  }

  @Test
  void testIsLoggedIn() {
    when(activeUserStore.getUsers()).thenReturn(new ArrayList<>());

    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());

    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setLeague(new League());
    team.setRanking(1);
    team.setSecondPickNumber(10);
    team.setStartingPoints(10.0d);
    team.setTeamId(1L);
    team.setTeamName("Team Name");
    team.setTeamPoints(10.0d);
    team.setUser(new User());

    User user = new User();
    user.setAuthorities(new HashSet<>());
    user.setEmail("jane.doe@example.org");
    user.setLastLogout("Last Logout");
    user.setPassword("password");
    user.setTeam(team);
    user.setUserId(1L);
    user.setUsername("janedoe");

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
    team2.setUser(user);

    User user2 = new User();
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setLastLogout("Last Logout");
    user2.setPassword("password");
    user2.setTeam(team2);
    user2.setUserId(1L);
    user2.setUsername("janedoe");
    assertFalse(userService.isLoggedIn(user2));
    verify(activeUserStore).getUsers();
  }

  @Test
  void testIsAdmin() {
    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());

    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setLeague(new League());
    team.setRanking(1);
    team.setSecondPickNumber(10);
    team.setStartingPoints(10.0d);
    team.setTeamId(1L);
    team.setTeamName("Team Name");
    team.setTeamPoints(10.0d);
    team.setUser(new User());

    User user = new User();
    user.setAuthorities(new HashSet<>());
    user.setEmail("jane.doe@example.org");
    user.setLastLogout("Last Logout");
    user.setPassword("password");
    user.setTeam(team);
    user.setUserId(1L);
    user.setUsername("janedoe");

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
    team2.setUser(user);

    User user2 = new User();
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setLastLogout("Last Logout");
    user2.setPassword("password");
    user2.setTeam(team2);
    user2.setUserId(1L);
    user2.setUsername("janedoe");
    assertFalse(userService.isAdmin(user2));
  }

  @Test
  void testIsAdmin2() {
    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());

    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setLeague(new League());
    team.setRanking(1);
    team.setSecondPickNumber(10);
    team.setStartingPoints(10.0d);
    team.setTeamId(1L);
    team.setTeamName("Team Name");
    team.setTeamPoints(10.0d);
    team.setUser(new User());

    User user = new User();
    user.setAuthorities(new HashSet<>());
    user.setEmail("jane.doe@example.org");
    user.setLastLogout("Last Logout");
    user.setPassword("password");
    user.setTeam(team);
    user.setUserId(1L);
    user.setUsername("janedoe");

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
    team2.setUser(user);
    User user2 = mock(User.class);
    when(user2.getAuthorities()).thenReturn(new HashSet<>());
    doNothing().when(user2).setAuthorities(Mockito.any());
    doNothing().when(user2).setEmail(Mockito.any());
    doNothing().when(user2).setLastLogout(Mockito.any());
    doNothing().when(user2).setPassword(Mockito.any());
    doNothing().when(user2).setTeam(Mockito.any());
    doNothing().when(user2).setUserId(Mockito.<Long>any());
    doNothing().when(user2).setUsername(Mockito.any());
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setLastLogout("Last Logout");
    user2.setPassword("password");
    user2.setTeam(team2);
    user2.setUserId(1L);
    user2.setUsername("janedoe");
    assertFalse(userService.isAdmin(user2));
    verify(user2).getAuthorities();
    verify(user2).setAuthorities(Mockito.any());
    verify(user2).setEmail(Mockito.any());
    verify(user2).setLastLogout(Mockito.any());
    verify(user2).setPassword(Mockito.any());
    verify(user2).setTeam(Mockito.any());
    verify(user2).setUserId(Mockito.<Long>any());
    verify(user2).setUsername(Mockito.any());
  }

  @Test
  void testIsAdmin3() {
    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());

    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setLeague(new League());
    team.setRanking(1);
    team.setSecondPickNumber(10);
    team.setStartingPoints(10.0d);
    team.setTeamId(1L);
    team.setTeamName("Team Name");
    team.setTeamPoints(10.0d);
    team.setUser(new User());

    User user = new User();
    user.setAuthorities(new HashSet<>());
    user.setEmail("jane.doe@example.org");
    user.setLastLogout("Last Logout");
    user.setPassword("password");
    user.setTeam(team);
    user.setUserId(1L);
    user.setUsername("janedoe");

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
    team2.setUser(user);

    League league2 = new League();
    league2.setActiveTimestamp("Active Timestamp");
    league2.setCreationTimestamp("Creation Timestamp");
    league2.setIsActive(true);
    league2.setLeagueId(1L);
    league2.setLeagueName("League Name");
    league2.setTeams(new ArrayList<>());

    User user2 = new User();
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setLastLogout("Last Logout");
    user2.setPassword("password");
    user2.setTeam(new Team());
    user2.setUserId(1L);
    user2.setUsername("janedoe");

    Team team3 = new Team();
    team3.setDrivers(new ArrayList<>());
    team3.setFirstPickNumber(10);
    team3.setLeague(league2);
    team3.setRanking(1);
    team3.setSecondPickNumber(10);
    team3.setStartingPoints(10.0d);
    team3.setTeamId(1L);
    team3.setTeamName("Team Name");
    team3.setTeamPoints(10.0d);
    team3.setUser(user2);

    User user3 = new User();
    user3.setAuthorities(new HashSet<>());
    user3.setEmail("jane.doe@example.org");
    user3.setLastLogout("Last Logout");
    user3.setPassword("password");
    user3.setTeam(team3);
    user3.setUserId(1L);
    user3.setUsername("janedoe");

    Authority authority = new Authority();
    authority.setAuthId(1L);
    authority.setAuthority("JaneDoe");
    authority.setUser(user3);

    HashSet<Authority> authoritySet = new HashSet<>();
    authoritySet.add(authority);
    User user4 = mock(User.class);
    when(user4.getAuthorities()).thenReturn(authoritySet);
    doNothing().when(user4).setAuthorities(Mockito.any());
    doNothing().when(user4).setEmail(Mockito.any());
    doNothing().when(user4).setLastLogout(Mockito.any());
    doNothing().when(user4).setPassword(Mockito.any());
    doNothing().when(user4).setTeam(Mockito.any());
    doNothing().when(user4).setUserId(Mockito.<Long>any());
    doNothing().when(user4).setUsername(Mockito.any());
    user4.setAuthorities(new HashSet<>());
    user4.setEmail("jane.doe@example.org");
    user4.setLastLogout("Last Logout");
    user4.setPassword("password");
    user4.setTeam(team2);
    user4.setUserId(1L);
    user4.setUsername("janedoe");
    assertFalse(userService.isAdmin(user4));
    verify(user4).getAuthorities();
    verify(user4).setAuthorities(Mockito.any());
    verify(user4).setEmail(Mockito.any());
    verify(user4).setLastLogout(Mockito.any());
    verify(user4).setPassword(Mockito.any());
    verify(user4).setTeam(Mockito.any());
    verify(user4).setUserId(Mockito.<Long>any());
    verify(user4).setUsername(Mockito.any());
  }

  @Test
  void testIsAdmin4() {
    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());

    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setLeague(new League());
    team.setRanking(1);
    team.setSecondPickNumber(10);
    team.setStartingPoints(10.0d);
    team.setTeamId(1L);
    team.setTeamName("Team Name");
    team.setTeamPoints(10.0d);
    team.setUser(new User());

    User user = new User();
    user.setAuthorities(new HashSet<>());
    user.setEmail("jane.doe@example.org");
    user.setLastLogout("Last Logout");
    user.setPassword("password");
    user.setTeam(team);
    user.setUserId(1L);
    user.setUsername("janedoe");

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
    team2.setUser(user);

    League league2 = new League();
    league2.setActiveTimestamp("Active Timestamp");
    league2.setCreationTimestamp("Creation Timestamp");
    league2.setIsActive(true);
    league2.setLeagueId(1L);
    league2.setLeagueName("League Name");
    league2.setTeams(new ArrayList<>());

    User user2 = new User();
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setLastLogout("Last Logout");
    user2.setPassword("password");
    user2.setTeam(new Team());
    user2.setUserId(1L);
    user2.setUsername("janedoe");

    Team team3 = new Team();
    team3.setDrivers(new ArrayList<>());
    team3.setFirstPickNumber(10);
    team3.setLeague(league2);
    team3.setRanking(1);
    team3.setSecondPickNumber(10);
    team3.setStartingPoints(10.0d);
    team3.setTeamId(1L);
    team3.setTeamName("Team Name");
    team3.setTeamPoints(10.0d);
    team3.setUser(user2);

    User user3 = new User();
    user3.setAuthorities(new HashSet<>());
    user3.setEmail("jane.doe@example.org");
    user3.setLastLogout("Last Logout");
    user3.setPassword("password");
    user3.setTeam(team3);
    user3.setUserId(1L);
    user3.setUsername("janedoe");

    Authority authority = new Authority();
    authority.setAuthId(1L);
    authority.setAuthority("JaneDoe");
    authority.setUser(user3);

    League league3 = new League();
    league3.setActiveTimestamp("JaneDoe");
    league3.setCreationTimestamp("JaneDoe");
    league3.setIsActive(false);
    league3.setLeagueId(2L);
    league3.setLeagueName("JaneDoe");
    league3.setTeams(new ArrayList<>());

    User user4 = new User();
    user4.setAuthorities(new HashSet<>());
    user4.setEmail("john.smith@example.org");
    user4.setLastLogout("JaneDoe");
    user4.setPassword("ROLE_ADMIN");
    user4.setTeam(new Team());
    user4.setUserId(2L);
    user4.setUsername("ROLE_ADMIN");

    Team team4 = new Team();
    team4.setDrivers(new ArrayList<>());
    team4.setFirstPickNumber(1);
    team4.setLeague(league3);
    team4.setRanking(0);
    team4.setSecondPickNumber(1);
    team4.setStartingPoints(0.5d);
    team4.setTeamId(2L);
    team4.setTeamName("JaneDoe");
    team4.setTeamPoints(0.5d);
    team4.setUser(user4);

    User user5 = new User();
    user5.setAuthorities(new HashSet<>());
    user5.setEmail("john.smith@example.org");
    user5.setLastLogout("JaneDoe");
    user5.setPassword("ROLE_ADMIN");
    user5.setTeam(team4);
    user5.setUserId(2L);
    user5.setUsername("ROLE_ADMIN");

    Authority authority2 = new Authority();
    authority2.setAuthId(2L);
    authority2.setAuthority("ROLE_ADMIN");
    authority2.setUser(user5);

    HashSet<Authority> authoritySet = new HashSet<>();
    authoritySet.add(authority2);
    authoritySet.add(authority);
    User user6 = mock(User.class);
    when(user6.getAuthorities()).thenReturn(authoritySet);
    doNothing().when(user6).setAuthorities(Mockito.any());
    doNothing().when(user6).setEmail(Mockito.any());
    doNothing().when(user6).setLastLogout(Mockito.any());
    doNothing().when(user6).setPassword(Mockito.any());
    doNothing().when(user6).setTeam(Mockito.any());
    doNothing().when(user6).setUserId(Mockito.<Long>any());
    doNothing().when(user6).setUsername(Mockito.any());
    user6.setAuthorities(new HashSet<>());
    user6.setEmail("jane.doe@example.org");
    user6.setLastLogout("Last Logout");
    user6.setPassword("password");
    user6.setTeam(team2);
    user6.setUserId(1L);
    user6.setUsername("janedoe");
    assertTrue(userService.isAdmin(user6));
    verify(user6).getAuthorities();
    verify(user6).setAuthorities(Mockito.any());
    verify(user6).setEmail(Mockito.any());
    verify(user6).setLastLogout(Mockito.any());
    verify(user6).setPassword(Mockito.any());
    verify(user6).setTeam(Mockito.any());
    verify(user6).setUserId(Mockito.<Long>any());
    verify(user6).setUsername(Mockito.any());
  }

  @Test
  void testUpdateLastLogout() {
    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());

    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setLeague(new League());
    team.setRanking(1);
    team.setSecondPickNumber(10);
    team.setStartingPoints(10.0d);
    team.setTeamId(1L);
    team.setTeamName("Team Name");
    team.setTeamPoints(10.0d);
    team.setUser(new User());

    User user = new User();
    user.setAuthorities(new HashSet<>());
    user.setEmail("jane.doe@example.org");
    user.setLastLogout("Last Logout");
    user.setPassword("password");
    user.setTeam(team);
    user.setUserId(1L);
    user.setUsername("janedoe");

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
    team2.setUser(user);

    User user2 = new User();
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setLastLogout("Last Logout");
    user2.setPassword("password");
    user2.setTeam(team2);
    user2.setUserId(1L);
    user2.setUsername("janedoe");
    Optional<User> ofResult = Optional.of(user2);

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
    team3.setLeague(league3);
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
    team4.setLeague(league2);
    team4.setRanking(1);
    team4.setSecondPickNumber(10);
    team4.setStartingPoints(10.0d);
    team4.setTeamId(1L);
    team4.setTeamName("Team Name");
    team4.setTeamPoints(10.0d);
    team4.setUser(user4);

    User user5 = new User();
    user5.setAuthorities(new HashSet<>());
    user5.setEmail("jane.doe@example.org");
    user5.setLastLogout("Last Logout");
    user5.setPassword("password");
    user5.setTeam(team4);
    user5.setUserId(1L);
    user5.setUsername("janedoe");
    when(userRepository.save(Mockito.any())).thenReturn(user5);
    when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    userService.updateLastLogout(1L);
    verify(userRepository).save(Mockito.any());
    verify(userRepository).findById(Mockito.<Long>any());
  }

  @Test
  void testSave() {
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
    when(userRepository.save(Mockito.any())).thenReturn(user3);

    League league3 = new League();
    league3.setActiveTimestamp("Active Timestamp");
    league3.setCreationTimestamp("Creation Timestamp");
    league3.setIsActive(true);
    league3.setLeagueId(1L);
    league3.setLeagueName("League Name");
    league3.setTeams(new ArrayList<>());

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

    User user5 = new User();
    user5.setAuthorities(new HashSet<>());
    user5.setEmail("jane.doe@example.org");
    user5.setLastLogout("Last Logout");
    user5.setPassword("password");
    user5.setTeam(team4);
    user5.setUserId(1L);
    user5.setUsername("janedoe");
    userService.save(user5);
    verify(userRepository).save(Mockito.any());
    assertTrue(user5.getAuthorities().isEmpty());
    assertEquals("janedoe", user5.getUsername());
    assertEquals(1L, user5.getUserId().longValue());
    assertEquals(team3, user5.getTeam());
    assertEquals("password", user5.getPassword());
    assertEquals("jane.doe@example.org", user5.getEmail());
    assertEquals("Last Logout", user5.getLastLogout());
  }

  @Test
  void testUpdateUser() {
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
    when(userRepository.save(Mockito.any())).thenReturn(user3);

    League league3 = new League();
    league3.setActiveTimestamp("Active Timestamp");
    league3.setCreationTimestamp("Creation Timestamp");
    league3.setIsActive(true);
    league3.setLeagueId(1L);
    league3.setLeagueName("League Name");
    league3.setTeams(new ArrayList<>());

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

    User user5 = new User();
    user5.setAuthorities(new HashSet<>());
    user5.setEmail("jane.doe@example.org");
    user5.setLastLogout("Last Logout");
    user5.setPassword("password");
    user5.setTeam(team4);
    user5.setUserId(1L);
    user5.setUsername("janedoe");
    userService.updateUser(user5);
    verify(userRepository).save(Mockito.any());
    assertTrue(user5.getAuthorities().isEmpty());
    assertEquals("janedoe", user5.getUsername());
    assertEquals(1L, user5.getUserId().longValue());
    assertEquals(team3, user5.getTeam());
    assertEquals("password", user5.getPassword());
    assertEquals("jane.doe@example.org", user5.getEmail());
    assertEquals("Last Logout", user5.getLastLogout());
  }

  @Test
  void testFindAll() {
    ArrayList<User> userList = new ArrayList<>();
    when(userRepository.findAll()).thenReturn(userList);
    List<User> actualFindAllResult = userService.findAll();
    assertSame(userList, actualFindAllResult);
    assertTrue(actualFindAllResult.isEmpty());
    verify(userRepository).findAll();
  }
}

