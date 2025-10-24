package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/auth/user/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = service.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/auth/user/products/{id}")
    public Product getProductById(@PathVariable int id) {
        try{
            return service.getProduct(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
