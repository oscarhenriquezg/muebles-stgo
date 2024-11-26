package com.mueblesstgo.ms_reporte_sueldos.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmpleadoDTO {
    private Long id;
    private String rut;
    private String apellidos;
    private String nombres;
    private LocalDate fechaNacimiento;
    private String categoria;
    private LocalDate fechaIngreso;
}
