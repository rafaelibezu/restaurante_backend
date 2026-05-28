package com.restaurante.repository;

import com.restaurante.entity.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PromocionRepository extends JpaRepository<Promocion, Integer> {

    List<Promocion> findByEstado(Boolean estado);

    List<Promocion> findByFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(
            LocalDate fechaInicio,
            LocalDate fechaFin
    );

    Long countByEstado(Boolean estado);
}