package com.mueblesstgo.ms_justificativos.repositories;

import com.mueblesstgo.ms_justificativos.entities.JustificativoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JustificativoRepository extends JpaRepository<JustificativoEntity, Long> {
    List<JustificativoEntity> findByRutEmpleado(String rutEmpleado);

    boolean existsByRutEmpleadoAndFecha(String rutEmpleado, LocalDate fecha);
}
