package me.niallmurray.slipstream.service;

import me.niallmurray.slipstream.domain.League;
import me.niallmurray.slipstream.domain.Team;
import me.niallmurray.slipstream.domain.User;
import me.niallmurray.slipstream.repositories.LeagueRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {LeagueService.class})
@ExtendWith(SpringExtension.class)
class LeagueServiceTest {
  @MockBean
  private DriverService driverService;
  @MockBean
  private LeagueRepository leagueRepository;
  @Autowired
  private LeagueService leagueService;

  /**
   * Method under test: {@link LeagueService#createLeague()}
   */
  @Test
  void testCreateLeague() {
    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setIsTestLeague(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());
    when(leagueRepository.save(Mockito.<League>any())).thenReturn(league);
    when(leagueRepository.findAll()).thenReturn(new ArrayList<>());
    assertSame(league, leagueService.createLeague());
    verify(leagueRepository).save(Mockito.<League>any());
    verify(leagueRepository, atLeast(1)).findAll();
  }

  @Test
  void testCreateLeague2() {
    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setIsTestLeague(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());

    League league2 = new League();
    league2.setActiveTimestamp("dd-MM-yy HH:mm");
    league2.setCreationTimestamp("dd-MM-yy HH:mm");
    league2.setIsActive(true);
    league2.setIsTestLeague(true);
    league2.setLeagueId(1L);
    league2.setLeagueName("dd-MM-yy HH:mm");
    league2.setTeams(new ArrayList<>());

    ArrayList<League> leagueList = new ArrayList<>();
    leagueList.add(league2);
    doNothing().when(leagueRepository).delete(Mockito.<League>any());
    when(leagueRepository.save(Mockito.<League>any())).thenReturn(league);
    when(leagueRepository.findAll()).thenReturn(leagueList);
    assertSame(league, leagueService.createLeague());
    verify(leagueRepository).save(Mockito.<League>any());
    verify(leagueRepository, atLeast(1)).findAll();
    verify(leagueRepository).delete(Mockito.<League>any());
  }

  @Test
  void testCreateLeague3() {
    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setIsTestLeague(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());

    League league2 = new League();
    league2.setActiveTimestamp("dd-MM-yy HH:mm");
    league2.setCreationTimestamp("dd-MM-yy HH:mm");
    league2.setIsActive(true);
    league2.setIsTestLeague(true);
    league2.setLeagueId(1L);
    league2.setLeagueName("dd-MM-yy HH:mm");
    league2.setTeams(new ArrayList<>());

    League league3 = new League();
    league3.setActiveTimestamp("dd-MM-yy HH:mm");
    league3.setCreationTimestamp("dd-MM-yy HH:mm");
    league3.setIsActive(true);
    league3.setIsTestLeague(true);
    league3.setLeagueId(1L);
    league3.setLeagueName("dd-MM-yy HH:mm");
    league3.setTeams(new ArrayList<>());

    User user = new User();
    user.setAuthorities(new HashSet<>());
    user.setEmail("jane.doe@example.org");
    user.setIsTestUser(true);
    user.setLastLogout("dd-MM-yy HH:mm");
    user.setPassword("iloveyou");
    user.setTeam(new Team());
    user.setUserId(1L);
    user.setUsername("janedoe");

    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setIsTestTeam(true);
    team.setLeague(league3);
    team.setRanking(1);
    team.setSecondPickNumber(10);
    team.setStartingPoints(10.0d);
    team.setTeamId(1L);
    team.setTeamName("dd-MM-yy HH:mm");
    team.setTeamPoints(10.0d);
    team.setUser(user);

    User user2 = new User();
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setIsTestUser(true);
    user2.setLastLogout("dd-MM-yy HH:mm");
    user2.setPassword("iloveyou");
    user2.setTeam(team);
    user2.setUserId(1L);
    user2.setUsername("janedoe");

    Team team2 = new Team();
    team2.setDrivers(new ArrayList<>());
    team2.setFirstPickNumber(10);
    team2.setIsTestTeam(true);
    team2.setLeague(league2);
    team2.setRanking(1);
    team2.setSecondPickNumber(10);
    team2.setStartingPoints(10.0d);
    team2.setTeamId(1L);
    team2.setTeamName("dd-MM-yy HH:mm");
    team2.setTeamPoints(10.0d);
    team2.setUser(user2);

    ArrayList<Team> teams = new ArrayList<>();
    teams.add(team2);

    League league4 = new League();
    league4.setActiveTimestamp("dd-MM-yy HH:mm");
    league4.setCreationTimestamp("dd-MM-yy HH:mm");
    league4.setIsActive(true);
    league4.setIsTestLeague(true);
    league4.setLeagueId(1L);
    league4.setLeagueName("dd-MM-yy HH:mm");
    league4.setTeams(teams);

    ArrayList<League> leagueList = new ArrayList<>();
    leagueList.add(league4);
    doNothing().when(leagueRepository).delete(Mockito.<League>any());
    when(leagueRepository.save(Mockito.<League>any())).thenReturn(league);
    when(leagueRepository.findAll()).thenReturn(leagueList);
    assertSame(league, leagueService.createLeague());
    verify(leagueRepository).save(Mockito.<League>any());
    verify(leagueRepository, atLeast(1)).findAll();
  }

