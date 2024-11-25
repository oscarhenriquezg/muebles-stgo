package com.mueblesstgo.ms_calculo_planilla_sueldos.controllers;

import com.mueblesstgo.ms_calculo_planilla_sueldos.entities.PlanillaSueldoEntity;
import com.mueblesstgo.ms_calculo_planilla_sueldos.services.PlanillaSueldoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/planilla")
public class PlanillaSueldoController {
    private final PlanillaSueldoService service;

    public PlanillaSueldoController(PlanillaSueldoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PlanillaSueldoEntity> generarPlanilla(@RequestBody PlanillaSueldoEntity planilla) {
        return ResponseEntity.ok(service.generarPlanilla(planilla.getRutEmpleado(), planilla.getMes()));
    }
}
