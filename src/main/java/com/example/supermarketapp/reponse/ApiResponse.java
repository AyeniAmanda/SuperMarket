package com.example.supermarketapp.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse <T>{
    private boolean status;
    private LocalDateTime localDateTime;
    private T data;
}
