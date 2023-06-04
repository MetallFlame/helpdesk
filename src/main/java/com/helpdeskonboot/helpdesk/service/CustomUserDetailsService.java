package com.helpdeskonboot.helpdesk.service;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        com.helpdeskonboot.helpdesk.model.User myUser = userService.getByEmail(email);
        UserDetails user = User.builder()
                .username(myUser.getEmail())
                .password(myUser.getPassword())
                .roles(myUser.getRole().toString())
                .build();
        return user;
    }
}
