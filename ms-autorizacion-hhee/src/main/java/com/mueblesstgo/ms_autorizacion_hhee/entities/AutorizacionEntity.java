package com.mueblesstgo.ms_autorizacion_hhee.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "autorizacion_hhee")
public class AutorizacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String rutEmpleado;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private Integer cantHHEE; // Cantidad de horas extras autorizadas para ese día
}
