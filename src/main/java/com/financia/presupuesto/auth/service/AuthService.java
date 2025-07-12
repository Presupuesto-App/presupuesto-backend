package com.financia.presupuesto.auth.service;

import com.financia.presupuesto.auth.config.JwtConfig;
import com.financia.presupuesto.auth.dto.LoginRequestDto;
import com.financia.presupuesto.auth.dto.RegisterRequestDto;
import com.financia.presupuesto.auth.model.JwtToken;
import com.financia.presupuesto.usuario.model.Usuario;
import com.financia.presupuesto.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public JwtToken register(RegisterRequestDto request) {
        // Verificar si el usuario ya existe
        if (usuarioRepository.findByUsername(request.getUsername()) != null) {
            throw new RuntimeException("El usuario ya existe");
        }

        if (usuarioRepository.findByEmail(request.getEmail()) != null) {
            throw new RuntimeException("El email ya está registrado");
        }

        // Crear nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));

        usuarioRepository.save(usuario);

        // Generar token JWT
        String token = jwtConfig.generateToken(usuario.getUsername());
        return new JwtToken(token);
    }

    public JwtToken login(LoginRequestDto request) {
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername());

        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // Generar token JWT
        String token = jwtConfig.generateToken(usuario.getUsername());
        return new JwtToken(token);
    }
}
