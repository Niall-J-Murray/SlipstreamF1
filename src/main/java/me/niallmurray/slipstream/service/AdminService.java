package me.niallmurray.slipstream.service;

import me.niallmurray.slipstream.domain.User;
import me.niallmurray.slipstream.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

  @Autowired
  private UserRepository userRepository;

  @Secured({"ROLE_ADMIN"})
  public List<User> getAllUserAccounts() {
    return userRepository.findAll();
  }

}
