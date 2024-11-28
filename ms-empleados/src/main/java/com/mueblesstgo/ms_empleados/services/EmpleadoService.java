package com.mueblesstgo.ms_empleados.services;

import com.mueblesstgo.ms_empleados.entities.EmpleadoEntity;
import com.mueblesstgo.ms_empleados.repositories.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    private final EmpleadoRepository repository;

    public EmpleadoService(EmpleadoRepository repository) {
        this.repository = repository;
    }

    public List<EmpleadoEntity> listarTodos() {
        return repository.findAll();
    }

    public Optional<EmpleadoEntity> buscarPorRut(String rut) {
        return repository.findByRut(rut);
    }
}
