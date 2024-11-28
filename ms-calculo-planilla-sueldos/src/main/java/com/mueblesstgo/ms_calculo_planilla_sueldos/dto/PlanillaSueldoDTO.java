package com.mueblesstgo.ms_calculo_planilla_sueldos.dto;

import java.math.BigDecimal;

public class PlanillaSueldoDTO {
    private String rutEmpleado;
    private String nombreEmpleado;
    private String categoria;
    private int añosServicio;
    private BigDecimal sueldoFijoMensual;
    private BigDecimal bonificacionAñosServicio;
    private BigDecimal montoPagoHorasExtras;
    private BigDecimal montoDescuentos;
    private BigDecimal sueldoBruto;
    private BigDecimal cotizacionPrevisional;
    private BigDecimal cotizacionSalud;
    private BigDecimal montoSueldoFinal;

    public PlanillaSueldoDTO(String rutEmpleado, String nombreEmpleado, String categoria, int añosServicio,
                             BigDecimal sueldoFijoMensual, BigDecimal bonificacionAñosServicio,
                             BigDecimal montoPagoHorasExtras, BigDecimal montoDescuentos,
                             BigDecimal sueldoBruto, BigDecimal cotizacionPrevisional,
                             BigDecimal cotizacionSalud, BigDecimal montoSueldoFinal) {
        this.rutEmpleado = rutEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.categoria = categoria;
        this.añosServicio = añosServicio;
        this.sueldoFijoMensual = sueldoFijoMensual;
        this.bonificacionAñosServicio = bonificacionAñosServicio;
        this.montoPagoHorasExtras = montoPagoHorasExtras;
        this.montoDescuentos = montoDescuentos;
        this.sueldoBruto = sueldoBruto;
        this.cotizacionPrevisional = cotizacionPrevisional;
        this.cotizacionSalud = cotizacionSalud;
        this.montoSueldoFinal = montoSueldoFinal;
    }

    // Getters y setters


    public String getRutEmpleado() {
        return rutEmpleado;
    }

    public void setRutEmpleado(String rutEmpleado) {
        this.rutEmpleado = rutEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAñosServicio() {
        return añosServicio;
    }

    public void setAñosServicio(int añosServicio) {
        this.añosServicio = añosServicio;
    }

    public BigDecimal getSueldoFijoMensual() {
        return sueldoFijoMensual;
    }

    public void setSueldoFijoMensual(BigDecimal sueldoFijoMensual) {
        this.sueldoFijoMensual = sueldoFijoMensual;
    }

    public BigDecimal getBonificacionAñosServicio() {
        return bonificacionAñosServicio;
    }

    public void setBonificacionAñosServicio(BigDecimal bonificacionAñosServicio) {
        this.bonificacionAñosServicio = bonificacionAñosServicio;
    }

    public BigDecimal getMontoPagoHorasExtras() {
        return montoPagoHorasExtras;
    }

    public void setMontoPagoHorasExtras(BigDecimal montoPagoHorasExtras) {
        this.montoPagoHorasExtras = montoPagoHorasExtras;
    }

    public BigDecimal getMontoDescuentos() {
        return montoDescuentos;
    }

    public void setMontoDescuentos(BigDecimal montoDescuentos) {
        this.montoDescuentos = montoDescuentos;
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

    public BigDecimal getMontoSueldoFinal() {
        return montoSueldoFinal;
    }

    public void setMontoSueldoFinal(BigDecimal montoSueldoFinal) {
        this.montoSueldoFinal = montoSueldoFinal;
    }
}
