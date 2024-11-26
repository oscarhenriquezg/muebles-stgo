package com.mueblesstgo.ms_reporte_sueldos.clients;

import com.mueblesstgo.ms_reporte_sueldos.dtos.EmpleadoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "ms-empleados")
public interface EmpleadosClient {
    @GetMapping("/api/empleados")
    List<EmpleadoDTO> obtenerTodosLosEmpleados();
}
