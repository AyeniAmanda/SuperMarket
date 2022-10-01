package com.example.supermarketapp.service;

import com.example.supermarketapp.dto.LoginDto;
import com.example.supermarketapp.dto.UserDto;
import com.example.supermarketapp.reponse.ApiResponse;

public interface UserService {
    ApiResponse signUp(UserDto userDto);



    ApiResponse login(LoginDto loginDto);

}
