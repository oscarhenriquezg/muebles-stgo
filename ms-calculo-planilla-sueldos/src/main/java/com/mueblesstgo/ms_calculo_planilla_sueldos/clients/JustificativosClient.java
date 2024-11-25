package com.mueblesstgo.ms_calculo_planilla_sueldos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ms-justificativos")
public interface JustificativosClient {
    @GetMapping("/api/justificativos/rut/{rutEmpleado}")
    List<Object> obtenerJustificativosPorRut(@PathVariable String rutEmpleado);
}
