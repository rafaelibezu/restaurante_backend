package com.restaurante.service;

import com.restaurante.dto.FidelizacionRequestDTO;
import com.restaurante.dto.FidelizacionResponseDTO;
import com.restaurante.entity.Cliente;
import com.restaurante.entity.Fidelizacion;
import com.restaurante.exception.ResourceNotFoundException;
import com.restaurante.mapper.FidelizacionMapper;
import com.restaurante.repository.ClienteRepository;
import com.restaurante.repository.FidelizacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FidelizacionService {

    private final FidelizacionRepository fidelizacionRepository;
    private final ClienteRepository clienteRepository;

    public List<FidelizacionResponseDTO> listarFidelizacion() {
        return fidelizacionRepository.findAll()
                .stream()
                .map(FidelizacionMapper::toDTO)
                .toList();
    }

    public FidelizacionResponseDTO obtenerPorId(Integer id) {
        Fidelizacion fidelizacion = fidelizacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fidelización no encontrada"));

        return FidelizacionMapper.toDTO(fidelizacion);
    }

    public FidelizacionResponseDTO obtenerPorCliente(Integer idCliente) {
        Fidelizacion fidelizacion = fidelizacionRepository.findByClienteIdCliente(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("Fidelización del cliente no encontrada"));

        return FidelizacionMapper.toDTO(fidelizacion);
    }

    public FidelizacionResponseDTO crearFidelizacion(FidelizacionRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        Fidelizacion fidelizacion = Fidelizacion.builder()
                .cliente(cliente)
                .puntos(dto.getPuntos())
                .nivel(dto.getNivel() != null ? dto.getNivel() : calcularNivel(dto.getPuntos()))
                .descuentoDisponible(dto.getDescuentoDisponible() != null ? dto.getDescuentoDisponible() : calcularDescuento(dto.getPuntos()))
                .fechaActualizacion(LocalDateTime.now())
                .build();

        return FidelizacionMapper.toDTO(fidelizacionRepository.save(fidelizacion));
    }

    public FidelizacionResponseDTO actualizarFidelizacion(Integer id, FidelizacionRequestDTO dto) {
        Fidelizacion fidelizacion = fidelizacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fidelización no encontrada"));

        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        fidelizacion.setCliente(cliente);
        fidelizacion.setPuntos(dto.getPuntos());
        fidelizacion.setNivel(dto.getNivel() != null ? dto.getNivel() : calcularNivel(dto.getPuntos()));
        fidelizacion.setDescuentoDisponible(dto.getDescuentoDisponible() != null ? dto.getDescuentoDisponible() : calcularDescuento(dto.getPuntos()));
        fidelizacion.setFechaActualizacion(LocalDateTime.now());

        return FidelizacionMapper.toDTO(fidelizacionRepository.save(fidelizacion));
    }

    public void eliminarFidelizacion(Integer id) {
        fidelizacionRepository.deleteById(id);
    }

    private String calcularNivel(Integer puntos) {
        if (puntos >= 200) return "VIP";
        if (puntos >= 100) return "Oro";
        if (puntos >= 50) return "Plata";
        return "Bronce";
    }

    private BigDecimal calcularDescuento(Integer puntos) {
        if (puntos >= 200) return BigDecimal.valueOf(20);
        if (puntos >= 100) return BigDecimal.valueOf(10);
        if (puntos >= 50) return BigDecimal.valueOf(5);
        return BigDecimal.ZERO;
    }
}