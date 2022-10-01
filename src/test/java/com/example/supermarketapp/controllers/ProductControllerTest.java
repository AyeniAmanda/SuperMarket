package com.example.supermarketapp.controllers;

import com.example.supermarketapp.dto.ProductDto;
import com.example.supermarketapp.entity.User;
import com.example.supermarketapp.reponse.ApiResponse;
import com.example.supermarketapp.service.ProductService;
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
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    ProductService productService;
    private LocalDateTime dateTime;
    private User user;

    @BeforeEach
    void setUp() {
        dateTime = LocalDateTime.of(2022, SEPTEMBER, 7, 4, 20, 40, 3000);
        user = new User(1l, "amanda", "ayeni", "amanda@gmail.com", "1234");
    }

    @Test
    void CreateProduct() throws Exception {
        ProductDto productDto = new ProductDto("Fish", 10000, "food", 1);
        when(productService.createProduct(any(ProductDto.class))).thenReturn(createProductResponse());
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/add-products").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(productDto)).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.status", is(true)))
                .andExpect(jsonPath("$.localDateTime", is(dateTime.toString())))
                .andExpect(jsonPath("$.data", is("you have added a product successfully")));
    }
    private ApiResponse createProductResponse() {
        ApiResponse createProductResponse = new ApiResponse(true, dateTime, "you have added a product successfully");
        return createProductResponse;
    }

}

