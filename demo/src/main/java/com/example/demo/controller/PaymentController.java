package com.example.demo.controller;


import com.example.demo.entity.Coupon;
import com.example.demo.service.CouponService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth/user")
public class PaymentController {
    
    @Autowired
    private CouponService couponService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/coupon/me")
    public Coupon getCoupon(Authentication authentication){
        
        return this.couponService.getCouponById(this.userService.getIdByEmail(authentication.getName()));

    }
}
