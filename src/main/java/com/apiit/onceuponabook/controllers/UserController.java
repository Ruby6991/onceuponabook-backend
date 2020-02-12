package com.apiit.onceuponabook.controllers;

import com.apiit.onceuponabook.models.User;
import com.apiit.onceuponabook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/Register")
    public ResponseEntity<Boolean> RegisterUser(@RequestBody User user){
        return userService.registerUser(user);
    }
}
