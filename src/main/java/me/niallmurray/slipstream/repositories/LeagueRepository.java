package me.niallmurray.slipstream.repositories;

import me.niallmurray.slipstream.domain.League;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<League, Long> {

}
