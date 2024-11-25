package com.mueblesstgo.ms_calculo_planilla_sueldos.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "planilla_sueldo")
public class PlanillaSueldoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String rutEmpleado;

    @Column(nullable = false)
    private String mes; // Formato: yyyy-MM

    @Column(nullable = false)
    private Integer horasTrabajadas;

    @Column(nullable = false)
    private Integer horasExtras;

    @Column(nullable = false)
    private Integer bonificaciones; // En pesos chilenos

    @Column(nullable = false)
    private Integer descuentos; // En pesos chilenos

    @Column(nullable = false)
    private Integer sueldoLiquido; // Sueldo final después de descuentos y bonificaciones
}
