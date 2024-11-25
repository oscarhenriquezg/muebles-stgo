package com.mueblesstgo.ms_justificativos.controllers;

import com.mueblesstgo.ms_justificativos.entities.JustificativoEntity;
import com.mueblesstgo.ms_justificativos.services.JustificativoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/justificativos")
public class JustificativoController {
    private final JustificativoService service;

    public JustificativoController(JustificativoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<JustificativoEntity> guardarJustificativo(@RequestBody JustificativoEntity justificativo) {
        return ResponseEntity.ok(service.guardarJustificativo(justificativo));
    }

    @GetMapping("/rut/{rutEmpleado}")
    public ResponseEntity<List<JustificativoEntity>> buscarPorRut(@PathVariable String rutEmpleado) {
        return ResponseEntity.ok(service.buscarPorRut(rutEmpleado));
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<JustificativoEntity>> buscarPorFechas(
            @RequestParam LocalDate inicio, @RequestParam LocalDate fin) {
        return ResponseEntity.ok(service.buscarPorRangoDeFechas(inicio, fin));
    }
}
