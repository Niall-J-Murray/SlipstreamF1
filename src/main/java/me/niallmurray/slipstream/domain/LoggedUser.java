package me.niallmurray.slipstream.domain;

import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;
import lombok.Getter;
import lombok.Setter;
import me.niallmurray.slipstream.security.ActiveUserStore;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Component
public class LoggedUser implements HttpSessionBindingListener, Serializable {

  @Serial
  private static final long serialVersionUID = 1L;
  private String username;
  private Boolean loggedIn;  // May be redundant
  private ActiveUserStore activeUserStore;

  public LoggedUser(String username, ActiveUserStore activeUserStore) {
    this.username = username;
    this.activeUserStore = activeUserStore;
  }

  public LoggedUser() {
  }

  @Override
  public void valueBound(HttpSessionBindingEvent event) {
    List<String> users = activeUserStore.getUsers();
    LoggedUser loggedUser = (LoggedUser) event.getValue();
    if (!users.contains(loggedUser.getUsername())) {
      users.add(loggedUser.getUsername());
      loggedUser.setLoggedIn(true);
    }
  }

  @Override
  public void valueUnbound(HttpSessionBindingEvent event) {
    List<String> users = activeUserStore.getUsers();
    LoggedUser loggedUser = (LoggedUser) event.getValue();
    users.remove(loggedUser.getUsername());
    loggedUser.setLoggedIn(false);
  }
}
