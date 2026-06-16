package com.pluralsight.repository;

import com.pluralsight.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Long categoryId);
    List<Product> findByNameContainingIgnoreCase(String name);
}


