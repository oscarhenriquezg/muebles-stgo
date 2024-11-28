package com.mueblesstgo.ms_calculo_planilla_sueldos.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmpleadoDTO {
    private String rut;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String categoria;
    private LocalDate fechaIngreso;
    private BigDecimal sueldoBase;
}
