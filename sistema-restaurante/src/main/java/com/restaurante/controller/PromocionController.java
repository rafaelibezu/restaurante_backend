package com.restaurante.controller;

import com.restaurante.dto.PromocionRequestDTO;
import com.restaurante.dto.PromocionResponseDTO;
import com.restaurante.service.PromocionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promociones")
@RequiredArgsConstructor
public class PromocionController {

    private final PromocionService promocionService;

    @GetMapping
    public List<PromocionResponseDTO> listarPromociones() {
        return promocionService.listarPromociones();
    }

    @GetMapping("/{id}")
    public PromocionResponseDTO obtenerPromocionPorId(@PathVariable Integer id) {
        return promocionService.obtenerPromocionPorId(id);
    }

    @GetMapping("/activas")
    public List<PromocionResponseDTO> listarPromocionesActivas() {
        return promocionService.listarPromocionesActivas();
    }

    @GetMapping("/vigentes")
    public List<PromocionResponseDTO> listarPromocionesVigentes() {
        return promocionService.listarPromocionesVigentes();
    }

    @PostMapping
    public PromocionResponseDTO crearPromocion(@Valid @RequestBody PromocionRequestDTO dto) {
        return promocionService.crearPromocion(dto);
    }

    @PutMapping("/{id}")
    public PromocionResponseDTO actualizarPromocion(@PathVariable Integer id, @Valid @RequestBody PromocionRequestDTO dto) {
        return promocionService.actualizarPromocion(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminarPromocion(@PathVariable Integer id) {
        promocionService.eliminarPromocion(id);
    }
}