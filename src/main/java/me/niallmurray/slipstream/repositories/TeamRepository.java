package me.niallmurray.slipstream.repositories;

import me.niallmurray.slipstream.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
