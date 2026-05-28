package com.restaurante.controller;

import com.restaurante.dto.MesaResponseDTO;
import com.restaurante.service.MesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/mesas")
@RequiredArgsConstructor
public class PublicMesaController {

    private final MesaService mesaService;

    @GetMapping("/disponibles")
    public List<MesaResponseDTO> listarMesasDisponibles() {
        return mesaService.listarMesasDisponibles();
    }
}