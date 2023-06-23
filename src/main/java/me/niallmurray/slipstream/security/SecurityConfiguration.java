package me.niallmurray.slipstream.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

  @Autowired
  private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;
  @Autowired
  private LogoutSuccessHandlerImpl logoutSuccessHandler;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .authorizeHttpRequests()
            .requestMatchers("/admin/**")
            .hasRole("ADMIN")
            .requestMatchers("/dashboard/**", "/confirm_logout/**", "/logout/**")
            .hasAnyRole("USER", "ADMIN")
            .requestMatchers("/", "/f1/", "/home/**", "/login", "/perform_login/**", "/register", "/css/**", "/js/**", "/images/**", "/plugins/**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .successHandler(authenticationSuccessHandler)
            .failureUrl("/login?error=true")
            .and()
            .logout()
            .logoutUrl("/confirm_logout")
            .logoutSuccessHandler(logoutSuccessHandler)
            .deleteCookies("JSESSIONID");
    return http.build();
  }
}