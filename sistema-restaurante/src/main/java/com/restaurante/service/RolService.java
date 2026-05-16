package com.restaurante.service;

import com.restaurante.dto.RolRequestDTO;
import com.restaurante.dto.RolResponseDTO;
import com.restaurante.entity.Rol;
import com.restaurante.mapper.RolMapper;
import com.restaurante.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.restaurante.exception.ResourceNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RolService {

    private final RolRepository rolRepository;

    public List<RolResponseDTO> listarRoles() {
        return rolRepository.findAll()
                .stream()
                .map(RolMapper::toDTO)
                .toList();
    }

    public RolResponseDTO obtenerRolPorId(Integer id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));
        return RolMapper.toDTO(rol);
    }

    public RolResponseDTO crearRol(RolRequestDTO dto) {
        Rol rol = RolMapper.toEntity(dto);
        return RolMapper.toDTO(rolRepository.save(rol));
    }

    public RolResponseDTO actualizarRol(Integer id, RolRequestDTO dto) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));

        rol.setNombreRol(dto.getNombreRol());
        rol.setEstado(dto.getEstado() != null ? dto.getEstado() : rol.getEstado());

        return RolMapper.toDTO(rolRepository.save(rol));
    }

    public void eliminarRol(Integer id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));

        rol.setEstado(false);
        rolRepository.save(rol);
    }
}