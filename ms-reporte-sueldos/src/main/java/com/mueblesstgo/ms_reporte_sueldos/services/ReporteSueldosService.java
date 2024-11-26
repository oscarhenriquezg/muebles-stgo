package com.mueblesstgo.ms_reporte_sueldos.services;

import com.mueblesstgo.ms_reporte_sueldos.clients.EmpleadosClient;
import com.mueblesstgo.ms_reporte_sueldos.clients.PlanillaSueldosClient;
import com.mueblesstgo.ms_reporte_sueldos.dtos.EmpleadoDTO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReporteSueldosService {
    private final PlanillaSueldosClient planillaClient;
    private final EmpleadosClient empleadosClient;

    public ReporteSueldosService(PlanillaSueldosClient planillaClient, EmpleadosClient empleadosClient) {
        this.planillaClient = planillaClient;
        this.empleadosClient = empleadosClient;
    }

    public List<Map<String, Object>> generarReporte(String mes) {
        // Obtener planillas para el mes especificado
        List<Map<String, Object>> planillas = planillaClient.obtenerPlanillasPorMes(mes);

        // Obtener todos los empleados
        List<EmpleadoDTO> empleados = empleadosClient.obtenerTodosLosEmpleados();

        // Crear un mapa de planillas por RUT para acceso rápido
        Map<String, Map<String, Object>> planillasPorRut = planillas.stream()
                .collect(Collectors.toMap(
                        planilla -> (String) planilla.get("rutEmpleado"),
                        planilla -> planilla
                ));

        // Combinar datos de empleados y planillas
        return empleados.stream().map(empleado -> {
            Map<String, Object> reporte = new HashMap<>();

            // Información del empleado
            reporte.put("rut", empleado.getRut());
            reporte.put("nombres", empleado.getNombres());
            reporte.put("apellidos", empleado.getApellidos());
            reporte.put("categoria", empleado.getCategoria());
            reporte.put("fechaIngreso", empleado.getFechaIngreso());

            // Información de la planilla (si existe)
            Map<String, Object> planilla = planillasPorRut.get(empleado.getRut());
            if (planilla != null) {
                reporte.putAll(planilla);
            } else {
                // Si no hay planilla, agregar valores por defecto o indicarlo
                reporte.put("horasTrabajadas", 0);
                reporte.put("horasExtras", 0);
                reporte.put("bonificaciones", 0);
                reporte.put("descuentos", 0);
                reporte.put("sueldoLiquido", 0);
            }

            return reporte;
        }).collect(Collectors.toList());
    }
}
