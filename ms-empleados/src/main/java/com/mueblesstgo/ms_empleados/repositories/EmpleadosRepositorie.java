package com.mueblesstgo.ms_empleados.repositories;

import com.mueblesstgo.ms_empleados.entities.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadosRepositorie extends JpaRepository<EmpleadoEntity, Long> {
}
