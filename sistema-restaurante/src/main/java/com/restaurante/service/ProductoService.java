package com.restaurante.service;

import com.restaurante.dto.ProductoRequestDTO;
import com.restaurante.dto.ProductoResponseDTO;
import com.restaurante.entity.Categoria;
import com.restaurante.entity.Producto;
import com.restaurante.mapper.ProductoMapper;
import com.restaurante.repository.CategoriaRepository;
import com.restaurante.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.restaurante.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public List<ProductoResponseDTO> listarProductos() {
        return productoRepository.findAll()
                .stream()
                .map(ProductoMapper::toDTO)
                .toList();
    }

    public List<ProductoResponseDTO> listarProductosPorCategoria(Integer idCategoria) {
        return productoRepository.findByCategoriaIdCategoria(idCategoria)
                .stream()
                .map(ProductoMapper::toDTO)
                .toList();
    }

    public ProductoResponseDTO obtenerProductoPorId(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        return ProductoMapper.toDTO(producto);
    }

    public ProductoResponseDTO crearProducto(ProductoRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

        Producto producto = ProductoMapper.toEntity(dto, categoria);
        return ProductoMapper.toDTO(productoRepository.save(producto));
    }

    public ProductoResponseDTO actualizarProducto(Integer id, ProductoRequestDTO dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setEstado(dto.getEstado() != null ? dto.getEstado() : producto.getEstado());
        producto.setCategoria(categoria);

        return ProductoMapper.toDTO(productoRepository.save(producto));
    }

    public void eliminarProducto(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        producto.setEstado(false);
        productoRepository.save(producto);
    }
}