package com.mueblesstgo.ms_autorizacion_hhee.services;

import com.mueblesstgo.ms_autorizacion_hhee.entities.AutorizacionHHEEEntity;
import com.mueblesstgo.ms_autorizacion_hhee.repositories.AutorizacionHHEERepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AutorizacionHHEEService {

    private final AutorizacionHHEERepository repository;

    public AutorizacionHHEEService(AutorizacionHHEERepository repository) {
        this.repository = repository;
    }

    public AutorizacionHHEEEntity registrarAutorizacion(AutorizacionHHEEEntity autorizacion) {
        if (repository.existsByRutEmpleadoAndFecha(autorizacion.getRutEmpleado(), autorizacion.getFecha())) {
            throw new IllegalArgumentException("Ya existe una autorización para el RUT y fecha especificados.");
        }
        return repository.save(autorizacion);
    }

    public List<AutorizacionHHEEEntity> listarTodas() {
        return repository.findAll();
    }

    public List<AutorizacionHHEEEntity> listarPorRut(String rutEmpleado) {
        return repository.findByRutEmpleado(rutEmpleado);
    }

    public boolean verificarAutorizacion(String rutEmpleado, LocalDate fecha) {
        AutorizacionHHEEEntity autorizacion = repository.findByRutEmpleadoAndFecha(rutEmpleado, fecha);
        return autorizacion != null && autorizacion.getAutorizado();
    }
}
