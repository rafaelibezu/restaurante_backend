package com.restaurante.repository;

import com.restaurante.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    List<Reserva> findByEstado(String estado);

    List<Reserva> findByFechaReserva(LocalDate fechaReserva);

    Long countByEstado(String estado);

    @Query(value = """
    SELECT CASE 
        WHEN COUNT(*) > 0 THEN CAST(1 AS bit) 
        ELSE CAST(0 AS bit) 
    END
    FROM Reserva
    WHERE id_mesa = :idMesa
      AND fecha_reserva = :fechaReserva
      AND CAST(hora_reserva AS time) = CAST(:horaReserva AS time)
      AND estado IN ('Pendiente', 'Confirmada')
    """, nativeQuery = true)
    boolean existeReservaActivaEnMesa(
            @Param("idMesa") Integer idMesa,
            @Param("fechaReserva") LocalDate fechaReserva,
            @Param("horaReserva") LocalTime horaReserva
    );

}