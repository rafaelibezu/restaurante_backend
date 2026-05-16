package com.restaurante.repository;

import com.restaurante.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByEstado(String estado);

    Long countByEstado(String estado);

    @Query("SELECT COALESCE(SUM(p.total),0) FROM Pedido p")

    BigDecimal obtenerVentasTotales();
}