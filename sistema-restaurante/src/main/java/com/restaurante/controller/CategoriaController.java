package com.restaurante.controller;

import com.restaurante.dto.CategoriaRequestDTO;
import com.restaurante.dto.CategoriaResponseDTO;
import com.restaurante.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaResponseDTO> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    @GetMapping("/{id}")
    public CategoriaResponseDTO obtenerCategoriaPorId(@PathVariable Integer id) {
        return categoriaService.obtenerCategoriaPorId(id);
    }

    @PostMapping
    public CategoriaResponseDTO crearCategoria(@Valid @RequestBody CategoriaRequestDTO dto) {
        return categoriaService.crearCategoria(dto);
    }

    @PutMapping("/{id}")
    public CategoriaResponseDTO actualizarCategoria(@PathVariable Integer id, @Valid @RequestBody CategoriaRequestDTO dto) {
        return categoriaService.actualizarCategoria(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminarCategoria(@PathVariable Integer id) {
        categoriaService.eliminarCategoria(id);
    }
}