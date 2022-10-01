package com.example.supermarketapp.service.serviceImpl;

import com.example.supermarketapp.dto.LoginDto;
import com.example.supermarketapp.dto.UserDto;
import com.example.supermarketapp.entity.User;
import com.example.supermarketapp.exception.UserNotFoundException;
import com.example.supermarketapp.reponse.ApiResponse;
import com.example.supermarketapp.repository.UserRepository;
import com.example.supermarketapp.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ApiResponse signUp(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);


        return new ApiResponse<>(true, LocalDateTime.now(), "Successfully registered");
    }


    @Override
    public ApiResponse login(LoginDto loginDto) {
        User user = userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword()).orElseThrow(()->new UserNotFoundException("Invalid user name or password"));
        return new ApiResponse(true, LocalDateTime.now(), "Successfully logged in");

    }



}
