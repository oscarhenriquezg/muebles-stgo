package com.mueblesstgo.ms_calculo_planilla_sueldos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ms-importador-marcas")
public interface ImportadorMarcasClient {
    @GetMapping("/api/marcas")
    List<Object> obtenerMarcasPorEmpleado(@RequestParam String rutEmpleado, @RequestParam String mes);
}
