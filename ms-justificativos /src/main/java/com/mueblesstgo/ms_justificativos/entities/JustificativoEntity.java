package com.mueblesstgo.ms_justificativos.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "justificativos")
public class JustificativoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJustificativos;

    @Column(nullable = false, length = 12)
    private String rutEmpleado;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private String motivo;
}
