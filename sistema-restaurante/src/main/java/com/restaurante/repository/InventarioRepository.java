package com.restaurante.repository;

import com.restaurante.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventarioRepository extends JpaRepository<Inventario, Integer> {

    List<Inventario> findByCantidadActualLessThanEqual(Integer stockMinimo);

    @Query("""
       SELECT COUNT(i)
       FROM Inventario i
       WHERE i.cantidadActual <= i.stockMinimo
       """)
    Long contarStockBajo();
}