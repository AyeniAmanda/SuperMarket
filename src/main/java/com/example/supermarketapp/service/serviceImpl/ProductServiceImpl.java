package com.example.supermarketapp.service.serviceImpl;

import com.example.supermarketapp.dto.ProductDto;
import com.example.supermarketapp.entity.Product;
import com.example.supermarketapp.exception.ProductNotFoundException;
import com.example.supermarketapp.reponse.ApiResponse;
import com.example.supermarketapp.repository.ProductRepository;
import com.example.supermarketapp.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
       return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long id) {
      return  productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("The required product does not exist"));
    }

    @Override
    public ApiResponse createProduct(ProductDto product) {
        Product products = productRepository.save(products(product));
         return new ApiResponse<>(true,LocalDateTime.now(),products);
    }

    @Override
    public ApiResponse updateProduct(ProductDto product, Long id) {
        Product updateProducts = findProductById(id);
        updateProducts.setProductName(product.getProductName());
        updateProducts.setProductCategory(product.getProductCategory());
        updateProducts.setProductQuantity(product.getProductQuantity());
        updateProducts.setProductPrice(product.getProductPrice());
        Product products = productRepository.save(updateProducts);
        return  new ApiResponse<>(true, LocalDateTime.now(),products);
    }



    @Override
    public Boolean deleteProductById(Long id) {
        if(findProductById(id) == null){
            return false;
        }
         productRepository.deleteById(id);
         return true;
    }

    @Override
    public Product products(ProductDto productDto) {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setProductCategory(productDto.getProductCategory());
        product.setProductQuantity(productDto.getProductQuantity());
        product.setProductPrice(productDto.getProductPrice());
        return productRepository.save(product);
    }
}
