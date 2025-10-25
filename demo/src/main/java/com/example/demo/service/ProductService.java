package com.example.demo.service;

import com.example.demo.DTO.CommentDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<ProductDTO> getAllProducts() {
        List<Product> products = repository.findAll();

        return products.stream().map(p -> {
            List<CommentDTO> commentDTOs = p.getComments().stream()
                    .map(c -> new CommentDTO(
                            c.getId(),
                            c.getContent(),
                            c.getRating(),
                            c.getUser()
                    ))
                    .collect(Collectors.toList());

            return new ProductDTO(
                    p.getId(),
                    p.getName(),
                    p.getDescription(),
                    p.getPrice(),
                    p.getImage_url(),
                    commentDTOs
            );
        }).collect(Collectors.toList());
    }
    public ProductDTO getProduct(int id) {

        Product p = repository.findByIdWithComments(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        List<CommentDTO> commentDTOs = p.getComments().stream()
                .map(c -> new CommentDTO(
                        c.getId(),
                        c.getContent(),
                        c.getRating(),
                        c.getUser()
                ))
                .collect(Collectors.toList());

        return new ProductDTO(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.getImage_url(),
                commentDTOs
        );
    }
    
    public Optional<Product> getProductnotDTO(int id){
        return repository.findById(id);
    }

}
