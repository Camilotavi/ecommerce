package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Table(name = "users")
@Entity
@Data
@ToString
public class User {

    public enum Role{
        user, admin
    }

    public enum Status {
        pending, approved, rejected
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private String password_hash;

    @Column
    private String photo_url;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role = Role.user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Status status = Status.pending;

    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    public User(){

    }

    public User(int id, String name, String email, String password_hash, String photo_url, Role role, Status status, LocalDateTime created_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password_hash = password_hash;
        this.photo_url = photo_url;
        this.role = role;
        this.status = status;
        this.created_at = created_at;
    }
}
