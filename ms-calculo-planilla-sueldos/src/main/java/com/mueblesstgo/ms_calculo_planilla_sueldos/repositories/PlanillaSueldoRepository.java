package com.mueblesstgo.ms_calculo_planilla_sueldos.repositories;

import com.mueblesstgo.ms_calculo_planilla_sueldos.entities.PlanillaSueldoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanillaSueldoRepository extends JpaRepository<PlanillaSueldoEntity, Long> {
    List<PlanillaSueldoEntity> findByRutEmpleado(String rutEmpleado);
}