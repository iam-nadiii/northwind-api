package com.pluralsight.controller;

import com.pluralsight.model.Product;
import com.pluralsight.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam (required = false) String name){
        if(name != null){
            ResponseEntity.ok(productService.getProductsByName(name));
        }
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@RequestParam Long id){
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categorId}")
    public ResponseEntity<List<Product>> getProductsByCategoryId(@RequestParam Long categoryId){
        return ResponseEntity.ok(productService.getProductsByCategoryId(categoryId));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@RequestParam Long id){
        if (productService.deleteProduct(id)){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}


