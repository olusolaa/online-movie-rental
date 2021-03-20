/*
package com.olusola.videorental.controller;

import com.olusola.videorental.dtos.user.LoginRequest;
import com.olusola.videorental.dtos.user.UserSignUpRequest;
import com.olusola.videorental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public class AuthController {

    @Autowired
    UserService UserService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return UserService.loginUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserSignUpRequest signUpRequest) {
        return UserService.createUser(signUpRequest);

    }
}
*/
