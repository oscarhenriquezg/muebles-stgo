package com.mueblesstgo.ms_autorizacion_hhee.repositories;

import com.mueblesstgo.ms_autorizacion_hhee.entities.AutorizacionHHEEEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AutorizacionHHEERepository extends JpaRepository<AutorizacionHHEEEntity, Long> {
    List<AutorizacionHHEEEntity> findByRutEmpleado(String rutEmpleado);

    boolean existsByRutEmpleadoAndFecha(String rutEmpleado, LocalDate fecha);

    AutorizacionHHEEEntity findByRutEmpleadoAndFecha(String rutEmpleado, LocalDate fecha);
}
