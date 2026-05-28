package com.restaurante.controller;

import com.restaurante.dto.ReservaRequestDTO;
import com.restaurante.dto.ReservaResponseDTO;
import com.restaurante.service.ReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/reservas")
@RequiredArgsConstructor
public class PublicReservaController {

    private final ReservaService reservaService;

    @PostMapping
    public ReservaResponseDTO crearReservaPublica(@Valid @RequestBody ReservaRequestDTO dto) {
        return reservaService.crearReserva(dto);
    }
}