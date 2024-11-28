package com.mueblesstgo.ms_calculo_planilla_sueldos.dto;

import lombok.Data;

@Data
public class PlanillaRequest {
    private String rut;
    private int mes;
    private int anio;
}
