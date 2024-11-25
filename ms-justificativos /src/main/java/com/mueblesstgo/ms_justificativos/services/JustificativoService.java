package com.mueblesstgo.ms_justificativos.services;

import com.mueblesstgo.ms_justificativos.entities.JustificativoEntity;
import com.mueblesstgo.ms_justificativos.repositories.JustificativoRepositorie;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JustificativoService {
    private final JustificativoRepositorie repositorie;

    public JustificativoService(JustificativoRepositorie repositorie) {
        this.repositorie = repositorie;
    }

    public JustificativoEntity guardarJustificativo(JustificativoEntity justificativo) {
        return repositorie.save(justificativo);
    }

    public List<JustificativoEntity> buscarPorRut(String rutEmpleado) {
        return repositorie.findByRutEmpleado(rutEmpleado);
    }

    public List<JustificativoEntity> buscarPorRangoDeFechas(LocalDate inicio, LocalDate fin) {
        return repositorie.findByFechaBetween(inicio, fin);
    }
}
