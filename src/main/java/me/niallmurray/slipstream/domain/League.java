package me.niallmurray.slipstream.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "league")
public class League {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long leagueId;
  @Column(nullable = false, unique = true)
  private String leagueName;
  @Column()
  private String creationTime;
  @ManyToMany(
          fetch = FetchType.LAZY,
          cascade = {CascadeType.MERGE})
  private List<Team> teams = new ArrayList<>();

  @Override
  public String toString() {
    return "Id= " + leagueId +
            ", Name= " + leagueName +
            ", Creation Time=" + creationTime +
            ", No. of Teams= " + teams.size();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    League league = (League) o;
    return Objects.equals(leagueId, league.leagueId) && Objects.equals(leagueName, league.leagueName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(leagueId, leagueName);
  }
}
