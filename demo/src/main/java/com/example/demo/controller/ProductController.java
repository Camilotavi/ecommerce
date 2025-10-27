package com.example.demo.controller;

import com.example.demo.DTO.CommentDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Product;
import com.example.demo.service.CommentService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/user")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private CommentService commentservice;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = service.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/{id}")
    public ProductDTO getProductById(@PathVariable("id") int id) {
        try{
            return service.getProduct(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/products/ncomment")
    public ResponseEntity<Comment> postComment(Authentication authentication, @RequestBody CommentDTO commentDTO){
        try{
            String name = authentication.getName();
            return ResponseEntity.ok(commentservice.addComment(name, commentDTO).getBody());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
