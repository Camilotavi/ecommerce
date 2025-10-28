package com.example.demo.entity;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Table(name = "coupons")
@Entity
@Data
@ToString
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String code;

    @Column
    private double discount;

    @Column(nullable = false)
    private int user;

    @Column
    private int used;

    @Column
    private LocalDateTime created_at = LocalDateTime.now();

    public Coupon(int id, String code, double discount, int user, int used, LocalDateTime created_at) {
        this.id = id;
        this.code = code;
        this.discount = discount;
        this.user = user;
        this.used = used;
        this.created_at = created_at;
    }

    public Coupon() {
    }
}
