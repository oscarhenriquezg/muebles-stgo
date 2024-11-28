package com.mueblesstgo.ms_reporte_sueldos.controllers;

import com.mueblesstgo.ms_reporte_sueldos.dto.ReporteDTO;
import com.mueblesstgo.ms_reporte_sueldos.services.ReporteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReporteController {
    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping("/api/reportes")
    public List<ReporteDTO> obtenerReporte(@RequestParam int mes, @RequestParam int anio) {
        System.out.println("Mes: " + mes + ", Año: " + anio);
        return reporteService.obtenerReporteCompleto(mes, anio);
    }
}
