package com.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("product") // evita ciclos infinitos al serializar
    private List<Comment> comments = new ArrayList<>();

    public Product(List<Comment> comments, LocalDateTime updated_at, LocalDateTime created_at, int stock, String image_url, Double price, String description, String name, int id) {
        this.comments = comments;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.stock = stock;
        this.image_url = image_url;
        this.price = price;
        this.description = description;
        this.name = name;
        this.id = id;
    }

    public Product() {
    }
}
