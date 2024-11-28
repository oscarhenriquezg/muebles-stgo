package com.mueblesstgo.ms_calculo_planilla_sueldos.services;

import com.mueblesstgo.ms_calculo_planilla_sueldos.entities.PlanillaSueldoEntity;
import com.mueblesstgo.ms_calculo_planilla_sueldos.repositories.PlanillaSueldoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.mueblesstgo.ms_calculo_planilla_sueldos.dto.EmpleadoDTO;
import org.springframework.http.ResponseEntity;
import com.mueblesstgo.ms_calculo_planilla_sueldos.dto.MarcaAsistenciaDTO;


import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PlanillaSueldoService {
    private static final Logger log = LoggerFactory.getLogger(PlanillaSueldoService.class);

    private final PlanillaSueldoRepository repository;
    private final RestTemplate restTemplate;

    public PlanillaSueldoService(PlanillaSueldoRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public BigDecimal calcularSueldoBase(String categoria) {
        switch (categoria) {
            case "A":
                return new BigDecimal("1700000"); // Sueldo para categoría A
            case "B":
                return new BigDecimal("1200000"); // Sueldo para categoría B
            case "C":
                return new BigDecimal("800000"); // Sueldo para categoría C
            default:
                throw new IllegalArgumentException("Categoría inválida: " + categoria);
        }
    }
    private void setSueldoBase(EmpleadoDTO empleado) {
        if (empleado.getSueldoBase() == null) {
            switch (empleado.getCategoria()) {
                case "A":
                    empleado.setSueldoBase(BigDecimal.valueOf(1700000));
                    break;
                case "B":
                    empleado.setSueldoBase(BigDecimal.valueOf(1200000));
                    break;
                case "C":
                    empleado.setSueldoBase(BigDecimal.valueOf(800000));
                    break;
                default:
                    throw new IllegalArgumentException("Categoría desconocida: " + empleado.getCategoria());
            }
        }
    }

    public PlanillaSueldoEntity calcularPlanilla(String rutEmpleado, int mes, int anio) {
        String empleadoUrl = "http://localhost:8084/api/empleados/{rut}";
        EmpleadoDTO empleado = restTemplate.getForObject(empleadoUrl, EmpleadoDTO.class, rutEmpleado);

        setSueldoBase(empleado); // Asegura que el sueldo base esté definido

        BigDecimal sueldoBase = empleado.getSueldoBase();
        BigDecimal bonificacion = calcularBonificacion(empleado.getFechaIngreso(), mes, anio);
//        BigDecimal horasExtras = calcularHorasExtras(rutEmpleado, mes, anio);
        BigDecimal horasExtras = calcularHorasExtras(rutEmpleado, mes, anio, empleado);
        BigDecimal descuentos = calcularDescuentos(rutEmpleado, mes, anio);

        log.info("Valores: sueldoBase={}, bonificacion={}, horasExtras={}, descuentos={}",
                sueldoBase, bonificacion, horasExtras, descuentos);

        BigDecimal sueldoBruto = sueldoBase.add(bonificacion).add(horasExtras).subtract(descuentos);
        BigDecimal cotizacionPrevisional = sueldoBruto.multiply(BigDecimal.valueOf(0.10));
        BigDecimal cotizacionSalud = sueldoBruto.multiply(BigDecimal.valueOf(0.08));
        BigDecimal sueldoNeto = sueldoBruto.subtract(cotizacionPrevisional).subtract(cotizacionSalud);

        PlanillaSueldoEntity planilla = new PlanillaSueldoEntity();
        planilla.setRutEmpleado(rutEmpleado);
        planilla.setMes(mes);
        planilla.setAnio(anio);
        planilla.setSueldoBase(sueldoBase);
        planilla.setBonificacion(bonificacion);
        planilla.setHorasExtras(horasExtras);
        planilla.setDescuentos(descuentos);
        planilla.setSueldoBruto(sueldoBruto);
        planilla.setCotizacionPrevisional(cotizacionPrevisional);
        planilla.setCotizacionSalud(cotizacionSalud);
        planilla.setSueldoNeto(sueldoNeto);

        return repository.save(planilla);
    }

    private BigDecimal calcularBonificacion(LocalDate fechaIngreso, int mes, int anio) {
        int añosServicio = anio - fechaIngreso.getYear();
        if (añosServicio < 5) return BigDecimal.ZERO;
        if (añosServicio < 10) return BigDecimal.valueOf(0.05).multiply(BigDecimal.valueOf(1700000));
        if (añosServicio < 15) return BigDecimal.valueOf(0.08).multiply(BigDecimal.valueOf(1700000));
        if (añosServicio < 20) return BigDecimal.valueOf(0.11).multiply(BigDecimal.valueOf(1700000));
        if (añosServicio < 25) return BigDecimal.valueOf(0.14).multiply(BigDecimal.valueOf(1700000));
        return BigDecimal.valueOf(0.17).multiply(BigDecimal.valueOf(1700000));
    }

//    private BigDecimal calcularHorasExtras(String rutEmpleado, int mes, int anio) {
//        String url = "http://localhost:8080/api/marcas/{rut}/{mes}/{anio}";
//        ResponseEntity<MarcaAsistenciaDTO[]> response = restTemplate.getForEntity(
//                url, MarcaAsistenciaDTO[].class, rutEmpleado, mes, anio);
//
//        MarcaAsistenciaDTO[] marcas = response.getBody();
//        BigDecimal totalHorasExtras = BigDecimal.ZERO;
//
//        for (MarcaAsistenciaDTO marca : marcas) {
//            if ("salida".equals(marca.getTipoMarca()) && marca.getHora().isAfter(LocalTime.of(18, 0))) {
//                String authUrl = "http://localhost:8082/api/autorizaciones/{rut}/{fecha}";
//                Boolean autorizado = restTemplate.getForObject(
//                        authUrl, Boolean.class, rutEmpleado, marca.getFecha());
//
//                if (Boolean.TRUE.equals(autorizado)) {
//                    long horasExtras = Duration.between(LocalTime.of(18, 0), marca.getHora()).toHours();
//                    totalHorasExtras = totalHorasExtras.add(
//                            BigDecimal.valueOf(horasExtras * obtenerPagoPorHoraExtra(empleado.getCategoria()))
//                    );
//                }
//            }
//        }
//
//        return totalHorasExtras;
//    }

    private BigDecimal calcularHorasExtras(String rutEmpleado, int mes, int anio, EmpleadoDTO empleado) {
        String url = "http://localhost:8080/api/marcas/{rut}/{mes}/{anio}";
        ResponseEntity<MarcaAsistenciaDTO[]> response = restTemplate.getForEntity(
                url, MarcaAsistenciaDTO[].class, rutEmpleado, mes, anio);

        MarcaAsistenciaDTO[] marcas = response.getBody();
        BigDecimal totalHorasExtras = BigDecimal.ZERO;

        for (MarcaAsistenciaDTO marca : marcas) {
            if ("salida".equals(marca.getTipoMarca()) && marca.getHora().isAfter(LocalTime.of(18, 0))) {
                String authUrl = "http://localhost:8082/api/autorizaciones/{rut}/{fecha}";
                Boolean autorizado = restTemplate.getForObject(
                        authUrl, Boolean.class, rutEmpleado, marca.getFecha());

                if (Boolean.TRUE.equals(autorizado)) {
                    long horasExtras = Duration.between(LocalTime.of(18, 0), marca.getHora()).toHours();
                    totalHorasExtras = totalHorasExtras.add(
                            BigDecimal.valueOf(horasExtras).multiply(obtenerPagoPorHoraExtra(empleado.getCategoria()))
                    );
                }
            }
        }

        return totalHorasExtras;
    }





//    private BigDecimal calcularDescuentos(String rutEmpleado, int mes, int anio) {
//        String url = "http://localhost:8080/api/marcas/{rut}/{mes}/{anio}";
//        ResponseEntity<MarcaAsistenciaDTO[]> response = restTemplate.getForEntity(
//                url, MarcaAsistenciaDTO[].class, rutEmpleado, mes, anio);
//
//        MarcaAsistenciaDTO[] marcas = response.getBody();
//        BigDecimal totalDescuentos = BigDecimal.ZERO;
//
//        LocalDate inicioMes = LocalDate.of(anio, mes, 1);
//        LocalDate finMes = inicioMes.withDayOfMonth(inicioMes.lengthOfMonth());
//
//        for (LocalDate fecha = inicioMes; !fecha.isAfter(finMes); fecha = fecha.plusDays(1)) {
//            if (fecha.getDayOfWeek().getValue() < 6) {
//                boolean tieneMarca = Arrays.stream(marcas).anyMatch(marca -> marca.getFecha().equals(fecha));
//
//                if (!tieneMarca) {
//                    String justificativoUrl = "http://localhost:8081/api/justificativos/{rut}/{fecha}";
//                    Boolean tieneJustificativo = restTemplate.getForObject(
//                            justificativoUrl, Boolean.class, rutEmpleado, fecha);
//
//                    if (Boolean.FALSE.equals(tieneJustificativo)) {
//                        totalDescuentos = totalDescuentos.add(obtenerDescuentoPorInasistencia(rutEmpleado));
//                    }
//                }
//            }
//        }
//
//        return totalDescuentos;
//    }
private BigDecimal calcularDescuentos(String rutEmpleado, int mes, int anio) {
    String url = "http://localhost:8080/api/marcas/{rut}/{mes}/{anio}";
    ResponseEntity<MarcaAsistenciaDTO[]> response = restTemplate.getForEntity(
            url, MarcaAsistenciaDTO[].class, rutEmpleado, mes, anio);

    MarcaAsistenciaDTO[] marcas = response.getBody();
    BigDecimal totalDescuentos = BigDecimal.ZERO;

    LocalDate inicioMes = LocalDate.of(anio, mes, 1);
    LocalDate finMes = inicioMes.withDayOfMonth(inicioMes.lengthOfMonth());

    for (LocalDate fecha = inicioMes; !fecha.isAfter(finMes); fecha = fecha.plusDays(1)) {
        if (fecha.getDayOfWeek().getValue() < 6) { // Lunes a viernes
            boolean tieneMarca = false;
            for (MarcaAsistenciaDTO marca : marcas) {
                if (marca.getFecha().equals(fecha)) {
                    tieneMarca = true;
                    break;
                }
            }

            if (!tieneMarca) {
                // Verificar justificativo
                String justificativoUrl = "http://localhost:8081/api/justificativos/{rut}/{fecha}";
                Boolean tieneJustificativo = restTemplate.getForObject(
                        justificativoUrl, Boolean.class, rutEmpleado, fecha);

                if (Boolean.FALSE.equals(tieneJustificativo)) {
                    totalDescuentos = totalDescuentos.add(obtenerDescuentoPorInasistencia(rutEmpleado));
                }
            }
        }
    }

    return totalDescuentos;
}



//    private BigDecimal obtenerPagoPorHoraExtra(String categoria) {
//        switch (categoria) {
//            case "A": return BigDecimal.valueOf(25000);
//            case "B": return BigDecimal.valueOf(20000);
//            case "C": return BigDecimal.valueOf(10000);
//            default: throw new IllegalArgumentException("Categoría desconocida: " + categoria);
//        }
//    }

    private BigDecimal obtenerPagoPorHoraExtra(String categoria) {
        switch (categoria) {
            case "A": return BigDecimal.valueOf(25000);
            case "B": return BigDecimal.valueOf(20000);
            case "C": return BigDecimal.valueOf(10000);
            default: throw new IllegalArgumentException("Categoría desconocida: " + categoria);
        }
    }



    private BigDecimal obtenerDescuentoPorInasistencia(String rutEmpleado) {
        String url = "http://localhost:8084/api/empleados/{rut}";
        EmpleadoDTO empleado = restTemplate.getForObject(url, EmpleadoDTO.class, rutEmpleado);
        return empleado.getSueldoBase().multiply(BigDecimal.valueOf(0.15));
    }


    public List<PlanillaSueldoEntity> listarTodas() {
        return repository.findAll();
    }

    public List<PlanillaSueldoEntity> listarPorRut(String rutEmpleado) {
        return repository.findByRutEmpleado(rutEmpleado);
    }


}
