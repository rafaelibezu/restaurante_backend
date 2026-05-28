package com.restaurante.service;
import com.restaurante.mapper.NotificacionMapper;
import com.restaurante.dto.ReservaRequestDTO;
import com.restaurante.dto.ReservaResponseDTO;
import com.restaurante.entity.*;
import com.restaurante.exception.ResourceNotFoundException;
import com.restaurante.mapper.ReservaMapper;
import com.restaurante.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ClienteRepository clienteRepository;
    private final MesaRepository mesaRepository;
    private final UsuarioRepository usuarioRepository;
    private final NotificacionRepository notificacionRepository;
    private final NotificacionWebSocketService notificacionWebSocketService;

    public List<ReservaResponseDTO> listarReservas() {
        return reservaRepository.findAll()
                .stream()
                .map(ReservaMapper::toDTO)
                .toList();
    }

    public List<ReservaResponseDTO> listarPorEstado(String estado) {
        return reservaRepository.findByEstado(estado)
                .stream()
                .map(ReservaMapper::toDTO)
                .toList();
    }

    public List<ReservaResponseDTO> listarPorFecha(LocalDate fecha) {
        return reservaRepository.findByFechaReserva(fecha)
                .stream()
                .map(ReservaMapper::toDTO)
                .toList();
    }

    public ReservaResponseDTO obtenerReservaPorId(Integer id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada"));

        return ReservaMapper.toDTO(reserva);
    }

    public ReservaResponseDTO crearReserva(ReservaRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        Mesa mesa = mesaRepository.findById(dto.getIdMesa())
                .orElseThrow(() -> new ResourceNotFoundException("Mesa no encontrada"));

        if (dto.getFechaReserva().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("No se puede registrar una reserva en una fecha pasada");
        }

        LocalTime horaApertura = LocalTime.of(12, 0);
        LocalTime horaCierre = LocalTime.of(23, 0);

        if (dto.getHoraReserva().isBefore(horaApertura) || dto.getHoraReserva().isAfter(horaCierre)) {
            throw new IllegalArgumentException("La reserva debe estar dentro del horario de atención: 12:00 a 23:00");
        }

        if (dto.getCantidadPersonas() > mesa.getCapacidad()) {
            throw new IllegalArgumentException("La cantidad de personas supera la capacidad de la mesa seleccionada");
        }

        boolean mesaReservada = reservaRepository.existeReservaActivaEnMesa(
                mesa.getIdMesa(),
                dto.getFechaReserva(),
                dto.getHoraReserva()
        );

        if (mesaReservada) {
            throw new IllegalArgumentException("La mesa ya tiene una reserva pendiente o confirmada en esa fecha y hora");
        }

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Reserva reserva = Reserva.builder()
                .cliente(cliente)
                .mesa(mesa)
                .usuario(usuario)
                .fechaReserva(dto.getFechaReserva())
                .horaReserva(dto.getHoraReserva())
                .cantidadPersonas(dto.getCantidadPersonas())
                .estado(dto.getEstado() != null ? dto.getEstado() : "Pendiente")
                .observacion(dto.getObservacion())
                .fechaCreacion(LocalDateTime.now())
                .build();

        Reserva reservaGuardada = reservaRepository.save(reserva);

        Notificacion notificacion = Notificacion.builder()
                .cliente(cliente)
                .titulo("Nueva reserva pendiente")
                .mensaje(cliente.getNombre() + " solicitó una reserva para el "
                        + reservaGuardada.getFechaReserva() + " a las "
                        + reservaGuardada.getHoraReserva())
                .tipo("Reserva")
                .estado("Pendiente")
                .fechaGeneracion(LocalDateTime.now())
                .fechaProgramada(LocalDateTime.of(
                        reservaGuardada.getFechaReserva(),
                        reservaGuardada.getHoraReserva()
                ))
                .build();

        Notificacion notificacionGuardada = notificacionRepository.save(notificacion);

        notificacionWebSocketService.enviarNotificacion(
                NotificacionMapper.toDTO(notificacionGuardada)
        );

        return ReservaMapper.toDTO(reservaGuardada);
    }

    public ReservaResponseDTO actualizarReserva(Integer id, ReservaRequestDTO dto) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada"));

        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        Mesa mesa = mesaRepository.findById(dto.getIdMesa())
                .orElseThrow(() -> new ResourceNotFoundException("Mesa no encontrada"));

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        reserva.setCliente(cliente);
        reserva.setMesa(mesa);
        reserva.setUsuario(usuario);
        reserva.setFechaReserva(dto.getFechaReserva());
        reserva.setHoraReserva(dto.getHoraReserva());
        reserva.setCantidadPersonas(dto.getCantidadPersonas());
        reserva.setEstado(dto.getEstado() != null ? dto.getEstado() : reserva.getEstado());
        reserva.setObservacion(dto.getObservacion());

        return ReservaMapper.toDTO(reservaRepository.save(reserva));
    }

    public void eliminarReserva(Integer id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada"));

        reserva.setEstado("Cancelada");
        reservaRepository.save(reserva);
    }
}