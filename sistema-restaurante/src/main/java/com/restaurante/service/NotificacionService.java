package com.restaurante.service;

import com.restaurante.dto.NotificacionRequestDTO;
import com.restaurante.dto.NotificacionResponseDTO;
import com.restaurante.entity.Cliente;
import com.restaurante.entity.Notificacion;
import com.restaurante.exception.ResourceNotFoundException;
import com.restaurante.mapper.NotificacionMapper;
import com.restaurante.repository.ClienteRepository;
import com.restaurante.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;
    private final ClienteRepository clienteRepository;
    private final NotificacionWebSocketService notificacionWebSocketService;

    public List<NotificacionResponseDTO> listarNotificaciones() {
        return notificacionRepository.findAll()
                .stream()
                .map(NotificacionMapper::toDTO)
                .toList();
    }

    public List<NotificacionResponseDTO> listarPendientes() {
        return notificacionRepository.findByEstado("Pendiente")
                .stream()
                .map(NotificacionMapper::toDTO)
                .toList();
    }

    public List<NotificacionResponseDTO> listarPorTipo(String tipo) {
        return notificacionRepository.findByTipo(tipo)
                .stream()
                .map(NotificacionMapper::toDTO)
                .toList();
    }

    public List<NotificacionResponseDTO> listarPorCliente(Integer idCliente) {
        return notificacionRepository.findByClienteIdCliente(idCliente)
                .stream()
                .map(NotificacionMapper::toDTO)
                .toList();
    }

    public NotificacionResponseDTO obtenerPorId(Integer id) {
        Notificacion notificacion = notificacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notificación no encontrada"));

        return NotificacionMapper.toDTO(notificacion);
    }

    public NotificacionResponseDTO crearNotificacion(NotificacionRequestDTO dto) {
        Cliente cliente = null;

        if (dto.getIdCliente() != null) {
            cliente = clienteRepository.findById(dto.getIdCliente())
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        }

        Notificacion notificacion = Notificacion.builder()
                .cliente(cliente)
                .titulo(dto.getTitulo())
                .mensaje(dto.getMensaje())
                .tipo(dto.getTipo())
                .estado(dto.getEstado() != null ? dto.getEstado() : "Pendiente")
                .fechaGeneracion(LocalDateTime.now())
                .fechaProgramada(dto.getFechaProgramada())
                .build();

        Notificacion notificacionGuardada = notificacionRepository.save(notificacion);

        NotificacionResponseDTO response = NotificacionMapper.toDTO(notificacionGuardada);

        notificacionWebSocketService.enviarNotificacion(response);

        return response;
    }

    public NotificacionResponseDTO cambiarEstado(Integer id, String estado) {
        Notificacion notificacion = notificacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notificación no encontrada"));

        notificacion.setEstado(estado);

        return NotificacionMapper.toDTO(notificacionRepository.save(notificacion));
    }

    public void eliminarNotificacion(Integer id) {
        Notificacion notificacion = notificacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notificación no encontrada"));

        notificacion.setEstado("Descartada");
        notificacionRepository.save(notificacion);
    }

    public List<NotificacionResponseDTO> generarNotificacionesCumpleanos() {

        LocalDate hoy = LocalDate.now();

        List<Cliente> cumpleaneros = clienteRepository.buscarCumpleaneros(
                hoy.getMonthValue(),
                hoy.getDayOfMonth()
        );

        List<Notificacion> nuevas = cumpleaneros.stream()
                .map(cliente -> {

                    Notificacion notificacion = Notificacion.builder()
                            .cliente(cliente)
                            .titulo("Cumpleaños de cliente")
                            .mensaje("Hoy es cumpleaños de " +
                                    cliente.getNombre() + ". Puedes enviar promoción especial.")
                            .tipo("Cumpleaños")
                            .estado("Pendiente")
                            .fechaGeneracion(LocalDateTime.now())
                            .build();

                    return notificacionRepository.save(notificacion);
                })
                .toList();

        return nuevas.stream()
                .map(NotificacionMapper::toDTO)
                .toList();
    }
}