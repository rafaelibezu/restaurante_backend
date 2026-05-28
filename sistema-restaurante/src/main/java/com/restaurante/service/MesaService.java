package com.restaurante.service;

import com.restaurante.dto.MesaRequestDTO;
import com.restaurante.dto.MesaResponseDTO;
import com.restaurante.entity.Mesa;
import com.restaurante.mapper.MesaMapper;
import com.restaurante.repository.MesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import com.restaurante.exception.ResourceNotFoundException;
@Service
@RequiredArgsConstructor
public class MesaService {

    private final MesaRepository mesaRepository;

    public List<MesaResponseDTO> listarMesas() {
        return mesaRepository.findAll()
                .stream()
                .map(MesaMapper::toDTO)
                .toList();
    }

    public List<MesaResponseDTO> listarMesasPorEstado(String estado) {
        return mesaRepository.findByEstado(estado)
                .stream()
                .map(MesaMapper::toDTO)
                .toList();
    }

    public MesaResponseDTO obtenerMesaPorId(Integer id) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mesa no encontrada"));
        return MesaMapper.toDTO(mesa);
    }

    public MesaResponseDTO crearMesa(MesaRequestDTO dto) {
        Mesa mesa = MesaMapper.toEntity(dto);
        return MesaMapper.toDTO(mesaRepository.save(mesa));
    }

    public MesaResponseDTO actualizarMesa(Integer id, MesaRequestDTO dto) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mesa no encontrada"));

        mesa.setNumero(dto.getNumero());
        mesa.setCapacidad(dto.getCapacidad());
        mesa.setEstado(dto.getEstado() != null ? dto.getEstado() : mesa.getEstado());

        return MesaMapper.toDTO(mesaRepository.save(mesa));
    }

    public void eliminarMesa(Integer id) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mesa no encontrada"));

        mesa.setEstado("Inactiva");
        mesaRepository.save(mesa);
    }

    public List<MesaResponseDTO> listarMesasDisponibles() {
        return mesaRepository.findByEstado("Libre")
                .stream()
                .map(MesaMapper::toDTO)
                .toList();
    }


}