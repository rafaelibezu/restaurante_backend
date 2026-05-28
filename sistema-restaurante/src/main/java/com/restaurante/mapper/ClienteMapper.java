package com.restaurante.mapper;

import com.restaurante.dto.ClienteRequestDTO;
import com.restaurante.dto.ClienteResponseDTO;
import com.restaurante.entity.Cliente;

import java.time.LocalDateTime;

public class ClienteMapper {

    public static Cliente toEntity(ClienteRequestDTO dto) {
        return Cliente.builder()
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .telefono(dto.getTelefono())
                .correo(dto.getCorreo())
                .fechaNacimiento(dto.getFechaNacimiento())
                .preferencias(dto.getPreferencias())
                .alergias(dto.getAlergias())
                .fechaRegistro(LocalDateTime.now())
                .estado(dto.getEstado() != null ? dto.getEstado() : true)
                .build();
    }

    public static ClienteResponseDTO toDTO(Cliente cliente) {
        return ClienteResponseDTO.builder()
                .idCliente(cliente.getIdCliente())
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .telefono(cliente.getTelefono())
                .correo(cliente.getCorreo())
                .fechaNacimiento(cliente.getFechaNacimiento())
                .preferencias(cliente.getPreferencias())
                .alergias(cliente.getAlergias())
                .fechaRegistro(cliente.getFechaRegistro())
                .ultimaVisita(cliente.getUltimaVisita())
                .estado(cliente.getEstado())
                .build();
    }
}