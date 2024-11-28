package com.mueblesstgo.ms_justificativos.repositories;

import com.mueblesstgo.ms_justificativos.entities.JustificativoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface JustificativoRepository extends JpaRepository<JustificativoEntity, Long> {
    List<JustificativoEntity> findByRutEmpleado(String rutEmpleado);

    boolean existsByRutEmpleadoAndFecha(String rutEmpleado, LocalDate fecha);
}
