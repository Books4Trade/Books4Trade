package com.example.books4trade.services;

import com.example.books4trade.models.Role;
import com.example.books4trade.models.User;
import com.example.books4trade.models.UserWithRoles;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.*;

@Component
public class SecurityHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        User user = (User) authentication.getPrincipal();
        long id = user.getId();
        System.out.println("User auth success for: " + id);
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        String redirectURL = request.getContextPath();

        if(roles.contains("BANNED")){
            redirectURL="/banned";
        } else if(roles.contains("ADMIN")){
            redirectURL = "/admin";
        } else if(roles.contains("USER")) {
            redirectURL = "/profile";
        } else if(roles.contains("INACTIVE")){
            redirectURL="/users/activate";
        }
        response.sendRedirect(redirectURL);
    }
}
