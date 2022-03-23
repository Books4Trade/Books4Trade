package com.example.books4trade;

import com.example.books4trade.services.SecurityHandler;
import com.example.books4trade.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
                        // Registration, Banned Pages - Public
                        "/", "/banned", "/register", "/about",
                        // Book Mappings, Inc Search - Public
                        "/books", "/books/{id}", "/books/search", "/books/search/api",
                        // Reviews Mappings - Views- Public
                        "/reviews", "/reviews/search", "/reviews/{id}", "/reviews/book/{id}",
                        // Owned Books Mappings - Views - Public
                        "/books/{id}/copies", "/books/{id}/copies/{id}",
                        // Users and Trades - Views - Public
                        "/users","/users/{id}","/trades"
                        )
                .permitAll()
                .and().authorizeRequests()
                .antMatchers(  "/users/activate")
                .authenticated()
                /* USERS - Pages that require authentication with Authority-Role "USER" */
                .and().authorizeRequests()
                .antMatchers(
                        //  Profile, Edit Profile, Reset Password
                        "/profile", "/profile/edit", "/profile/passwordreset",
                        // Book Creation, Reading
                        "/books/create", "/books/read/{id}",

                        // Add, Edit, Delete an Owned Book - Users Only
                        "/books/{id}/addcopy", "/books/{id}/copies/{copyid}/delete", "/books/{id}/copies/{copyid}/edit",
                        "/books/{id}/copies/{copyid}/delete",
                        // Add, Edit, Delete a Review - Users Only
                        "/books/{id}/createreview","/books/{id}/addreview","/reviews/{id}/edit", "/reviews/{id}/delete"

                ).hasAuthority("USER")
                // Any Unspecified Mapping is available to ADMIN
                .and().authorizeRequests().anyRequest().hasAuthority("ADMIN");
    }
}
