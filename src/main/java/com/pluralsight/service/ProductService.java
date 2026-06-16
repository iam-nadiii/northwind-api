package com.pluralsight.service;

import com.pluralsight.model.Product;
import com.pluralsight.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public boolean deleteProduct(Long id){
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getProductsByCategoryId(Long categoryId){
        return productRepository.findByCategory(categoryId);
    }

    public List<Product> getProductsByName(String name){
        return productRepository.findByNameContainingIgnoreCase(name);
    }


}


