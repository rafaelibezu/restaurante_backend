package com.restaurante.service;

import com.restaurante.dto.DashboardResponseDTO;
import com.restaurante.repository.InventarioRepository;
import com.restaurante.repository.MesaRepository;
import com.restaurante.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final PedidoRepository pedidoRepository;
    private final MesaRepository mesaRepository;
    private final InventarioRepository inventarioRepository;

    public DashboardResponseDTO obtenerDashboard() {

        return DashboardResponseDTO.builder()
                .totalPedidos((long) pedidoRepository.findAll().size())
                .pedidosPendientes(pedidoRepository.countByEstado("Pendiente"))
                .pedidosEntregados(pedidoRepository.countByEstado("Entregado"))
                .mesasOcupadas(mesaRepository.countByEstado("Ocupada"))
                .mesasDisponibles(mesaRepository.countByEstado("Disponible"))
                .productosStockBajo(inventarioRepository.contarStockBajo())
                .ventasTotales(pedidoRepository.obtenerVentasTotales())
                .build();
    }
}