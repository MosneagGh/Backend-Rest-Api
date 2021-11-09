package com.marketplace.backend.controller;

import com.marketplace.backend.model.LoginRequest;
import com.marketplace.backend.model.RegisterRequest;
import com.marketplace.backend.model.User;
import com.marketplace.backend.service.UserService;
import com.marketplace.backend.security.JwtUtil;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "User Controller")
@RestController
@RequestMapping(path = "/user")
@AllArgsConstructor
@Validated

public class UserController {
    @Autowired
    private final JwtUtil jwtUtil;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody @Valid RegisterRequest request) {
        return userService.register(request);

    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody @Valid LoginRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("Bad credential");
        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }

}
