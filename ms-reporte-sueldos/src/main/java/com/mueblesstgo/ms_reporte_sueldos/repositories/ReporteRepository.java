package com.mueblesstgo.ms_reporte_sueldos.repositories;

import com.mueblesstgo.ms_reporte_sueldos.entities.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {
    List<Reporte> findByMesAndAnio(int mes, int anio);
}
