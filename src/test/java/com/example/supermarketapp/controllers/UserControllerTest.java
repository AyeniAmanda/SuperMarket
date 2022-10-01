package com.example.supermarketapp.controllers;

import com.example.supermarketapp.dto.LoginDto;
import com.example.supermarketapp.dto.UserDto;
import com.example.supermarketapp.entity.User;
import com.example.supermarketapp.reponse.ApiResponse;
import com.example.supermarketapp.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import static java.util.Calendar.SEPTEMBER;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserService userService;
    private User user;
    private LocalDateTime dateTime;


    @BeforeEach
    void setUp(){
        dateTime = LocalDateTime.of(2022,SEPTEMBER,7,4,20,40,3000);
        user = new User(1l , "amanda" , "ayeni" , "amanda@gmail.com" , "1234" );
    }

    @Test
    void register() throws Exception {
        UserDto userDTO = new UserDto( "amanda" , "ayeni" , "amanda@gmail.com" , "1234" );
        when(userService.signUp(any(UserDto.class))).thenReturn(registerResponse());
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/signup").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(userDTO)).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.status",is(true)))
                .andExpect(jsonPath("$.localDateTime",is(dateTime.toString())))
                .andExpect(jsonPath("$.data",is("successfully registered")));
    }

    private ApiResponse registerResponse(){
        ApiResponse registerResponse = new ApiResponse(true,dateTime,"successfully registered");
        return registerResponse;
    }

    @Test
    void login () throws Exception {
        LoginDto loginDto = new LoginDto("amanda@gmail.com","1234");
        when(userService.login(any(LoginDto.class))).thenReturn(loginResponse());
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/login").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(loginDto)).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.status",is(true)))
                .andExpect(jsonPath("$.localDateTime",is(dateTime.toString())))
                .andExpect(jsonPath("$.data",is("logged in Successfully")));
    }
    private ApiResponse loginResponse(){
        ApiResponse loginResponse = new ApiResponse(true,dateTime,"logged in Successfully");
        return loginResponse;
    }

    }
