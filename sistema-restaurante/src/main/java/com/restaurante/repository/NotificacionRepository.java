package com.restaurante.repository;

import com.restaurante.entity.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {

    List<Notificacion> findByEstado(String estado);

    List<Notificacion> findByTipo(String tipo);

    List<Notificacion> findByClienteIdCliente(Integer idCliente);

    Long countByEstado(String estado);

}