package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Table(name = "products")
@Entity
@ToString
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column
    private String image_url;

    @Column
    private int stock;

    @Column
    private LocalDateTime created_at = LocalDateTime.now();

    @Column
    private LocalDateTime updated_at = LocalDateTime.now();

    public Product(int id, String name, String description, Double price, String image_url, int stock, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image_url = image_url;
        this.stock = stock;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
