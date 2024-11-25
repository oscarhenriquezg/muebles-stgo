package com.mueblesstgo.ms_importador_marcas.repositories;

import com.mueblesstgo.ms_importador_marcas.entities.MarcaTiempoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarcaTiempoRepositorie extends JpaRepository<MarcaTiempoEntity, Long> {
    List<MarcaTiempoEntity> findByRutEmpleado(String rutEmpleado);
}
