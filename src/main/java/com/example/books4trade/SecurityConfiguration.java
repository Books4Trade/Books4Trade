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
                        "/", "/banned", "/register", "/about", "/session-invalidate",
                        // Book Mappings, Inc Search - Public
                        "/books",  "/books/search", "/books/search/api",
                        // Reviews Mappings - Views- Public
                        "/reviews", "/reviews/search", "/reviews/book/{id}",
                        // Owned Books Mappings - Views - Public

                        // Trades - Views - Public
                        "/trades"
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
                        "/books/{id}", "/books/create", "/books/{id}/read", "/books/{id}/unread",

                        // Add, Edit, Delete an Owned Book - Users Only
                        "/copies/all", "/books/{id}/copies", "/books/{id}/addcopy",
                        "/copies/{copyid}/edit", "/copies/{id}/delete",
                        // For Now, only Users can see a Review or Owned Copy- Individual Copy page due to User Info
                        "/reviews/{id}", "/copies/{copyid}",
                        // Add, Edit, Delete a Review - Users Only
                        "/books/{id}/addreview", "/books/{id}/submitreview","/reviews/{id}/edit", "/reviews/{id}/delete",
                        // View User Index, Search Users
                        "/users","/users/{id}", "/users/search"
                ).hasAuthority("USER")
                // Any Unspecified Mapping is available to ADMIN
                .and().authorizeRequests().anyRequest().hasAuthority("ADMIN"); // Admin
    }
}
