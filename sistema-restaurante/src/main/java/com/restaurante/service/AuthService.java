package com.restaurante.service;

import com.restaurante.dto.AuthRequestDTO;
import com.restaurante.dto.AuthResponseDTO;
import com.restaurante.repository.UsuarioRepository;
import com.restaurante.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.Service;

import com.restaurante.entity.Usuario;
import com.restaurante.exception.ResourceNotFoundException;
import com.restaurante.repository.UsuarioRepository;
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;

    public AuthResponseDTO login(AuthRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCorreo(),
                        request.getPassword()
                )
        );

        Usuario usuario = usuarioRepository.findByCorreo(request.getCorreo())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        String token = jwtService.generarToken(request.getCorreo());

        return AuthResponseDTO.builder()
                .token(token)
                .idUsuario(usuario.getIdUsuario())
                .nombre(usuario.getNombre())
                .correo(usuario.getCorreo())
                .rol(usuario.getRol().getNombreRol())
                .build();
    }
}