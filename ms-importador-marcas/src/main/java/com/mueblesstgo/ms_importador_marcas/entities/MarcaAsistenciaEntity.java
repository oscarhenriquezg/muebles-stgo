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

    public Long getIdMarcasAsistencia() {
        return idMarcasAsistencia;
    }

    public void setIdMarcasAsistencia(Long idMarcasAsistencia) {
        this.idMarcasAsistencia = idMarcasAsistencia;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getRutEmpleado() {
        return rutEmpleado;
    }

    public void setRutEmpleado(String rutEmpleado) {
        this.rutEmpleado = rutEmpleado;
    }

    public String getTipoMarca() {
        return tipoMarca;
    }

    public void setTipoMarca(String tipoMarca) {
        this.tipoMarca = tipoMarca;
    }
}
