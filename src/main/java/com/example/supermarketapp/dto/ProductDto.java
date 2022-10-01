package com.example.supermarketapp.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto implements Serializable {
    private String productName;

    private double productPrice;

    private String productCategory;

    private int productQuantity;

}
