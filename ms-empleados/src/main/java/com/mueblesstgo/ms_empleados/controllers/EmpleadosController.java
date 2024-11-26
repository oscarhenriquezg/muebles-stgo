package com.mueblesstgo.ms_empleados.controllers;

import com.mueblesstgo.ms_empleados.entities.EmpleadoEntity;
import com.mueblesstgo.ms_empleados.repositories.EmpleadosRepositorie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadosController {
    private final EmpleadosRepositorie repositorie;

    public EmpleadosController(EmpleadosRepositorie repositorie) {
        this.repositorie = repositorie;
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoEntity>> obtenerEmpleados() {
        return ResponseEntity.ok(repositorie.findAll());
    }

    @GetMapping("/{rut}")
    public ResponseEntity<EmpleadoEntity> obtenerEmpleadoPorRut(@PathVariable String rut) {
        return repositorie.findAll()
                .stream()
                .filter(empleado -> empleado.getRut().equals(rut))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmpleadoEntity> crearEmpleado(@RequestBody EmpleadoEntity empleado) {
        return ResponseEntity.ok(repositorie.save(empleado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoEntity> actualizarEmpleado(
            @PathVariable Long id,
            @RequestBody EmpleadoEntity empleadoActualizado) {
        return repositorie.findById(id)
                .map(empleado -> {
                    empleado.setRut(empleadoActualizado.getRut());
                    empleado.setApellidos(empleadoActualizado.getApellidos());
                    empleado.setNombres(empleadoActualizado.getNombres());
                    empleado.setFechaNacimiento(empleadoActualizado.getFechaNacimiento());
                    empleado.setCategoria(empleadoActualizado.getCategoria());
                    empleado.setFechaIngreso(empleadoActualizado.getFechaIngreso());
                    return ResponseEntity.ok(repositorie.save(empleado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
        if (repositorie.existsById(id)) {
            repositorie.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
