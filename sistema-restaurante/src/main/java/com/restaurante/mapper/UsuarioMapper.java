package com.restaurante.mapper;

import com.restaurante.dto.UsuarioRequestDTO;
import com.restaurante.dto.UsuarioResponseDTO;
import com.restaurante.entity.Rol;
import com.restaurante.entity.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequestDTO dto, Rol rol) {
        return Usuario.builder()
                .nombre(dto.getNombre())
                .correo(dto.getCorreo())
                .password(dto.getPassword())
                .telefono(dto.getTelefono())
                .estado(dto.getEstado() != null ? dto.getEstado() : true)
                .rol(rol)
                .build();
    }

    public static UsuarioResponseDTO toDTO(Usuario usuario) {
        return UsuarioResponseDTO.builder()
                .idUsuario(usuario.getIdUsuario())
                .nombre(usuario.getNombre())
                .correo(usuario.getCorreo())
                .telefono(usuario.getTelefono())
                .estado(usuario.getEstado())
                .idRol(usuario.getRol().getIdRol())
                .nombreRol(usuario.getRol().getNombreRol())
                .build();
    }
}