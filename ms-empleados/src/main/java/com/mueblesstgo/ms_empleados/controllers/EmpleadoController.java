package com.mueblesstgo.ms_empleados.controllers;

import com.mueblesstgo.ms_empleados.entities.EmpleadoEntity;
import com.mueblesstgo.ms_empleados.services.EmpleadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoService service;

    public EmpleadoController(EmpleadoService service) {
        this.service = service;
    }

    @GetMapping
    public List<EmpleadoEntity> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{rut}")
    public ResponseEntity<EmpleadoEntity> buscarPorRut(@PathVariable String rut) {
        return service.buscarPorRut(rut)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
