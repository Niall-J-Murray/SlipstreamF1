package me.niallmurray.slipstream.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import me.niallmurray.slipstream.domain.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("authenticationSuccessHandlerImpl")
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

  @Autowired
  ActiveUserStore activeUserStore;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Authentication authentication)
          throws IOException {
    HttpSession session = request.getSession(false);
    if (session != null) {
      LoggedUser user = new LoggedUser(authentication.getName(), activeUserStore);
      session.setAttribute("user", user);
    }
    response.sendRedirect("/dashboard");
  }
}
