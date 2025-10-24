package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/admin")
@PreAuthorize("hasRole('admin')")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/testingAdmin")
    @PreAuthorize("hasRole('admin')")
    public String test() {
        return "You are ADMIN";
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    @PatchMapping("/users/{id}/approve")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<?> approveUser(@PathVariable int id){
        try{
            User newApprovedUser = userService.approveUser(id);
            return ResponseEntity.ok(newApprovedUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

