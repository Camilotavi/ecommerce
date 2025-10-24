package com.example.demo.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class updateUserDTO {
    private String name;
    private String photo_url;

    public updateUserDTO(String photo_url, String name) {
        this.photo_url = photo_url;
        this.name = name;
    }
}
