package me.niallmurray.slipstream;

import me.niallmurray.slipstream.domain.Driver;
import me.niallmurray.slipstream.domain.League;
import me.niallmurray.slipstream.domain.Team;
import me.niallmurray.slipstream.domain.User;
import me.niallmurray.slipstream.repositories.DriverRepository;
import me.niallmurray.slipstream.service.DriverService;
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

@ContextConfiguration(classes = {DriverService.class})
@ExtendWith(SpringExtension.class)
class DriverServiceTest {
  @MockBean
  private DriverRepository driverRepository;
  @Autowired
  private DriverService driverService;

  private static User createNewUser(Team team) {
    User user2 = new User();
    user2.setAuthorities(new HashSet<>());
    user2.setEmail("jane.doe@example.org");
    user2.setLastLogout("Last Logout");
    user2.setPassword("password");
    user2.setTeam(team);
    user2.setUserId(1L);
    user2.setUsername("janedoe");
    return user2;
  }

  private static User createNewUser() {
    return createNewUser(new Team());
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

  private static Team createNewTeam(League league2, User user) {
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
    return team;
  }

  private static Driver createNewDriver() {
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
    return driver;
  }

  @Test
  void testMapDTOToDrivers() {
    assertTrue(driverService.mapDTOToDrivers(new ArrayList<>()).isEmpty());
  }

  @Test
  void testAddDrivers() {
    Driver driver = createNewDriver();

    Driver driver2 = createNewDriver();
    when(driverRepository.save(Mockito.any())).thenReturn(driver);
    when(driverRepository.findByCarNumber(anyInt())).thenReturn(driver2);

    Driver driver3 = createNewDriver();

    ArrayList<Driver> drivers = new ArrayList<>();
    drivers.add(driver3);
    driverService.addDrivers(drivers);
    verify(driverRepository).findByCarNumber(anyInt());
  }

  @Test
  void testUpdateDrivers() {
    doNothing().when(driverRepository).updatePoints(Mockito.any(), Mockito.<Double>any());
    doNothing().when(driverRepository).updateStandings(Mockito.any(), Mockito.<Integer>any());

    Driver driver = createNewDriver();

    ArrayList<Driver> updatedDrivers = new ArrayList<>();
    updatedDrivers.add(driver);
    driverService.updateDrivers(updatedDrivers);
    verify(driverRepository).updatePoints(Mockito.any(), Mockito.<Double>any());
    verify(driverRepository).updateStandings(Mockito.any(), Mockito.<Integer>any());
  }

  @Test
  void testSortDriversStanding() {
    ArrayList<Driver> driverList = new ArrayList<>();
    when(driverRepository.findAllByOrderByStandingAsc()).thenReturn(driverList);
    List<Driver> actualSortDriversStandingResult = driverService.sortDriversStanding();
    assertSame(driverList, actualSortDriversStandingResult);
    assertTrue(actualSortDriversStandingResult.isEmpty());
    verify(driverRepository).findAllByOrderByStandingAsc();
  }

  @Test
  void testGetUndraftedDrivers() {
    ArrayList<Driver> driverList = new ArrayList<>();
    when(driverRepository.findAllByOrderByStandingAsc()).thenReturn(driverList);

    League league = createNewLeague();
    List<Driver> actualUndraftedDrivers = driverService.getUndraftedDrivers(league);
    assertSame(driverList, actualUndraftedDrivers);
    assertTrue(actualUndraftedDrivers.isEmpty());
    verify(driverRepository).findAllByOrderByStandingAsc();
  }

  @Test
  void testGetUndraftedDrivers2() {
    ArrayList<Driver> driverList = new ArrayList<>();
    when(driverRepository.findAllByOrderByStandingAsc()).thenReturn(driverList);

    League league = createNewLeague();
    League league2 = createNewLeague();
    User user = createNewUser();
    Team team = createNewTeam(league2, user);
    User user2 = createNewUser(team);
    Team team2 = createNewTeam(league, user2);

    ArrayList<Team> teams = new ArrayList<>();
    teams.add(team2);

    League league3 = createNewLeague(teams);
    List<Driver> actualUndraftedDrivers = driverService.getUndraftedDrivers(league3);
    assertSame(driverList, actualUndraftedDrivers);
    assertTrue(actualUndraftedDrivers.isEmpty());
    verify(driverRepository).findAllByOrderByStandingAsc();
  }

  @Test
  void testFindById() {
    Driver driver = createNewDriver();
    Optional<Driver> ofResult = Optional.of(driver);
    when(driverRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    assertSame(driver, driverService.findById(1L));
    verify(driverRepository).findById(Mockito.<Long>any());
  }

  @Test
  void testSave() {
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
    ArrayList<Team> teams = new ArrayList<>();
    driver.setTeams(teams);
    driver.setWikiPage("Wiki Page");
    when(driverRepository.save(Mockito.any())).thenReturn(driver);

    Driver driver2 = createNewDriver();
    driverService.save(driver2);
    verify(driverRepository).save(Mockito.any());
    assertEquals(10, driver2.getCarNumber().intValue());
    assertEquals("Doe", driver2.toString());
    assertEquals("Wiki Page", driver2.getWikiPage());
    assertEquals(teams, driver2.getTeams());
    assertEquals(1, driver2.getStanding().intValue());
    assertEquals("Short Name", driver2.getShortName());
    assertEquals(10.0d, driver2.getPoints().doubleValue());
    assertEquals("Nationality", driver2.getNationality());
    assertEquals("Jane", driver2.getFirstName());
    assertEquals(1L, driver2.getDriverId().longValue());
    assertEquals("2020-03-01", driver2.getDateOfBirth());
    assertEquals("Constructor", driver2.getConstructor());
  }
}

