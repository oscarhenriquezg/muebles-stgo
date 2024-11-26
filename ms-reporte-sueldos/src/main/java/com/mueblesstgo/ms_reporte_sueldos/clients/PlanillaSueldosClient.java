package com.mueblesstgo.ms_reporte_sueldos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.Map;

@FeignClient(name = "ms-calculo-planilla-sueldos")
public interface PlanillaSueldosClient {
    @GetMapping("/api/planilla/mes/{mes}")
    List<Map<String, Object>> obtenerPlanillasPorMes(@PathVariable("mes") String mes);
}
