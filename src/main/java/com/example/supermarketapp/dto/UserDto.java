package com.example.supermarketapp.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto implements Serializable {
    private String firstName;

    private String lastName;


    private String email;

    private String password;
}
