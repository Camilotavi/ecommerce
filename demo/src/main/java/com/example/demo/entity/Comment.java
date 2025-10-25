package com.example.demo.entity;

import com.example.demo.DTO.ProductDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "comments")
@ToString
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "user_id", nullable = false)
    private String user;

    private String content;
    private int rating; // 1–5
    private LocalDateTime createdAt = LocalDateTime.now();

    public Comment(int id, Product product, String user, String content, int rating, LocalDateTime createdAt) {
        this.id = id;
        this.product = product;
        this.user = user;
        this.content = content;
        this.rating = rating;
        this.createdAt = createdAt;
    }

    public Comment() {
    }


    public Comment(Optional<Product> product, String name, String content, int rating) {
    }
}

