package com.restaurante.controller;

import com.restaurante.dto.ProductoRequestDTO;
import com.restaurante.dto.ProductoResponseDTO;
import com.restaurante.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public List<ProductoResponseDTO> listarProductos() {
        return productoService.listarProductos();
    }

    @GetMapping("/categoria/{idCategoria}")
    public List<ProductoResponseDTO> listarProductosPorCategoria(@PathVariable Integer idCategoria) {
        return productoService.listarProductosPorCategoria(idCategoria);
    }

    @GetMapping("/{id}")
    public ProductoResponseDTO obtenerProductoPorId(@PathVariable Integer id) {
        return productoService.obtenerProductoPorId(id);
    }

    @PostMapping
    public ProductoResponseDTO crearProducto(@Valid @RequestBody ProductoRequestDTO dto) {
        return productoService.crearProducto(dto);
    }

    @PutMapping("/{id}")
    public ProductoResponseDTO actualizarProducto(@PathVariable Integer id, @Valid @RequestBody ProductoRequestDTO dto) {
        return productoService.actualizarProducto(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Integer id) {
        productoService.eliminarProducto(id);
    }
}