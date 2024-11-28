package com.mueblesstgo.ms_empleados.repositories;

import com.mueblesstgo.ms_empleados.entities.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {
    Optional<EmpleadoEntity> findByRut(String rut);
}
