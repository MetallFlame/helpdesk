package com.helpdeskonboot.helpdesk.controller;

import com.helpdeskonboot.helpdesk.dto.UserDTO;
import com.helpdeskonboot.helpdesk.dto.auth.AuthRequest;
import com.helpdeskonboot.helpdesk.dto.auth.AuthResponse;
import com.helpdeskonboot.helpdesk.service.UserService;
import com.helpdeskonboot.helpdesk.util.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/authenticate")
public class AuthenticationController {

    private final UserService userService;
    private AuthenticationManager authenticationManager;
    private JWTUtil jwtTokenUtil;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JWTUtil jwtTokenUtil,
                                    UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<AuthResponse> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Name or password is wrong", e);
        }
        String jwt = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());
        AuthResponse response = new AuthResponse();
        response.setAuthToken(jwt);
        UserDTO authUser = userService.getDTOByEmail(authRequest.getName());
        response.setUserId(authUser.getId().toString());
        response.setUserRole(authUser.getRole().toString());
        response.setEmail(authUser.getEmail());
        return ResponseEntity.ok(response);
    }
}