  @Test
  void testFindNewestLeagueId() {
    when(leagueRepository.findAll()).thenReturn(new ArrayList<>());
    assertEquals(1L, leagueService.findNewestLeagueId().longValue());
    verify(leagueRepository).findAll();
  }

  @Test
  void testFindNewestLeagueId2() {
    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setIsTestLeague(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());

    ArrayList<League> leagueList = new ArrayList<>();
    leagueList.add(league);
    when(leagueRepository.findAll()).thenReturn(leagueList);
    assertEquals(2L, leagueService.findNewestLeagueId().longValue());
    verify(leagueRepository).findAll();
  }

  @Test
  void testFindAvailableLeague() {
    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setIsTestLeague(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());
    when(leagueRepository.save(Mockito.<League>any())).thenReturn(league);
    when(leagueRepository.findAll()).thenReturn(new ArrayList<>());
    assertSame(league, leagueService.findAvailableLeague());
    verify(leagueRepository).save(Mockito.<League>any());
    verify(leagueRepository, atLeast(1)).findAll();
  }

  @Test
  void testFindAvailableLeague2() {
    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setIsTestLeague(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());

    League league2 = new League();
    league2.setActiveTimestamp("dd-MM-yy HH:mm");
    league2.setCreationTimestamp("dd-MM-yy HH:mm");
    league2.setIsActive(true);
    league2.setIsTestLeague(true);
    league2.setLeagueId(1L);
    league2.setLeagueName("dd-MM-yy HH:mm");
    league2.setTeams(new ArrayList<>());

    ArrayList<League> leagueList = new ArrayList<>();
    leagueList.add(league2);
    doNothing().when(leagueRepository).delete(Mockito.<League>any());
    when(leagueRepository.save(Mockito.<League>any())).thenReturn(league);
    when(leagueRepository.findAll()).thenReturn(leagueList);
    assertSame(league, leagueService.findAvailableLeague());
    verify(leagueRepository).save(Mockito.<League>any());
    verify(leagueRepository, atLeast(1)).findAll();
    verify(leagueRepository).delete(Mockito.<League>any());
  }

