package com.example.books4trade;

import com.example.books4trade.models.Role;
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
import java.util.*;

@Component
public class SecurityHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

       /* UserWithRoles userWithRoles = (UserWithRoles) authentication.getPrincipal();
        List<Role> usersRoles = userWithRoles.getRoles();
        List<String> listOfRoleNames = new LinkedList<>();
        for(Role role : usersRoles) {
            listOfRoleNames.add(role.getName());
        }
        String AllRoles = String.join(",", listOfRoleNames);*/
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        String redirectURL = request.getContextPath();

        if(roles.contains("BANNED")){
            redirectURL="/banned";
        } else if(roles.contains("ADMIN")){
            redirectURL = "/profile";
            //redirectURL="/admin";  UNCOMMENT THIS AND DELETE PREVIOUS LINE ONCE MAPPING/VIEW IS MADE
        } else if (roles.contains("USER")){
            redirectURL="/profile";
        }
        response.sendRedirect(redirectURL);
    }
}
