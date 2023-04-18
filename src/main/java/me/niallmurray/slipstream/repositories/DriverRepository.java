package me.niallmurray.slipstream.repositories;

import me.niallmurray.slipstream.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {

  Driver findByCarNumber(int carNumber);

  List<Driver> findAllByOrderByStandingAsc();

  @Modifying
  @Query("update Driver d set d.points = :points where d.shortName = :shortName")
  void updatePoints
          (@Param(value = "shortName") String shortName,
           @Param(value = "points") Double points);

  @Modifying
  @Query("update Driver d set d.standing = :standing where d.shortName = :shortName")
  void updateStandings
          (@Param(value = "shortName") String shortName,
           @Param(value = "standing") Integer standing);
}