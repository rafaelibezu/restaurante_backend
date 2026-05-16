package com.restaurante.controller;

import com.restaurante.dto.MesaRequestDTO;
import com.restaurante.dto.MesaResponseDTO;
import com.restaurante.service.MesaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesas")
@RequiredArgsConstructor
public class MesaController {

    private final MesaService mesaService;

    @GetMapping
    public List<MesaResponseDTO> listarMesas() {
        return mesaService.listarMesas();
    }

    @GetMapping("/estado/{estado}")
    public List<MesaResponseDTO> listarMesasPorEstado(@PathVariable String estado) {
        return mesaService.listarMesasPorEstado(estado);
    }

    @GetMapping("/{id}")
    public MesaResponseDTO obtenerMesaPorId(@PathVariable Integer id) {
        return mesaService.obtenerMesaPorId(id);
    }

    @PostMapping
    public MesaResponseDTO crearMesa(@Valid @RequestBody MesaRequestDTO dto) {
        return mesaService.crearMesa(dto);
    }

    @PutMapping("/{id}")
    public MesaResponseDTO actualizarMesa(@PathVariable Integer id, @Valid @RequestBody MesaRequestDTO dto) {
        return mesaService.actualizarMesa(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminarMesa(@PathVariable Integer id) {
        mesaService.eliminarMesa(id);
    }
}