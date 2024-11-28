package com.mueblesstgo.ms_justificativos.controllers;

import com.mueblesstgo.ms_justificativos.entities.JustificativoEntity;
import com.mueblesstgo.ms_justificativos.services.JustificativoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/justificativos")
public class JustificativoController {

    private final JustificativoService service;

    public JustificativoController(JustificativoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> registrarJustificativo(@RequestBody JustificativoEntity justificativo) {
        try {
            JustificativoEntity nuevoJustificativo = service.registrarJustificativo(justificativo);
            return ResponseEntity.ok(nuevoJustificativo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<JustificativoEntity> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{rut}")
    public List<JustificativoEntity> listarPorRut(@PathVariable String rut) {
        return service.listarPorRut(rut);
    }

    @RestController
    @RequestMapping("/api/justificativos")
    public class JustificativosController {

        @GetMapping("/{rut}/{fecha}")
        public ResponseEntity<?> obtenerJustificativos(@PathVariable String rut, @PathVariable String fecha) {
            // Lógica del controlador
            return ResponseEntity.ok(true);
        }
    }

}
