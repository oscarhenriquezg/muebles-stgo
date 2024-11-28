package com.mueblesstgo.ms_importador_marcas.repositories;

import com.mueblesstgo.ms_importador_marcas.entities.MarcaAsistenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
//test
public interface MarcaAsistenciaRepository extends JpaRepository<MarcaAsistenciaEntity, Long> {
    List<MarcaAsistenciaEntity> findByRutEmpleadoAndFechaBetween(String rutEmpleado, LocalDate startDate, LocalDate endDate);
}
