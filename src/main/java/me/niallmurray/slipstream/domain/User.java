package me.niallmurray.slipstream.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;
  @Column(nullable = false, unique = true)
  private String username;
  @Column()
  private Boolean isTestUser;
  @Column(nullable = false, unique = true)
  private String email;
  @Column()
  private Integer emailsReceived;
  @Column(nullable = false)
  private String password;
  @OneToMany(fetch = FetchType.LAZY,
          cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},
          mappedBy = "user")
  private Set<Authority> authorities = new HashSet<>();
  @Column()
  private String lastLogout;
  @JoinColumn(name = "user_id")
  @OneToOne(mappedBy = "user",
          cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},
          orphanRemoval = true)
  private Team team;
  // For simplicity, each user has only one team for now.
  // This means no need for creating separate leagues for users with multiple teams.
  // Features for multiple teams/leagues per user, and/or drivers per team could be added eventually.

  @Override
  public String toString() {
    return "User:" +
            " userId= " + userId +
            ", username= '" + username + '\'' +
            ", email= '" + email + '\'' +
            ", lastLogout= " + lastLogout;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User user)) return false;
    return Objects.equals(userId, user.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId);
  }
}
