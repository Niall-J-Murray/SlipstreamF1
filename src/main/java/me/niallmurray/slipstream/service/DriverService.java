package me.niallmurray.slipstream.service;

import jakarta.transaction.Transactional;
import me.niallmurray.slipstream.domain.Driver;
import me.niallmurray.slipstream.domain.League;
import me.niallmurray.slipstream.domain.Team;
import me.niallmurray.slipstream.dto.DriverStanding;
import me.niallmurray.slipstream.repositories.DriverRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DriverService {
  @Autowired
  private DriverRepository driverRepository;

  public List<Driver> mapDTOToDrivers(List<DriverStanding> allDriverStandings) {
    List<Driver> drivers = new ArrayList<>();
    ModelMapper modelMapper = new ModelMapper();

    for (DriverStanding driverStanding : allDriverStandings) {
      Driver driver = modelMapper.map(driverStanding.driverDTO, Driver.class);
      driver.setStanding(Integer.parseInt(driverStanding.position));
      driver.setPoints(Double.parseDouble(driverStanding.points));
      driver.setConstructor(driverStanding.constructors.get(0).name);
      drivers.add(driver);
    }
    return drivers;
  }

  public void addDrivers(List<Driver> drivers) {
    for (Driver driver : drivers) {
      // Set current champ to car #1
      if (driver.getCarNumber() == 33) {
        driver.setCarNumber(1);
      }
      if (driverRepository.findByCarNumber(driver.getCarNumber()) == null) {
        driverRepository.save(driver);
      }
    }
  }

  @Transactional
  public void updateDrivers(List<Driver> updatedDrivers) {
    for (Driver driver : updatedDrivers) {
      driverRepository.updatePoints(driver.getShortName(), driver.getPoints());
      driverRepository.updateStandings(driver.getShortName(), driver.getStanding());
    }
  }

  public List<Driver> sortDriversStanding() {
    return driverRepository.findAllByOrderByStandingAsc();
  }

  public List<Driver> getUndraftedDrivers(League league) {
    List<Driver> undraftedDrivers = driverRepository.findAllByOrderByStandingAsc();
    List<Team> teams = league.getTeams();
    for (Team team : teams) {
      List<Driver> drivers = team.getDrivers();
      undraftedDrivers.removeAll(drivers);
    }
    return undraftedDrivers;
  }

  public Driver findById(Long driverId) {
    return driverRepository.findById(driverId).orElse(null);
  }

  public void save(Driver driver) {
    driverRepository.save(driver);
  }

}
