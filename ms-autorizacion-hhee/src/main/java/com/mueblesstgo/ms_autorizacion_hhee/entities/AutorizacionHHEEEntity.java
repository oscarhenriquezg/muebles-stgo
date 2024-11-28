package com.mueblesstgo.ms_autorizacion_hhee.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "autorizaciones_hhee")
public class AutorizacionHHEEEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAutorizacionesHhee;

    @Column(nullable = false, length = 12)
    private String rutEmpleado;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private Boolean autorizado = true;
}
