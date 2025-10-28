package com.example.demo.controller;

import com.example.demo.DTO.LoginRequest;
import com.example.demo.entity.User;
import com.example.demo.service.LoginAttemptService;
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

@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"}, allowCredentials = "true")
@RestController
public class AuthController {

    @Autowired
    private LoginAttemptService loginAttemptService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/auth/verify")
    public ResponseEntity<?> verifySession(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sesión inválida");
        }
        return ResponseEntity.ok("Sesión válida");
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletRequest loginRequest) {

        String clientIP = getClientIP(loginRequest);
        if (loginAttemptService.isBlocked(clientIP)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("Demasiados intentos fallidos. Intente nuevamente más tarde.");
        }

        try {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

            Authentication authentication = authenticationManager.authenticate(authToken);

            // Si llega aquí → login exitoso
            loginAttemptService.loginSucceeded(clientIP);

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

            HttpSession session = loginRequest.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", context);

            User user = userService.getUserByEmail(request.getEmail());
            return ResponseEntity.ok(new UserResponse(user.getEmail(), String.valueOf(user.getRole())));

        } catch (Exception e) {
            loginAttemptService.loginFailed(clientIP);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

    private String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    public record UserResponse(String username, String role) {}

}
