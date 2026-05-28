package com.restaurante.service;

import com.restaurante.dto.NotificacionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacionWebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public void enviarNotificacion(NotificacionResponseDTO notificacion) {
        messagingTemplate.convertAndSend("/topic/notificaciones", notificacion);
    }
}