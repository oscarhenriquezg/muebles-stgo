package com.mueblesstgo.ms_reporte_sueldos.services;

import com.mueblesstgo.ms_reporte_sueldos.dto.EmpleadoDTO;
import com.mueblesstgo.ms_reporte_sueldos.dto.PlanillaSueldoDTO;
import com.mueblesstgo.ms_reporte_sueldos.dto.ReporteDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    private final RestTemplate restTemplate;

    public ReporteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ReporteDTO> obtenerReporteCompleto(int mes, int anio) {
        String empleadosUrl = "http://localhost:8084/api/empleados";
        String planillasUrl = String.format("http://localhost:8083/api/planillas?mes=%d&anio=%d", mes, anio);

        // Obtener empleados y planillas desde los microservicios correspondientes
        List<EmpleadoDTO> empleados = List.of(restTemplate.getForObject(empleadosUrl, EmpleadoDTO[].class));
        List<PlanillaSueldoDTO> planillas = List.of(restTemplate.getForObject(planillasUrl, PlanillaSueldoDTO[].class));

        // Mapear empleados y sus planillas al formato de reporte
        return empleados.stream().map(empleado -> {
            PlanillaSueldoDTO planilla = planillas.stream()
                    .filter(p -> p.getRutEmpleado().equals(empleado.getRut()))
                    .findFirst()
                    .orElse(null);

            if (planilla == null) {
                return null; // Empleado sin planilla, se puede manejar si es necesario
            }

            return new ReporteDTO(
                    empleado.getRut(),
                    empleado.getNombres() + " " + empleado.getApellidos(),
                    empleado.getCategoria(),
                    calcularAniosServicio(empleado.getFechaIngreso()),
                    planilla.getSueldoBase(),
                    planilla.getBonificacion(),
                    planilla.getHorasExtras(),
                    planilla.getDescuentos(),
                    planilla.getSueldoBruto(),
                    planilla.getCotizacionPrevisional(),
                    planilla.getCotizacionSalud(),
                    planilla.getSueldoNeto()
            );
        }).filter(reporte -> reporte != null).collect(Collectors.toList());
    }

    private int calcularAniosServicio(String fechaIngreso) {
        // Convertir la fecha de ingreso (String) a LocalDate
        LocalDate fechaIngresoDate = LocalDate.parse(fechaIngreso);

        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Calcular la diferencia en años entre la fecha de ingreso y la actual
        return Period.between(fechaIngresoDate, fechaActual).getYears();
    }
}
