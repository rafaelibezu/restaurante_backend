package com.restaurante.controller;

import com.restaurante.dto.RolRequestDTO;
import com.restaurante.dto.RolResponseDTO;
import com.restaurante.service.RolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    @GetMapping
    public List<RolResponseDTO> listarRoles() {
        return rolService.listarRoles();
    }

    @GetMapping("/{id}")
    public RolResponseDTO obtenerRolPorId(@PathVariable Integer id) {
        return rolService.obtenerRolPorId(id);
    }

    @PostMapping
    public RolResponseDTO crearRol(@Valid @RequestBody RolRequestDTO dto) {
        return rolService.crearRol(dto);
    }

    @PutMapping("/{id}")
    public RolResponseDTO actualizarRol(@PathVariable Integer id, @Valid @RequestBody RolRequestDTO dto) {
        return rolService.actualizarRol(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminarRol(@PathVariable Integer id) {
        rolService.eliminarRol(id);
    }
}