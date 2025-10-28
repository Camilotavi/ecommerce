package com.example.demo.controller;


import com.example.demo.DTO.OrderDTO;
import com.example.demo.entity.Coupon;
import com.example.demo.entity.Order;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CouponService;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth/user")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private CouponService couponService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(Authentication authentication, @RequestBody OrderDTO request) {
        String name = authentication.getName();
        if (!userRepository.existsById(userService.getIdByEmail(name))) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + userService.getIdByEmail(name));
        }


        Order order = orderService.addOrder(request,userService.getIdByEmail(name));
        if(request.getPayment_method()== Order.PaymentMethod.code){
            Coupon cupon = couponService.denyCoupon(authentication.getName());
        }
        return ResponseEntity.ok(order);
    }
/*
    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") int id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    } */

    @GetMapping("/order/me")
    public ResponseEntity<List<Order>> getOrdersByUser(Authentication authentication) {
        String name = authentication.getName();
        if (!userRepository.existsById(userService.getIdByEmail(name))) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + userService.getIdByEmail(name));
        }
        List<Order> orders = orderService.getOrdersByUserId(userService.getIdByEmail(name));
        return ResponseEntity.ok(orders);
    }
}
