package com.restaurante.controller;

import com.restaurante.dto.ReservaRequestDTO;
import com.restaurante.dto.ReservaResponseDTO;
import com.restaurante.service.ReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @GetMapping
    public List<ReservaResponseDTO> listarReservas() {
        return reservaService.listarReservas();
    }

    @GetMapping("/{id}")
    public ReservaResponseDTO obtenerReservaPorId(@PathVariable Integer id) {
        return reservaService.obtenerReservaPorId(id);
    }

    @GetMapping("/estado/{estado}")
    public List<ReservaResponseDTO> listarPorEstado(@PathVariable String estado) {
        return reservaService.listarPorEstado(estado);
    }

    @GetMapping("/fecha/{fecha}")
    public List<ReservaResponseDTO> listarPorFecha(@PathVariable LocalDate fecha) {
        return reservaService.listarPorFecha(fecha);
    }

    @PostMapping
    public ReservaResponseDTO crearReserva(@Valid @RequestBody ReservaRequestDTO dto) {
        return reservaService.crearReserva(dto);
    }

    @PutMapping("/{id}")
    public ReservaResponseDTO actualizarReserva(@PathVariable Integer id, @Valid @RequestBody ReservaRequestDTO dto) {
        return reservaService.actualizarReserva(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminarReserva(@PathVariable Integer id) {
        reservaService.eliminarReserva(id);
    }
}