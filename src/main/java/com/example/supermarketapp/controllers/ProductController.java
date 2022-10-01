package com.example.supermarketapp.controllers;

import com.example.supermarketapp.dto.ProductDto;
import com.example.supermarketapp.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/add-products")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto product) {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }


    @PostMapping(value = "/edit-products/product-id/{id}")
    public ResponseEntity<?> editProduct(@RequestBody ProductDto product,@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(productService.updateProduct(product,id),HttpStatus.OK);
    }

    @GetMapping(value = "/get-products/product-id/{productId}")
    public ResponseEntity<?> getSingleProduct(@PathVariable(name="productId") Long productId){
        return new ResponseEntity<>(productService.findProductById(productId),HttpStatus.OK);
    }

    @GetMapping(value = "/get-all-products")
    public ResponseEntity<?> getAllProduct(){
        return new ResponseEntity<>(productService.getAll(),HttpStatus.OK);
    }



    @PostMapping(value = "/deleteProduct/product-id/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name="productId") Long productId){
        return new ResponseEntity<>( productService.deleteProductById(productId),HttpStatus.OK);
    }
}
