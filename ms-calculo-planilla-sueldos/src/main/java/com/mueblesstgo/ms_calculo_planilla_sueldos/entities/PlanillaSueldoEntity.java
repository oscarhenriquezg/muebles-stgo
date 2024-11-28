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
}
