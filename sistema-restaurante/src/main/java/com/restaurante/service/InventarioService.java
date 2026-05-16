package com.restaurante.service;

import com.restaurante.dto.InventarioRequestDTO;
import com.restaurante.dto.InventarioResponseDTO;
import com.restaurante.entity.Inventario;
import com.restaurante.entity.Producto;
import com.restaurante.mapper.InventarioMapper;
import com.restaurante.repository.InventarioRepository;
import com.restaurante.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.restaurante.exception.ResourceNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository inventarioRepository;
    private final ProductoRepository productoRepository;

    public List<InventarioResponseDTO> listarInventario() {
        return inventarioRepository.findAll()
                .stream()
                .map(InventarioMapper::toDTO)
                .toList();
    }

    public InventarioResponseDTO obtenerInventarioPorId(Integer id) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado"));
        return InventarioMapper.toDTO(inventario);
    }

    public InventarioResponseDTO crearInventario(InventarioRequestDTO dto) {
        Producto producto = productoRepository.findById(dto.getIdProducto())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        Inventario inventario = InventarioMapper.toEntity(dto, producto);
        return InventarioMapper.toDTO(inventarioRepository.save(inventario));
    }

    public InventarioResponseDTO actualizarInventario(Integer id, InventarioRequestDTO dto) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado"));

        Producto producto = productoRepository.findById(dto.getIdProducto())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        inventario.setProducto(producto);
        inventario.setCantidadActual(dto.getCantidadActual());
        inventario.setStockMinimo(dto.getStockMinimo());
        inventario.setFechaActualizacion(LocalDateTime.now());

        return InventarioMapper.toDTO(inventarioRepository.save(inventario));
    }

    public void eliminarInventario(Integer id) {
        inventarioRepository.deleteById(id);
    }
}