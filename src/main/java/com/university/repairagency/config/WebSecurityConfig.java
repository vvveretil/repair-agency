package com.university.repairagency.config;

import com.university.repairagency.role.Role;
import com.university.repairagency.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.cors().disable().csrf().disable()
                .authorizeRequests()

                .antMatchers(HttpMethod.DELETE, "/requests/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
                .antMatchers(HttpMethod.GET, "/requests/**").hasAnyRole(Role.ADMIN.name(), Role.MASTER.name(), Role.USER.name())
                .antMatchers(HttpMethod.POST, "/requests/**").hasAnyRole(Role.USER.name())

                .antMatchers("/profile/**").hasAnyRole(Role.ADMIN.name(), Role.MASTER.name(), Role.USER.name())
                .antMatchers("/users/**").hasRole(Role.ADMIN.name())
                .antMatchers("/", "/about", "/contacts", "/login", "/registration", "/css/**","/js/**").permitAll()

                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .defaultSuccessUrl("/profile")
                .permitAll()

                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()

                .and()
                .exceptionHandling()
                .accessDeniedPage("/forbidden")

                .and()
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return userService::findByUsername;
    }

}
