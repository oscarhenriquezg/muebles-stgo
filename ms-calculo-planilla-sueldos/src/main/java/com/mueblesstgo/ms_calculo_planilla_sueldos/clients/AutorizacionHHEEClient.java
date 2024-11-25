package com.mueblesstgo.ms_calculo_planilla_sueldos.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "ms-autorizacion-hhee")
public interface AutorizacionHHEEClient {
    @GetMapping("/api/autorizaciones/rut/{rutEmpleado}")
    List<Object> obtenerAutorizacionesPorRut(@PathVariable String rutEmpleado);
}
