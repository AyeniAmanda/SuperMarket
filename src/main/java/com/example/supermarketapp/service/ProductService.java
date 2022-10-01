package com.example.supermarketapp.service;

import com.example.supermarketapp.dto.ProductDto;
import com.example.supermarketapp.entity.Product;
import com.example.supermarketapp.reponse.ApiResponse;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Product findProductById(Long id);


    ApiResponse createProduct(ProductDto product);

    ApiResponse updateProduct(ProductDto product,Long id);



    Boolean deleteProductById(Long id);

    Product products(ProductDto productDto);
}
