package com.example.books4trade;

import com.example.books4trade.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsLoader usersLoader;
    private SecurityHandler authSuccessHandler;
    public SecurityConfiguration(UserDetailsLoader usersLoader, SecurityHandler authSuccessHandler){
        this.usersLoader = usersLoader;
        this.authSuccessHandler = authSuccessHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(usersLoader)
            .passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /* Login configuration */
                .formLogin().loginPage("/login").defaultSuccessUrl("/profile") // user's home page, it can be any URL
                .permitAll() // Anyone can go to the login page
                // AuthSuccess will redirect the user based on successful login based on roles
                .successHandler(authSuccessHandler)
                /* Logout configuration */
                .and().logout().logoutSuccessUrl("/login?logout")
                /* VISITORS - Pages that can be viewed without having to log in */
                .and().authorizeRequests()
                // Static File Paths
                .antMatchers( "/js/**","/img/**", "/css/**",
                        "/banned",
                        // Register and Root-Index Mappings
                        "/", "/register","/books", "/reviews","/trades",
                        "/books/{id}", "/books/{id}/copies",
                        "/reviews/{id}",
                        "/books/search", "/books/search/api"
                        ) // anyone can see the home and the Post-Index pages
                .permitAll()
                /* USERS - Pages that require authentication */
                .and().authorizeRequests()
                .antMatchers(
                        "/profile",
                        "/books/create","/books/{id}/copies/add",
                        "/books/{id}/createreview",
                        "/books/{id}/copies/add", "/books/{id}/copies/{copyid}", "/books/{id}/copies/{copyid}/delete", "/books/{id}/copies/{copyid}/edit"
                ).hasAuthority("USER")
                .and().authorizeRequests().anyRequest().hasAuthority("ADMIN");
    }
}
