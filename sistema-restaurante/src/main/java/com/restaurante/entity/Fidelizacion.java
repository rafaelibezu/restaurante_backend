package com.restaurante.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Fidelizacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fidelizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fidelizacion")
    private Integer idFidelizacion;

    @OneToOne
    @JoinColumn(name = "id_cliente", nullable = false, unique = true)
    private Cliente cliente;

    @Column(name = "puntos", nullable = false)
    private Integer puntos;

    @Column(name = "nivel", nullable = false, length = 20)
    private String nivel;

    @Column(name = "descuento_disponible", nullable = false, precision = 5, scale = 2)
    private BigDecimal descuentoDisponible;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;
}