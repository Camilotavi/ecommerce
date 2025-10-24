package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts()
    {
        return (List<Product>) this.repository.findAll();
    }

    public Product getProduct(int id)
    {
        Optional<Product> optional = this.repository.findById(id);
        Product product=optional.get();
        return product;
    }
}
