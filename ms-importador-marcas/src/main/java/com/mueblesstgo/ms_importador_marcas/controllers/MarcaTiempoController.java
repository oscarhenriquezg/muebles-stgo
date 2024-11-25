package com.mueblesstgo.ms_importador_marcas.controllers;

import com.mueblesstgo.ms_importador_marcas.entities.MarcaTiempoEntity;
import com.mueblesstgo.ms_importador_marcas.services.MarcaTiempoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con marcas de tiempo.
 * Proporciona un punto de entrada REST para cargar y consultar marcas.
 */
@RestController
@RequestMapping("/api/marcas")
public class MarcaTiempoController {
    private final MarcaTiempoService service;

    public MarcaTiempoController(MarcaTiempoService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public String cargarArchivo(@RequestParam("file") MultipartFile file) {
        try {
            int registrosProcesados = service.procesarArchivo(new InputStreamReader(file.getInputStream()));
            return "Archivo procesado. Registros guardados: " + registrosProcesados;
        } catch (Exception e) {
            return "Error al procesar el archivo: " + e.getMessage();
        }
    }

    @GetMapping
    public List<MarcaTiempoEntity> obtenerMarcasPorEmpleado(
            @RequestParam String rutEmpleado,
            @RequestParam String mes) {
        return service.obtenerMarcas(rutEmpleado, mes);
    }
}
