package com.example.books4trade.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class UserWithRoles extends User implements UserDetails {

    private User user;

    public UserWithRoles(User user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = user.getRoles();
//        List<String> listOfRoleNames = new LinkedList<>();
//        for(Role role : roles) {
//            listOfRoleNames.add(role.getName());
//        }
//        String AllRoles = String.join(",", listOfRoleNames);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
      //  return AuthorityUtils.commaSeparatedStringToAuthorityList(AllRoles);
    }

    @Override
    public long getId(){return user.getId(); }
    @Override
    public String getPassword(){
        return user.getPassword();
    }
    @Override
    public String getUsername(){
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }
}
