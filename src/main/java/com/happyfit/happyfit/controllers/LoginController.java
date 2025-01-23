package com.happyfit.happyfit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happyfit.happyfit.models.User;
import com.happyfit.happyfit.models.dto.UserLoginDto;
import com.happyfit.happyfit.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;
    
    @PostMapping
    public ResponseEntity<User> login(@Valid @RequestBody UserLoginDto loginDto) {
        String userEmail = loginDto.getEmail();
        String userPassword = loginDto.getPassword();

        User user = this.userService.findByEmail(userEmail);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        boolean isLoginCorrect = this.userService.verifyLogin(user, userPassword);

        if (isLoginCorrect) {
            return ResponseEntity.ok().body(user);
        }

        return ResponseEntity.badRequest().body(null);
    }
    
}
