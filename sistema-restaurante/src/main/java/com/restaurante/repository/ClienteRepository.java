package com.restaurante.repository;

import com.restaurante.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findByTelefono(String telefono);

    Optional<Cliente> findByCorreo(String correo);

    Long countByEstado(Boolean estado);
    
    @Query("""
    SELECT c
    FROM Cliente c
    WHERE MONTH(c.fechaNacimiento) = :mes
    AND DAY(c.fechaNacimiento) = :dia
    AND c.estado = true
""")
    List<Cliente> buscarCumpleaneros(
            @Param("mes") int mes,
            @Param("dia") int dia
    );
}