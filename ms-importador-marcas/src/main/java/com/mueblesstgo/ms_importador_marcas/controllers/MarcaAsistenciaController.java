package com.mueblesstgo.ms_importador_marcas.controllers;

import com.mueblesstgo.ms_importador_marcas.entities.MarcaAsistenciaEntity;
import com.mueblesstgo.ms_importador_marcas.services.MarcaAsistenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
public class MarcaAsistenciaController {

    private final MarcaAsistenciaService service;

    public MarcaAsistenciaController(MarcaAsistenciaService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> cargarArchivo(@RequestParam("file") MultipartFile file) {
        List<String> errores = service.procesarArchivo(file);
        if (errores.isEmpty()) {
            return ResponseEntity.ok("Archivo procesado correctamente");
        } else {
            return ResponseEntity.badRequest().body(errores);
        }
    }

    @GetMapping
    public List<MarcaAsistenciaEntity> listarTodasLasMarcas() {
        return service.listarTodasLasMarcas();
    }

    @GetMapping("/{rut}/{mes}/{anio}")
    public List<MarcaAsistenciaEntity> listarMarcasPorRutYMes(@PathVariable String rut, @PathVariable int mes, @PathVariable int anio) {
        return service.listarMarcasPorRutYMes(rut, mes, anio);
    }
}
