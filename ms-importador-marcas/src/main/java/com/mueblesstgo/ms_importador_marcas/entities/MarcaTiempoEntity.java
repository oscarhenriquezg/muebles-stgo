package com.mueblesstgo.ms_importador_marcas.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "marca_tiempo")
public class MarcaTiempoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private LocalTime hora;

    @Column(nullable = false)
    private String rutEmpleado;
}
