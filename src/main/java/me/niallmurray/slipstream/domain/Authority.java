package me.niallmurray.slipstream.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "authority")
public class Authority implements GrantedAuthority {
  @Serial
  private static final long serialVersionUID = -8123526131047887755L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long authId;
  @ManyToOne
  private User user;
  @Column(nullable = false)
  private String authority;

  @Override
  public int hashCode() {
    return Objects.hash(authId);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Authority other)) {
      return false;
    }
    return Objects.equals(authId, other.authId);
  }
}
