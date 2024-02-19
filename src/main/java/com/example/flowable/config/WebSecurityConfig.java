package com.example.flowable.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  // Configure in-memory authentication
  @Bean
  public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
    var user = User.withUsername("user")
            .password(passwordEncoder.encode("!QAZxsw2"))
            .roles("USER")
            .build();
    var admin = User.withUsername("admin")
            .password(passwordEncoder.encode("!QAZxsw2"))
            .roles("ADMIN")
            .build();
    var akceptujacy = User.withUsername("akceptujacy")
            .password(passwordEncoder.encode("!QAZxsw2"))
            .roles("USER")
            .build();
    return new InMemoryUserDetailsManager(user, admin, akceptujacy);
  }

  // Define the SecurityFilterChain bean
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .authorizeRequests(authz -> authz
                    .anyRequest().authenticated()
            )
            .formLogin(withDefaults())
            .httpBasic(withDefaults()); // If you want to enable basic authentication
    return http.build();
  }

  // Password encoder bean
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
