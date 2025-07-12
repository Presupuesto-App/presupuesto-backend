package com.financia.presupuesto.auth.controller;

import com.financia.presupuesto.auth.dto.LoginRequestDto;
import com.financia.presupuesto.auth.dto.RegisterRequestDto;
import com.financia.presupuesto.auth.model.JwtToken;
import com.financia.presupuesto.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Autenticación", description = "Operaciones de registro e inicio de sesión")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Registrar nuevo usuario", description = "Registra un nuevo usuario y devuelve un token JWT")
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDto request) {
        try {
            JwtToken token = authService.register(request);
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            // Devolver el mensaje de error específico para debugging
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            // Capturar cualquier otra excepción
            return ResponseEntity.badRequest().body("Error interno: " + e.getMessage());
        }
    }

    @Operation(summary = "Iniciar sesión", description = "Autentica un usuario y devuelve un token JWT")
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto request) {
        try {
            JwtToken token = authService.login(request);
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error interno: " + e.getMessage());
        }
    }
}