  @Test
  void testFindAvailableLeague3() {
    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setIsTestLeague(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());
    League league2 = mock(League.class);
    when(league2.getIsActive()).thenReturn(false);
    doNothing().when(league2).setActiveTimestamp(Mockito.<String>any());
    doNothing().when(league2).setCreationTimestamp(Mockito.<String>any());
    doNothing().when(league2).setIsActive(Mockito.<Boolean>any());
    doNothing().when(league2).setIsTestLeague(Mockito.<Boolean>any());
    doNothing().when(league2).setLeagueId(Mockito.<Long>any());
    doNothing().when(league2).setLeagueName(Mockito.<String>any());
    doNothing().when(league2).setTeams(Mockito.<List<Team>>any());
    league2.setActiveTimestamp("dd-MM-yy HH:mm");
    league2.setCreationTimestamp("dd-MM-yy HH:mm");
    league2.setIsActive(true);
    league2.setIsTestLeague(true);
    league2.setLeagueId(1L);
    league2.setLeagueName("dd-MM-yy HH:mm");
    league2.setTeams(new ArrayList<>());

    ArrayList<League> leagueList = new ArrayList<>();
    leagueList.add(league2);
    doNothing().when(leagueRepository).delete(Mockito.<League>any());
    when(leagueRepository.save(Mockito.<League>any())).thenReturn(league);
    when(leagueRepository.findAll()).thenReturn(leagueList);
    leagueService.findAvailableLeague();
    verify(leagueRepository).findAll();
    verify(league2).getIsActive();
    verify(league2).setActiveTimestamp(Mockito.<String>any());
    verify(league2).setCreationTimestamp(Mockito.<String>any());
    verify(league2).setIsActive(Mockito.<Boolean>any());
    verify(league2).setIsTestLeague(Mockito.<Boolean>any());
    verify(league2).setLeagueId(Mockito.<Long>any());
    verify(league2).setLeagueName(Mockito.<String>any());
    verify(league2).setTeams(Mockito.<List<Team>>any());
  }

  private static League createNewLeague(ArrayList<Team> teams) {
    League league3 = new League();
    league3.setActiveTimestamp("Active Timestamp");
    league3.setCreationTimestamp("Creation Timestamp");
    league3.setIsActive(true);
    league3.setLeagueId(1L);
    league3.setLeagueName("League Name");
    league3.setTeams(teams);
    return league3;
  }

  private static League createNewLeague() {
    return createNewLeague(new ArrayList<>());
  }


  @Test
  void testGetCurrentPickNumber() {
    when(driverService.getUndraftedDrivers(Mockito.any())).thenReturn(new ArrayList<>());

    League league = createNewLeague();
    assertEquals(21, leagueService.getCurrentPickNumber(league));
    verify(driverService).getUndraftedDrivers(Mockito.any());
  }

  @Test
  void testActivateLeague() {
    League league = createNewLeague();
    leagueService.activateLeague(league);
    assertTrue(league.getIsActive());
  }

  @Test
  void testActivateLeague2() {
    League league = mock(League.class);
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
    leagueService.activateLeague(league);
    verify(league, atLeast(1)).setActiveTimestamp(Mockito.any());
    verify(league).setCreationTimestamp(Mockito.any());
    verify(league, atLeast(1)).setIsActive(Mockito.<Boolean>any());
    verify(league).setLeagueId(Mockito.<Long>any());
    verify(league).setLeagueName(Mockito.any());
    verify(league).setTeams(Mockito.any());
  }

  @Test
  void testDeleteEmptyLeagues() {
    when(leagueRepository.findAll()).thenReturn(new ArrayList<>());
    leagueService.deleteEmptyLeagues();
    verify(leagueRepository).findAll();
    assertTrue(leagueService.findAll().isEmpty());
  }

  @Test
  void testDeleteEmptyLeagues2() {
    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setIsTestLeague(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());

    ArrayList<League> leagueList = new ArrayList<>();
    leagueList.add(league);
    doNothing().when(leagueRepository).delete(Mockito.<League>any());
    when(leagueRepository.findAll()).thenReturn(leagueList);
    leagueService.deleteEmptyLeagues();
    verify(leagueRepository).findAll();
    verify(leagueRepository).delete(Mockito.<League>any());
    assertEquals(1, leagueService.findAll().size());
  }

