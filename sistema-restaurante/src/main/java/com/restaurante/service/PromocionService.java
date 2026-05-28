package com.restaurante.service;

import com.restaurante.dto.PromocionRequestDTO;
import com.restaurante.dto.PromocionResponseDTO;
import com.restaurante.entity.Promocion;
import com.restaurante.exception.ResourceNotFoundException;
import com.restaurante.mapper.PromocionMapper;
import com.restaurante.repository.PromocionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromocionService {

    private final PromocionRepository promocionRepository;

    public List<PromocionResponseDTO> listarPromociones() {
        return promocionRepository.findAll()
                .stream()
                .map(PromocionMapper::toDTO)
                .toList();
    }

    public List<PromocionResponseDTO> listarPromocionesActivas() {
        return promocionRepository.findByEstado(true)
                .stream()
                .map(PromocionMapper::toDTO)
                .toList();
    }

    public List<PromocionResponseDTO> listarPromocionesVigentes() {
        LocalDate hoy = LocalDate.now();

        return promocionRepository
                .findByFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(hoy, hoy)
                .stream()
                .map(PromocionMapper::toDTO)
                .toList();
    }

    public PromocionResponseDTO obtenerPromocionPorId(Integer id) {
        Promocion promocion = promocionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Promoción no encontrada"));

        return PromocionMapper.toDTO(promocion);
    }

    public PromocionResponseDTO crearPromocion(PromocionRequestDTO dto) {
        Promocion promocion = PromocionMapper.toEntity(dto);
        return PromocionMapper.toDTO(promocionRepository.save(promocion));
    }

    public PromocionResponseDTO actualizarPromocion(Integer id, PromocionRequestDTO dto) {
        Promocion promocion = promocionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Promoción no encontrada"));

        promocion.setNombre(dto.getNombre());
        promocion.setDescripcion(dto.getDescripcion());
        promocion.setTipo(dto.getTipo());
        promocion.setValorDescuento(dto.getValorDescuento());
        promocion.setFechaInicio(dto.getFechaInicio());
        promocion.setFechaFin(dto.getFechaFin());
        promocion.setEstado(dto.getEstado() != null ? dto.getEstado() : promocion.getEstado());

        return PromocionMapper.toDTO(promocionRepository.save(promocion));
    }

    public void eliminarPromocion(Integer id) {
        Promocion promocion = promocionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Promoción no encontrada"));

        promocion.setEstado(false);
        promocionRepository.save(promocion);
    }
}