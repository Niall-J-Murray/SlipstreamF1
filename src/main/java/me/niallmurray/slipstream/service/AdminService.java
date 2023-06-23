package me.niallmurray.slipstream.service;

import me.niallmurray.slipstream.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
  @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
  @Autowired
  private UserService userService;

  @Secured({"ROLE_ADMIN"})
  public List<User> getAllUserAccounts() {
    return userService.findAll();
  }
}
