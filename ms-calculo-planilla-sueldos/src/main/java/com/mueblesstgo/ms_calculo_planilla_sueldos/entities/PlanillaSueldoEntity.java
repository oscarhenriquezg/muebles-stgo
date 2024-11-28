package com.mueblesstgo.ms_calculo_planilla_sueldos.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "planilla_sueldos")
public class PlanillaSueldoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlanillaSueldos;

    @Column(nullable = false, length = 12)
    private String rutEmpleado;

    @Column(nullable = false)
    private int mes;

    @Column(nullable = false)
    private int anio;

    @Column(nullable = false)
    private BigDecimal sueldoBase;

    private BigDecimal bonificacion;

    private BigDecimal horasExtras;

    private BigDecimal descuentos;

    @Column(nullable = false)
    private BigDecimal sueldoBruto;

    @Column(nullable = false)
    private BigDecimal cotizacionPrevisional;

    @Column(nullable = false)
    private BigDecimal cotizacionSalud;

    @Column(nullable = false)
    private BigDecimal sueldoNeto;

    public Long getIdPlanillaSueldos() {
        return idPlanillaSueldos;
    }

    public void setIdPlanillaSueldos(Long idPlanillaSueldos) {
        this.idPlanillaSueldos = idPlanillaSueldos;
    }

    public String getRutEmpleado() {
        return rutEmpleado;
    }

    public void setRutEmpleado(String rutEmpleado) {
        this.rutEmpleado = rutEmpleado;
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

    public BigDecimal getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(BigDecimal sueldoBase) {
        this.sueldoBase = sueldoBase;
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
