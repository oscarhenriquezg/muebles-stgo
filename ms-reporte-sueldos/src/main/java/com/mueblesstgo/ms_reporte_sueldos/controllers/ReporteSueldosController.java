package com.mueblesstgo.ms_reporte_sueldos.controllers;

import com.mueblesstgo.ms_reporte_sueldos.services.ReporteSueldosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ReporteSueldosController {
    private final ReporteSueldosService service;

    public ReporteSueldosController(ReporteSueldosService service) {
        this.service = service;
    }

    @GetMapping("/api/reportes/planilla")
    public ResponseEntity<List<Map<String, Object>>> generarReporte(@RequestParam String mes) {
        return ResponseEntity.ok(service.generarReporte(mes));
    }
}
