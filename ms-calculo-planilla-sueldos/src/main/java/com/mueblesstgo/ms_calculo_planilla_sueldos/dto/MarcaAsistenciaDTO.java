package com.mueblesstgo.ms_calculo_planilla_sueldos.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class MarcaAsistenciaDTO {
    private LocalDate fecha;
    private LocalTime hora;
    private String rutEmpleado;
    private String tipoMarca; // "ingreso" o "salida"
}
