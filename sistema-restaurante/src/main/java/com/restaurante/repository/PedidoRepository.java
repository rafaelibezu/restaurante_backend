package com.restaurante.repository;

import com.restaurante.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByEstado(String estado);

    List<Pedido> findByClienteIdCliente(Integer idCliente);

    Long countByEstado(String estado);

    @Query("SELECT COALESCE(SUM(p.total),0) FROM Pedido p")

    BigDecimal obtenerVentasTotales();

    @Query("SELECT COALESCE(AVG(p.total), 0) FROM Pedido p WHERE p.cliente IS NOT NULL")
    BigDecimal obtenerTicketPromedioClientes();

    @Query("""
    SELECT COUNT(DISTINCT p.cliente.idCliente)
    FROM Pedido p
    WHERE p.cliente IS NOT NULL
    GROUP BY p.cliente.idCliente
    HAVING COUNT(p.idPedido) >= 2
""")
    List<Long> contarClientesFrecuentes();

    @Query("""
    SELECT CONCAT(p.cliente.nombre, ' ', COALESCE(p.cliente.apellido, '')), SUM(p.total)
    FROM Pedido p
    WHERE p.cliente IS NOT NULL
    GROUP BY p.cliente.idCliente, p.cliente.nombre, p.cliente.apellido
    ORDER BY SUM(p.total) DESC
""")
    List<Object[]> obtenerRankingClientesPorConsumo();
}