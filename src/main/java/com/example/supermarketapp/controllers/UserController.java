package com.example.supermarketapp.controllers;

import com.example.supermarketapp.dto.LoginDto;
import com.example.supermarketapp.dto.UserDto;
import com.example.supermarketapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUp(@RequestBody UserDto user) {
        return new ResponseEntity<>(userService.signUp(user), HttpStatus.CREATED);
    }


    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(userService.login(loginDto),HttpStatus.OK);

    }



}
