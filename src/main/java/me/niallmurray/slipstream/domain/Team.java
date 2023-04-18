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
@Table(name = "team",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"league_league_id", "firstPickNumber"}))
public class Team {
  @ManyToOne()
  League league;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long teamId;
  @JoinColumn(name = "user_id")
  @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
  //1-1 for now, see comment in User.class
  private User user;
  @Column()
  private Integer firstPickNumber;
  @Column()
  private Integer secondPickNumber;
  @Column()
  private String teamName;
  @Column()
  private Double startingPoints;
  @Column()
  private Double teamPoints;
  @Column()
  private Integer ranking;
  @Column()
  @ManyToMany(fetch = FetchType.LAZY,
          cascade = {CascadeType.MERGE, CascadeType.PERSIST},
          mappedBy = "teams")
  private List<Driver> drivers = new ArrayList<>(6);

  @Override
  public String toString() {
    return "Team:" +
            " teamId= " + teamId +
            ", user= " + user.getUsername() +
            ", teamName=' " + teamName + '\'' +
            ", firstPick= " + firstPickNumber +
            ", secondPick= " + secondPickNumber;

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Team team)) return false;
    return Objects.equals(teamId, team.teamId) && Objects.equals(teamName, team.teamName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(teamId, teamName);
  }
}
