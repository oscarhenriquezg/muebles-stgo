package com.mueblesstgo.ms_justificativos.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "justificativo")
public class JustificativoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String rutEmpleado;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private String tipo; // Por ejemplo: "Permiso médico", "Vacaciones", etc.

    @Column(nullable = false)
    private String descripcion;
}
