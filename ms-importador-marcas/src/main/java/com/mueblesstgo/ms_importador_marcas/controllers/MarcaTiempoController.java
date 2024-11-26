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

    /**
     * Constructor que inyecta el servicio de MarcaTiempo.
     * @param service Servicio que maneja la lógica de negocio para las marcas de tiempo.
     */
    public MarcaTiempoController(MarcaTiempoService service) {
        this.service = service;
    }

    /**
     * Endpoint para cargar un archivo de marcas de tiempo.
     * @param file Archivo a cargar.
     * @return Mensaje indicando el resultado del proceso.
     */
    @PostMapping("/upload")
    public String cargarArchivo(@RequestParam("file") MultipartFile file) {
        try {
            int registrosProcesados = service.procesarArchivo(new InputStreamReader(file.getInputStream()));
            return "Archivo procesado. Registros guardados: " + registrosProcesados;
        } catch (Exception e) {
            return "Error al procesar el archivo: " + e.getMessage();
        }
    }

    /**
     * Endpoint para obtener las marcas de tiempo de un empleado en un mes específico.
     * @param rutEmpleado RUT del empleado.
     * @param mes Mes para filtrar las marcas de tiempo (en formato MM-yyyy).
     * @return Lista de entidades de marcas de tiempo correspondientes al empleado y mes proporcionados.
     */
    @GetMapping
    public List<MarcaTiempoEntity> obtenerMarcasPorEmpleado(
            @RequestParam String rutEmpleado,
            @RequestParam String mes) {
        return service.obtenerMarcas(rutEmpleado, mes);
    }
}