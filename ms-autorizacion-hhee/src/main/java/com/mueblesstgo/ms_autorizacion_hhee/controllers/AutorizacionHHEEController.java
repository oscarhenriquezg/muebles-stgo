package com.mueblesstgo.ms_autorizacion_hhee.controllers;

import com.mueblesstgo.ms_autorizacion_hhee.entities.AutorizacionHHEEEntity;
import com.mueblesstgo.ms_autorizacion_hhee.services.AutorizacionHHEEService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/autorizaciones")
public class AutorizacionHHEEController {

    private final AutorizacionHHEEService service;

    public AutorizacionHHEEController(AutorizacionHHEEService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> registrarAutorizacion(@RequestBody AutorizacionHHEEEntity autorizacion) {
        try {
            AutorizacionHHEEEntity nuevaAutorizacion = service.registrarAutorizacion(autorizacion);
            return ResponseEntity.ok(nuevaAutorizacion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<AutorizacionHHEEEntity> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{rut}")
    public List<AutorizacionHHEEEntity> listarPorRut(@PathVariable String rut) {
        return service.listarPorRut(rut);
    }

    @GetMapping("/{rut}/{fecha}")
    public ResponseEntity<?> verificarAutorizacion(@PathVariable String rut, @PathVariable String fecha) {
        LocalDate fechaParseada = LocalDate.parse(fecha);
        boolean autorizado = service.verificarAutorizacion(rut, fechaParseada);
        return ResponseEntity.ok(autorizado);
    }
}