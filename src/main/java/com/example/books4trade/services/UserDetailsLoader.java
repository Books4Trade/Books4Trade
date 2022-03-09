package com.example.books4trade.services;

import com.example.books4trade.models.User;
import com.example.books4trade.models.UserWithRoles;
import com.example.books4trade.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService {
    private final UserRepository users;

    public UserDetailsLoader(UserRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("No User Found for Username: "+username);
        }
        return new UserWithRoles(user);
    }
}