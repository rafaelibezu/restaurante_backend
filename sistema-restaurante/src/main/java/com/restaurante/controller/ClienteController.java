package com.restaurante.controller;

import com.restaurante.dto.ClienteRequestDTO;
import com.restaurante.dto.ClienteResponseDTO;
import com.restaurante.dto.HistorialConsumoDTO;
import com.restaurante.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public List<ClienteResponseDTO> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public ClienteResponseDTO obtenerClientePorId(@PathVariable Integer id) {
        return clienteService.obtenerClientePorId(id);
    }

    @GetMapping("/{id}/historial-consumo")
    public HistorialConsumoDTO obtenerHistorialConsumo(@PathVariable Integer id) {
        return clienteService.obtenerHistorialConsumo(id);
    }

    @PostMapping
    public ClienteResponseDTO crearCliente(@Valid @RequestBody ClienteRequestDTO dto) {
        return clienteService.crearCliente(dto);
    }

    @PutMapping("/{id}")
    public ClienteResponseDTO actualizarCliente(@PathVariable Integer id, @Valid @RequestBody ClienteRequestDTO dto) {
        return clienteService.actualizarCliente(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable Integer id) {
        clienteService.eliminarCliente(id);
    }
}