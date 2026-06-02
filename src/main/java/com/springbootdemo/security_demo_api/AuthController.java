package com.springbootdemo.security_demo_api;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.AuthenticationException;

@RestController
public class AuthController {

    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    public AuthController(JwtService jwtService,
                          AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/authenticate")
    public String login(@RequestBody LoginRequest request) {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(),
                                    request.getPassword()
                            )
                    );

            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(request.getUsername());
            }

            return "Invalid login";

        } catch (AuthenticationException e) {
            return "Invalid username or password";
        }
    }
}
