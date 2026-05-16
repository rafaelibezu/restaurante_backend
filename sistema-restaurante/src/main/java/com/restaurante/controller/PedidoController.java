package com.restaurante.controller;

import com.restaurante.dto.PedidoRequestDTO;
import com.restaurante.dto.PedidoResponseDTO;
import com.restaurante.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    public List<PedidoResponseDTO> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public PedidoResponseDTO obtenerPedidoPorId(@PathVariable Integer id) {
        return pedidoService.obtenerPedidoPorId(id);
    }

    @GetMapping("/estado/{estado}")
    public List<PedidoResponseDTO> listarPedidosPorEstado(@PathVariable String estado) {
        return pedidoService.listarPedidosPorEstado(estado);
    }

    @PostMapping
    public PedidoResponseDTO crearPedido(@Valid @RequestBody PedidoRequestDTO dto) {
        return pedidoService.crearPedido(dto);
    }

    @PatchMapping("/{id}/estado")
    public PedidoResponseDTO cambiarEstado(
            @PathVariable Integer id,
            @RequestParam String estado
    ) {
        return pedidoService.cambiarEstado(id, estado);
    }
}