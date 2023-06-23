package me.niallmurray.slipstream.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import me.niallmurray.slipstream.domain.User;
import me.niallmurray.slipstream.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("logoutSuccessHandlerImpl")
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
  @Autowired
  UserService userService;

  @Override
  public void onLogoutSuccess(HttpServletRequest request,
                              HttpServletResponse response,
                              Authentication authentication)
          throws IOException {
    HttpSession session = request.getSession();
    if (session != null) {
      userService.updateLastLogout(((User) authentication.getPrincipal()).getUserId());
      session.removeAttribute("user");
    }
    response.sendRedirect("/home?logout");
  }
}

