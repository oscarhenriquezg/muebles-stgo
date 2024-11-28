package com.mueblesstgo.ms_calculo_planilla_sueldos.dto;

import lombok.Data;

@Data
public class PlanillaRequest {
    private String rut;
    private int mes;
    private int anio;

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
}
