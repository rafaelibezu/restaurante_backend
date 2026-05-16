package com.restaurante.controller;

import com.restaurante.dto.DashboardResponseDTO;
import com.restaurante.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public DashboardResponseDTO obtenerDashboard() {
        return dashboardService.obtenerDashboard();
    }
}