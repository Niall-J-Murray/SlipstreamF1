package me.niallmurray.slipstream.service;

import me.niallmurray.slipstream.domain.Authority;
import me.niallmurray.slipstream.domain.User;
import me.niallmurray.slipstream.repositories.UserRepository;
import me.niallmurray.slipstream.security.ActiveUserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

  @Autowired
  ActiveUserStore activeUserStore;
  @Autowired
  private UserRepository userRepository;

  public void createUser(User user) {
    Authority authority = new Authority();
    authority.setUser(user);
    authority.setAuthority("ROLE_USER");

    user.getAuthorities().add(authority);
    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    // For new users first login and to check for unsuccessful logouts.
    user.setLastLogout(String.valueOf(LocalDateTime.of(666, 6, 6, 6, 6)));

    userRepository.save(user);
  }

  public User findById(Long userId) {
    Optional<User> findById = userRepository.findById(userId);
    return findById.orElse(null);
  }

  public boolean usernameExists(String username) {
    return userRepository.findByUsername(username) != null;
  }

  public boolean emailExists(String email) {
    return userRepository.findByEmail(email) != null;
  }

  public boolean isLoggedIn(User user) {
    if (user != null) {
      return activeUserStore.getUsers().contains(user.getUsername());
    }
    return false;
  }

  public boolean isAdmin(User user) {
    if (user != null) {
      Set<Authority> authorities = user.getAuthorities();
      for (Authority authority : authorities) {
        if (authority.getAuthority().equals("ROLE_ADMIN")) {
          return true;
        }
      }
    }
    return false;
  }

  public void updateLastLogout(Long userId) {
    User user = findById(userId);
    user.setLastLogout(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yy HH:mm")));
    userRepository.save(user);
  }
}