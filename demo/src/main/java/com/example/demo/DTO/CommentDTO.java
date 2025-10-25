package com.example.demo.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {
    private int id;
    private String content;
    private int rating;
    private String userName;

    public CommentDTO(int id, String content, int rating, String userName) {
        this.id = id;
        this.content = content;
        this.rating = rating;
        this.userName = userName;
    }

}

