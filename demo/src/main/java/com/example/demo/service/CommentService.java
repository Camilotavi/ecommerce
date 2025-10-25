package com.example.demo.service;

import com.example.demo.DTO.CommentDTO;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    public ResponseEntity<Comment> addComment(String name, CommentDTO commentDTO) {
        User user = userService.getUserByEmail(name);

        Comment comment = new Comment();

        Product product = productRepository.findById(commentDTO.getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + commentDTO.getId()));

        comment.setProduct(product);
        comment.setContent(commentDTO.getContent());
        comment.setUser(name);
        comment.setRating(commentDTO.getRating());


        return ResponseEntity.ok(commentRepository.save(comment));
    }
}

