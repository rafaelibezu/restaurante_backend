package com.restaurante.controller;

import com.restaurante.dto.ClienteRequestDTO;
import com.restaurante.dto.ClienteResponseDTO;
import com.restaurante.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/clientes")
@RequiredArgsConstructor
public class PublicClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ClienteResponseDTO registrarCliente(@Valid @RequestBody ClienteRequestDTO dto) {
        return clienteService.registrarOActualizarCliente(dto);
    }
}