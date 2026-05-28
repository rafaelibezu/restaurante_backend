package com.restaurante.service;

import com.restaurante.dto.DashboardCrmDTO;
import com.restaurante.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardCrmService {

    private final ClienteRepository clienteRepository;
    private final ReservaRepository reservaRepository;
    private final PromocionRepository promocionRepository;
    private final NotificacionRepository notificacionRepository;
    private final PedidoRepository pedidoRepository;

    public DashboardCrmDTO obtenerDashboardCrm() {

        Long clientesFrecuentes = (long) pedidoRepository.contarClientesFrecuentes().size();

        String clienteTop = "Sin datos";
        BigDecimal totalGastadoClienteTop = BigDecimal.ZERO;

        List<Object[]> rankingClientes = pedidoRepository.obtenerRankingClientesPorConsumo();

        if (!rankingClientes.isEmpty()) {
            Object[] top = rankingClientes.get(0);
            clienteTop = (String) top[0];
            totalGastadoClienteTop = (BigDecimal) top[1];
        }

        return DashboardCrmDTO.builder()
                .totalClientes(clienteRepository.count())
                .clientesFrecuentes(clientesFrecuentes)
                .clientesInactivos(clienteRepository.countByEstado(false))
                .reservasPendientes(reservaRepository.countByEstado("Pendiente"))
                .promocionesActivas(promocionRepository.countByEstado(true))
                .ticketPromedioGeneral(pedidoRepository.obtenerTicketPromedioClientes())
                .clienteTop(clienteTop)
                .totalGastadoClienteTop(totalGastadoClienteTop)
                .notificacionesPendientes(notificacionRepository.countByEstado("Pendiente"))
                .build();
    }
}