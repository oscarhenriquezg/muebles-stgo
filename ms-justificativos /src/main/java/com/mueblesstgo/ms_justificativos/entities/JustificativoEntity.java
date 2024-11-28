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

    public Long getIdJustificativos() {
        return idJustificativos;
    }

    public void setIdJustificativos(Long idJustificativos) {
        this.idJustificativos = idJustificativos;
    }

    public String getRutEmpleado() {
        return rutEmpleado;
    }

    public void setRutEmpleado(String rutEmpleado) {
        this.rutEmpleado = rutEmpleado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
