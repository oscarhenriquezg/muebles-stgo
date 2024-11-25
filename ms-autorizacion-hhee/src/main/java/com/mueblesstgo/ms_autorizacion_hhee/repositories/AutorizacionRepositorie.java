package com.mueblesstgo.ms_autorizacion_hhee.repositories;

import com.mueblesstgo.ms_autorizacion_hhee.entities.AutorizacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AutorizacionRepositorie extends JpaRepository<AutorizacionEntity, Long> {
    List<AutorizacionEntity> findByRutEmpleado(String rutEmpleado);
    List<AutorizacionEntity> findByFecha(LocalDate fecha);
}
