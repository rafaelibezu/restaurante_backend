package com.restaurante.service;

import com.restaurante.dto.CategoriaRequestDTO;
import com.restaurante.dto.CategoriaResponseDTO;
import com.restaurante.entity.Categoria;
import com.restaurante.mapper.CategoriaMapper;
import com.restaurante.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.restaurante.exception.ResourceNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<CategoriaResponseDTO> listarCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(CategoriaMapper::toDTO)
                .toList();
    }

    public CategoriaResponseDTO obtenerCategoriaPorId(Integer id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
        return CategoriaMapper.toDTO(categoria);
    }

    public CategoriaResponseDTO crearCategoria(CategoriaRequestDTO dto) {
        Categoria categoria = CategoriaMapper.toEntity(dto);
        return CategoriaMapper.toDTO(categoriaRepository.save(categoria));
    }

    public CategoriaResponseDTO actualizarCategoria(Integer id, CategoriaRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        categoria.setEstado(dto.getEstado() != null ? dto.getEstado() : categoria.getEstado());

        return CategoriaMapper.toDTO(categoriaRepository.save(categoria));
    }

    public void eliminarCategoria(Integer id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

        categoria.setEstado(false);
        categoriaRepository.save(categoria);
    }
}