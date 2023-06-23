package me.niallmurray.slipstream.security;


import me.niallmurray.slipstream.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;

public class UserDetailsImpl extends User implements UserDetails {
  @Serial
  private static final long serialVersionUID = -4381938875186527688L;

  public UserDetailsImpl() {
  }

  public UserDetailsImpl(User user) {
    this.setAuthorities(user.getAuthorities());
    this.setUserId(user.getUserId());
    this.setPassword(user.getPassword());
    this.setUsername(user.getUsername());
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
