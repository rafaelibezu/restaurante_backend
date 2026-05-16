package com.restaurante.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> manejarNoEncontrado(ResourceNotFoundException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("fecha", LocalDateTime.now());
        error.put("estado", 404);
        error.put("mensaje", ex.getMessage());
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> manejarValidaciones(MethodArgumentNotValidException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("fecha", LocalDateTime.now());
        error.put("estado", 400);
        error.put("mensaje", "Error de validación");

        Map<String, String> campos = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> campos.put(e.getField(), e.getDefaultMessage()));

        error.put("errores", campos);
        return error;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> manejarErrorGeneral(Exception ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("fecha", LocalDateTime.now());
        error.put("estado", 500);
        error.put("mensaje", "Error interno del servidor");
        return error;
    }
}