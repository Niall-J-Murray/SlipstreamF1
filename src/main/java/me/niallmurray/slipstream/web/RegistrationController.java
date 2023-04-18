package me.niallmurray.slipstream.web;

import me.niallmurray.slipstream.domain.User;
import me.niallmurray.slipstream.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

  @Autowired
  private UserService userService;

  @GetMapping("/register")
  public String getRegister(ModelMap model) {
    model.addAttribute("user", new User());
    return "register";
  }


  @PostMapping("/register")
  public String postCreateUser(ModelMap model, User user) {

    if (userService.usernameExists(user.getUsername())) {
      model.addAttribute("userExists", "Username taken");
      return "register";
    }

    if (userService.emailExists(user.getEmail())) {
      model.addAttribute("emailExists", "Email already registered");
      return "register";
    }

    if (user.getPassword().length() < 6) {
      model.addAttribute("badPassword", "Password cannot be less than 6 characters");
      return "register";
    }
    userService.createUser(user);
    return "redirect:/login";
  }
}
