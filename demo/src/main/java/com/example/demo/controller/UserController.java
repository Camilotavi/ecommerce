package com.example.demo.controller;

import com.example.demo.DTO.updateUserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController
{
    @Autowired
    private UserService services;

    @RequestMapping("/auth/welcome")
    public String welcome() {
        return "Welcome, this endpoint is not secure";
    }

    @PostMapping("/user/addingUser")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            System.out.println(user);
            services.addUser(user);
            return ResponseEntity.ok("Usuario guardado exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el usuario");
        }
    }

    @GetMapping("/auth/user/me")
    public User getCurrentUser(Authentication authentication) {
        // Obtiene el correo (o username) del usuario autenticado
        String email = authentication.getName();

        // Busca el usuario en la base de datos a partir del email
        return services.getUserByEmail(email);
    }



    @PutMapping("/auth/user/update/me")
    public ResponseEntity<updateUserDTO> updateCurrentUser(Authentication authentication, @RequestBody updateUserDTO updatedUser) {
        try {
            String email = authentication.getName();
            User currentUser = services.getUserByEmail(email);
            services.updateUser(authentication.getName(), updatedUser);

            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/auth/user/delete/me")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        try {
            services.deleteUser(id);
            return ResponseEntity.ok("Usuario eliminado exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el usuario");
        }
    }




}