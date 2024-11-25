package com.mueblesstgo.ms_autorizacion_hhee.services;

import com.mueblesstgo.ms_autorizacion_hhee.entities.AutorizacionEntity;
import com.mueblesstgo.ms_autorizacion_hhee.repositories.AutorizacionRepositorie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorizacionService {
    private final AutorizacionRepositorie repositorie;

    public AutorizacionService(AutorizacionRepositorie repositorie) {
        this.repositorie = repositorie;
    }

    public AutorizacionEntity registrarAutorizacion(AutorizacionEntity autorizacion) {
        return repositorie.save(autorizacion);
    }

    public List<AutorizacionEntity> buscarPorRut(String rutEmpleado) {
        return repositorie.findByRutEmpleado(rutEmpleado);
    }

//    public List<AutorizacionEntity> buscarPorFecha(LocalDate fecha) {
//        return repositorie.findByFecha(fecha);
//    }

    public List<AutorizacionEntity> listarTodas() {
        return repositorie.findAll();
    }
}
