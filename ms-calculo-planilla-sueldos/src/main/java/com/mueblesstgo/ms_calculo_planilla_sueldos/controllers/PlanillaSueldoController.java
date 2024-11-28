package com.mueblesstgo.ms_calculo_planilla_sueldos.controllers;

import com.mueblesstgo.ms_calculo_planilla_sueldos.dto.PlanillaRequest;
import com.mueblesstgo.ms_calculo_planilla_sueldos.dto.PlanillaSueldoDTO;
import com.mueblesstgo.ms_calculo_planilla_sueldos.entities.PlanillaSueldoEntity;
import com.mueblesstgo.ms_calculo_planilla_sueldos.services.PlanillaSueldoService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planilla")
public class PlanillaSueldoController {

    private final PlanillaSueldoService service;

    public PlanillaSueldoController(PlanillaSueldoService service) {
        this.service = service;
    }
    @GetMapping("/todos")

    public ResponseEntity<List<PlanillaSueldoDTO>> calcularPlanillasParaTodos(@RequestParam int mes, @RequestParam int anio) {
        List<PlanillaSueldoDTO> planillas = service.calcularPlanillasParaTodos(mes, anio);
        return ResponseEntity.ok(planillas);
    }

    @RestController
    @RequestMapping("/api/planillas")
    public class PlanillaController {
        private final PlanillaSueldoService planillaSueldoService;

        public PlanillaController(PlanillaSueldoService planillaSueldoService) {
            this.planillaSueldoService = planillaSueldoService;
        }


    }


//    @PostMapping
//    public ResponseEntity<PlanillaSueldoEntity> generarPlanilla(@RequestParam String rut, @RequestParam int mes, @RequestParam int anio) {
//        return ResponseEntity.ok(service.calcularPlanilla(rut, mes, anio));
//    }


//    @PostMapping
//    public ResponseEntity<PlanillaSueldoEntity> generarPlanilla(
//            @RequestParam String rut,
//            @RequestParam int mes,
//            @RequestParam int anio
//    ) {
//        PlanillaSueldoEntity planilla = service.calcularPlanilla(rut, mes, anio);
//        return ResponseEntity.ok(planilla);
//    }

    @PostMapping
    public ResponseEntity<PlanillaSueldoEntity> generarPlanilla(@RequestBody PlanillaRequest request) {
        PlanillaSueldoEntity planilla = service.calcularPlanilla(
                request.getRut(), request.getMes(), request.getAnio()
        );
        return ResponseEntity.ok(planilla);
    }




    @GetMapping
    public List<PlanillaSueldoEntity> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{rut}")
    public List<PlanillaSueldoEntity> listarPorRut(@PathVariable String rut) {
        return service.listarPorRut(rut);
    }
}