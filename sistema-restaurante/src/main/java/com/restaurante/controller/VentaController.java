package com.restaurante.controller;

import com.restaurante.dto.VentaRequestDTO;
import com.restaurante.dto.VentaResponseDTO;
import com.restaurante.service.VentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    @GetMapping
    public List<VentaResponseDTO> listarVentas() {
        return ventaService.listarVentas();
    }

    @GetMapping("/{id}")
    public VentaResponseDTO obtenerVentaPorId(@PathVariable Integer id) {
        return ventaService.obtenerVentaPorId(id);
    }

    @PostMapping
    public VentaResponseDTO registrarVenta(@Valid @RequestBody VentaRequestDTO dto) {
        return ventaService.registrarVenta(dto);
    }
}