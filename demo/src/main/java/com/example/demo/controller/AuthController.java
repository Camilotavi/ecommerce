package com.example.demo.controller;

import com.example.demo.DTO.LoginRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
public class AuthController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletRequest loginRequest) {

        System.out.println("USUARIO RECIBIDO: "+ request.getEmail());

        try {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

            Authentication authentication = authenticationManager.authenticate(authToken);

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            loginRequest.getSession(true);


            HttpSession session = loginRequest.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", context);

            // Buscar info del usuario
            User user = userService.getUserByEmail(request.getEmail());

            // Retornar datos al front
            return ResponseEntity.ok(new UserResponse(user.getEmail(), String.valueOf(user.getRole())));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cai aca");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
    public record UserResponse(String username, String role) {}
}
