package com.mueblesstgo.ms_autorizacion_hhee.controllers;

import com.mueblesstgo.ms_autorizacion_hhee.entities.AutorizacionEntity;
import com.mueblesstgo.ms_autorizacion_hhee.services.AutorizacionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/autorizaciones")
public class AutorizacionController {
    private final AutorizacionService service;

    public AutorizacionController(AutorizacionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AutorizacionEntity> registrarAutorizacion(@RequestBody AutorizacionEntity autorizacion) {
        return ResponseEntity.ok(service.registrarAutorizacion(autorizacion));
    }

    @GetMapping("/rut/{rutEmpleado}")
    public ResponseEntity<List<AutorizacionEntity>> buscarPorRut(@PathVariable String rutEmpleado) {
        return ResponseEntity.ok(service.buscarPorRut(rutEmpleado));
    }

//    @GetMapping("/fecha/{fecha}")
//    public ResponseEntity<List<AutorizacionEntity>> buscarPorFecha(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
//        return ResponseEntity.ok(service.buscarPorFecha(fecha));
//    }

    @GetMapping
    public ResponseEntity<List<AutorizacionEntity>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }
}
