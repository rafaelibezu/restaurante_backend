package com.restaurante.controller;

import com.restaurante.dto.NotificacionRequestDTO;
import com.restaurante.dto.NotificacionResponseDTO;
import com.restaurante.service.NotificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;

    @GetMapping
    public List<NotificacionResponseDTO> listarNotificaciones() {
        return notificacionService.listarNotificaciones();
    }

    @GetMapping("/pendientes")
    public List<NotificacionResponseDTO> listarPendientes() {
        return notificacionService.listarPendientes();
    }

    @GetMapping("/{id}")
    public NotificacionResponseDTO obtenerPorId(@PathVariable Integer id) {
        return notificacionService.obtenerPorId(id);
    }

    @GetMapping("/tipo/{tipo}")
    public List<NotificacionResponseDTO> listarPorTipo(@PathVariable String tipo) {
        return notificacionService.listarPorTipo(tipo);
    }

    @GetMapping("/cliente/{idCliente}")
    public List<NotificacionResponseDTO> listarPorCliente(@PathVariable Integer idCliente) {
        return notificacionService.listarPorCliente(idCliente);
    }

    @PostMapping
    public NotificacionResponseDTO crearNotificacion(@RequestBody NotificacionRequestDTO dto) {
        return notificacionService.crearNotificacion(dto);
    }

    @PatchMapping("/{id}/estado")
    public NotificacionResponseDTO cambiarEstado(
            @PathVariable Integer id,
            @RequestParam String estado
    ) {
        return notificacionService.cambiarEstado(id, estado);
    }

    @DeleteMapping("/{id}")
    public void eliminarNotificacion(@PathVariable Integer id) {
        notificacionService.eliminarNotificacion(id);
    }

    @PostMapping("/generar-cumpleanos")
    public List<NotificacionResponseDTO> generarCumpleanos() {
        return notificacionService.generarNotificacionesCumpleanos();
    }

}