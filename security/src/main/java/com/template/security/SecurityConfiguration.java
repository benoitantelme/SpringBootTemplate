package com.template.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        // hello is open
        .authorizeHttpRequests((requests) -> requests.requestMatchers("/hello").permitAll())
        .authorizeHttpRequests((requests) -> requests.anyRequest().authenticated())
        .formLogin((form) -> form.loginPage("/login").permitAll())
        .logout(LogoutConfigurer::permitAll);

    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails user =
        //    TODO: encoding
        User.withDefaultPasswordEncoder().username("user").password("user").roles("USER").build();

    return new InMemoryUserDetailsManager(user);
  }
}
