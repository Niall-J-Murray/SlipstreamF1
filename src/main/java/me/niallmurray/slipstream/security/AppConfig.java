package me.niallmurray.slipstream.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  @Bean
  public me.niallmurray.slipstream.security.ActiveUserStore activeUserStore() {
    return new ActiveUserStore();
  }

}

