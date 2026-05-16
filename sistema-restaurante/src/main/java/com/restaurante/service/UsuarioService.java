package com.restaurante.service;

import com.restaurante.dto.UsuarioRequestDTO;
import com.restaurante.dto.UsuarioResponseDTO;
import com.restaurante.entity.Rol;
import com.restaurante.entity.Usuario;
import com.restaurante.mapper.UsuarioMapper;
import com.restaurante.repository.RolRepository;
import com.restaurante.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.restaurante.exception.ResourceNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::toDTO)
                .toList();
    }

    public UsuarioResponseDTO obtenerUsuarioPorId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return UsuarioMapper.toDTO(usuario);
    }

    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO dto) {
        Rol rol = rolRepository.findById(dto.getIdRol())
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));

        Usuario usuario = UsuarioMapper.toEntity(dto, rol);
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        return UsuarioMapper.toDTO(usuarioRepository.save(usuario));
    }

    public UsuarioResponseDTO actualizarUsuario(Integer id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Rol rol = rolRepository.findById(dto.getIdRol())
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));

        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setTelefono(dto.getTelefono());
        usuario.setEstado(dto.getEstado() != null ? dto.getEstado() : usuario.getEstado());
        usuario.setRol(rol);

        return UsuarioMapper.toDTO(usuarioRepository.save(usuario));
    }

    public void eliminarUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        usuario.setEstado(false);
        usuarioRepository.save(usuario);
    }
}