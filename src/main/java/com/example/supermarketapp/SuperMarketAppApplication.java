package com.example.supermarketapp;

import com.example.supermarketapp.entity.Product;
import com.example.supermarketapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SuperMarketAppApplication implements CommandLineRunner {
    private final ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(SuperMarketAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception{
        productRepository.save(new Product(1l,"fish",100000,"food",10));
        productRepository.save(new Product(2l,"meat",1500,"food",5));
        productRepository.save(new Product(3l,"milo",250,"provision",20));
        productRepository.save(new Product(4l,"milk",5000,"provision",10));
    }

}
