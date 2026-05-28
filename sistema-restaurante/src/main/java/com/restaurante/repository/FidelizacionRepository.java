package com.restaurante.repository;

import com.restaurante.entity.Fidelizacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FidelizacionRepository extends JpaRepository<Fidelizacion, Integer> {

    Optional<Fidelizacion> findByClienteIdCliente(Integer idCliente);
}