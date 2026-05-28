package com.restaurante.controller;

import com.restaurante.dto.PromocionResponseDTO;
import com.restaurante.service.PromocionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/promociones")
@RequiredArgsConstructor
public class PublicPromocionController {

    private final PromocionService promocionService;

    @GetMapping("/vigentes")
    public List<PromocionResponseDTO> listarPromocionesVigentes() {
        return promocionService.listarPromocionesVigentes();
    }

    @GetMapping("/activas")
    public List<PromocionResponseDTO> listarPromocionesActivas() {
        return promocionService.listarPromocionesActivas();
    }
}