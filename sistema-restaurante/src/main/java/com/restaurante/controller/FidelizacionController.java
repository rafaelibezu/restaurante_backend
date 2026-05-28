package com.restaurante.controller;

import com.restaurante.dto.FidelizacionRequestDTO;
import com.restaurante.dto.FidelizacionResponseDTO;
import com.restaurante.service.FidelizacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fidelizacion")
@RequiredArgsConstructor
public class FidelizacionController {

    private final FidelizacionService fidelizacionService;

    @GetMapping
    public List<FidelizacionResponseDTO> listarFidelizacion() {
        return fidelizacionService.listarFidelizacion();
    }

    @GetMapping("/{id}")
    public FidelizacionResponseDTO obtenerPorId(@PathVariable Integer id) {
        return fidelizacionService.obtenerPorId(id);
    }

    @GetMapping("/cliente/{idCliente}")
    public FidelizacionResponseDTO obtenerPorCliente(@PathVariable Integer idCliente) {
        return fidelizacionService.obtenerPorCliente(idCliente);
    }

    @PostMapping
    public FidelizacionResponseDTO crearFidelizacion(@Valid @RequestBody FidelizacionRequestDTO dto) {
        return fidelizacionService.crearFidelizacion(dto);
    }

    @PutMapping("/{id}")
    public FidelizacionResponseDTO actualizarFidelizacion(@PathVariable Integer id, @Valid @RequestBody FidelizacionRequestDTO dto) {
        return fidelizacionService.actualizarFidelizacion(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminarFidelizacion(@PathVariable Integer id) {
        fidelizacionService.eliminarFidelizacion(id);
    }
}