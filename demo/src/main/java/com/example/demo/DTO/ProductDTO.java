package com.example.demo.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    private int id;
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private List<CommentDTO> comments;

    public ProductDTO(int id, String name, String description, double price, String imageUrl, List<CommentDTO> comments) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.comments = comments;
    }
}
