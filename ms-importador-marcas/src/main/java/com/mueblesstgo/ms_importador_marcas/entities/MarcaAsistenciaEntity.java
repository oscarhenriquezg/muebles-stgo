package com.mueblesstgo.ms_importador_marcas.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
//test coment

@Entity
@Data
@Table(name = "marcas_asistencia")
public class MarcaAsistenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMarcasAsistencia;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime hora;

    @Column(nullable = false, length = 12)
    private String rutEmpleado;

    @Column(nullable = false, length = 10)
    private String tipoMarca; // "ingreso" o "salida"
}
