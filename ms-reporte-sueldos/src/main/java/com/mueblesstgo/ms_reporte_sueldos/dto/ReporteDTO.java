package com.mueblesstgo.ms_reporte_sueldos.dto;

import java.math.BigDecimal;

public class ReporteDTO {
    private String rut;
    private String nombreCompleto;
    private String categoria;
    private int aniosServicio;
    private BigDecimal sueldoFijoMensual;
    private BigDecimal bonificacion;
    private BigDecimal horasExtras;
    private BigDecimal descuentos;
    private BigDecimal sueldoBruto;
    private BigDecimal cotizacionPrevisional;
    private BigDecimal cotizacionSalud;
    private BigDecimal sueldoNeto;

    public ReporteDTO(String rut, String nombreCompleto, String categoria, int aniosServicio,
                      BigDecimal sueldoFijoMensual, BigDecimal bonificacion, BigDecimal horasExtras,
                      BigDecimal descuentos, BigDecimal sueldoBruto, BigDecimal cotizacionPrevisional,
                      BigDecimal cotizacionSalud, BigDecimal sueldoNeto) {
        this.rut = rut;
        this.nombreCompleto = nombreCompleto;
        this.categoria = categoria;
        this.aniosServicio = aniosServicio;
        this.sueldoFijoMensual = sueldoFijoMensual;
        this.bonificacion = bonificacion;
        this.horasExtras = horasExtras;
        this.descuentos = descuentos;
        this.sueldoBruto = sueldoBruto;
        this.cotizacionPrevisional = cotizacionPrevisional;
        this.cotizacionSalud = cotizacionSalud;
        this.sueldoNeto = sueldoNeto;
    }

    // Getters y Setters (si se requiere)
    public String getRut() {
        return rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public int getAniosServicio() {
        return aniosServicio;
    }

    public void setAniosServicio(int aniosServicio) {
        this.aniosServicio = aniosServicio;
    }

    public BigDecimal getSueldoFijoMensual() {
        return sueldoFijoMensual;
    }

    public void setSueldoFijoMensual(BigDecimal sueldoFijoMensual) {
        this.sueldoFijoMensual = sueldoFijoMensual;
    }

    public BigDecimal getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(BigDecimal bonificacion) {
        this.bonificacion = bonificacion;
    }

    public BigDecimal getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(BigDecimal horasExtras) {
        this.horasExtras = horasExtras;
    }

    public BigDecimal getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(BigDecimal descuentos) {
        this.descuentos = descuentos;
    }

    public BigDecimal getSueldoBruto() {
        return sueldoBruto;
    }

    public void setSueldoBruto(BigDecimal sueldoBruto) {
        this.sueldoBruto = sueldoBruto;
    }

    public BigDecimal getCotizacionPrevisional() {
        return cotizacionPrevisional;
    }

    public void setCotizacionPrevisional(BigDecimal cotizacionPrevisional) {
        this.cotizacionPrevisional = cotizacionPrevisional;
    }

    public BigDecimal getCotizacionSalud() {
        return cotizacionSalud;
    }

    public void setCotizacionSalud(BigDecimal cotizacionSalud) {
        this.cotizacionSalud = cotizacionSalud;
    }

    public BigDecimal getSueldoNeto() {
        return sueldoNeto;
    }

    public void setSueldoNeto(BigDecimal sueldoNeto) {
        this.sueldoNeto = sueldoNeto;
    }
}

