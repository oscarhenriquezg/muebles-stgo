package com.mueblesstgo.ms_justificativos.services;

import com.mueblesstgo.ms_justificativos.entities.JustificativoEntity;
import com.mueblesstgo.ms_justificativos.repositories.JustificativoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JustificativoService {

    private final JustificativoRepository repository;

    public JustificativoService(JustificativoRepository repository) {
        this.repository = repository;
    }

    public JustificativoEntity registrarJustificativo(JustificativoEntity justificativo) {
        if (repository.existsByRutEmpleadoAndFecha(justificativo.getRutEmpleado(), justificativo.getFecha())) {
            throw new IllegalArgumentException("Ya existe un justificativo para el RUT y fecha especificados.");
        }
        return repository.save(justificativo);
    }

    public List<JustificativoEntity> listarTodos() {
        return repository.findAll();
    }

    public List<JustificativoEntity> listarPorRut(String rutEmpleado) {
        return repository.findByRutEmpleado(rutEmpleado);
    }
}