  @Test
  void testDeleteEmptyLeagues3() {
    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setIsTestLeague(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    league.setTeams(new ArrayList<>());

    League league2 = new League();
    league2.setActiveTimestamp("Active Timestamp");
    league2.setCreationTimestamp("Creation Timestamp");
    league2.setIsActive(true);
    league2.setIsTestLeague(true);
    league2.setLeagueId(1L);
    league2.setLeagueName("League Name");
    league2.setTeams(new ArrayList<>());

    User user = new User();
    user.setAuthorities(new HashSet<>());
    user.setEmail("jane.doe@example.org");
    user.setIsTestUser(true);
    user.setLastLogout("Last Logout");
    user.setPassword("iloveyou");
    user.setTeam(new Team());
    user.setUserId(1L);
    user.setUsername("janedoe");

    Team team = new Team();
    team.setDrivers(new ArrayList<>());
    team.setFirstPickNumber(10);
    team.setIsTestTeam(true);
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
    user2.setIsTestUser(true);
    user2.setLastLogout("Last Logout");
    user2.setPassword("iloveyou");
    user2.setTeam(team);
    user2.setUserId(1L);
    user2.setUsername("janedoe");

    Team team2 = new Team();
    team2.setDrivers(new ArrayList<>());
    team2.setFirstPickNumber(10);
    team2.setIsTestTeam(true);
    team2.setLeague(league);
    team2.setRanking(1);
    team2.setSecondPickNumber(10);
    team2.setStartingPoints(10.0d);
    team2.setTeamId(1L);
    team2.setTeamName("Team Name");
    team2.setTeamPoints(10.0d);
    team2.setUser(user2);

    ArrayList<Team> teams = new ArrayList<>();
    teams.add(team2);

    League league3 = new League();
    league3.setActiveTimestamp("Active Timestamp");
    league3.setCreationTimestamp("Creation Timestamp");
    league3.setIsActive(true);
    league3.setIsTestLeague(true);
    league3.setLeagueId(1L);
    league3.setLeagueName("League Name");
    league3.setTeams(teams);

    ArrayList<League> leagueList = new ArrayList<>();
    leagueList.add(league3);
    doNothing().when(leagueRepository).delete(Mockito.<League>any());
    when(leagueRepository.findAll()).thenReturn(leagueList);
    leagueService.deleteEmptyLeagues();
    verify(leagueRepository).findAll();
    assertEquals(1, leagueService.findAll().size());
  }

  @Test
  void testFindAll() {
    ArrayList<League> leagueList = new ArrayList<>();
    when(leagueRepository.findAll()).thenReturn(leagueList);
    List<League> actualFindAllResult = leagueService.findAll();
    assertSame(leagueList, actualFindAllResult);
    assertTrue(actualFindAllResult.isEmpty());
    verify(leagueRepository).findAll();
  }

  @Test
  void testSave() {
    League league = new League();
    league.setActiveTimestamp("Active Timestamp");
    league.setCreationTimestamp("Creation Timestamp");
    league.setIsActive(true);
    league.setLeagueId(1L);
    league.setLeagueName("League Name");
    ArrayList<Team> teams = new ArrayList<>();
    league.setTeams(teams);
    when(leagueRepository.save(Mockito.any())).thenReturn(league);

    League league2 = new League();
    league2.setActiveTimestamp("Active Timestamp");
    league2.setCreationTimestamp("Creation Timestamp");
    league2.setIsActive(true);
    league2.setLeagueId(1L);
    league2.setLeagueName("League Name");
    league2.setTeams(new ArrayList<>());
    leagueService.save(league2);
    verify(leagueRepository).save(Mockito.any());
    assertEquals("Active Timestamp", league2.getActiveTimestamp());
    assertEquals(teams, league2.getTeams());
    assertEquals("League Name", league2.getLeagueName());
    assertEquals(1L, league2.getLeagueId().longValue());
    assertTrue(league2.getIsActive());
    assertEquals("Creation Timestamp", league2.getCreationTimestamp());
    assertTrue(leagueService.findAll().isEmpty());
  }
}

