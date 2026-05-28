package com.restaurante.controller;

import com.restaurante.dto.DashboardCrmDTO;
import com.restaurante.service.DashboardCrmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard/crm")
@RequiredArgsConstructor
public class DashboardCrmController {

    private final DashboardCrmService dashboardCrmService;

    @GetMapping
    public DashboardCrmDTO obtenerDashboardCrm() {
        return dashboardCrmService.obtenerDashboardCrm();
    }
}