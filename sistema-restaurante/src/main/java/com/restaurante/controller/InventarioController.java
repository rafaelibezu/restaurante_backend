package com.restaurante.controller;

import com.restaurante.dto.InventarioRequestDTO;
import com.restaurante.dto.InventarioResponseDTO;
import com.restaurante.service.InventarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService inventarioService;

    @GetMapping
    public List<InventarioResponseDTO> listarInventario() {
        return inventarioService.listarInventario();
    }

    @GetMapping("/{id}")
    public InventarioResponseDTO obtenerInventarioPorId(@PathVariable Integer id) {
        return inventarioService.obtenerInventarioPorId(id);
    }

    @PostMapping
    public InventarioResponseDTO crearInventario(@Valid @RequestBody InventarioRequestDTO dto) {
        return inventarioService.crearInventario(dto);
    }

    @PutMapping("/{id}")
    public InventarioResponseDTO actualizarInventario(@PathVariable Integer id, @Valid @RequestBody InventarioRequestDTO dto) {
        return inventarioService.actualizarInventario(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminarInventario(@PathVariable Integer id) {
        inventarioService.eliminarInventario(id);
    }
}