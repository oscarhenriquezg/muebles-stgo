package com.mueblesstgo.ms_calculo_planilla_sueldos.services;

import com.mueblesstgo.ms_calculo_planilla_sueldos.clients.ImportadorMarcasClient;
import com.mueblesstgo.ms_calculo_planilla_sueldos.clients.AutorizacionHHEEClient;
import com.mueblesstgo.ms_calculo_planilla_sueldos.clients.JustificativosClient;
import com.mueblesstgo.ms_calculo_planilla_sueldos.entities.PlanillaSueldoEntity;
import com.mueblesstgo.ms_calculo_planilla_sueldos.repositories.PlanillaSueldoRepositorie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanillaSueldoService {
    private final PlanillaSueldoRepositorie repositorie;
    private final ImportadorMarcasClient marcasClient;
    private final AutorizacionHHEEClient autorizacionesClient;
    private final JustificativosClient justificativosClient;

    public PlanillaSueldoService(
            PlanillaSueldoRepositorie repositorie,
            ImportadorMarcasClient marcasClient,
            AutorizacionHHEEClient autorizacionesClient,
            JustificativosClient justificativosClient
    ) {
        this.repositorie = repositorie;
        this.marcasClient = marcasClient;
        this.autorizacionesClient = autorizacionesClient;
        this.justificativosClient = justificativosClient;
    }

    public PlanillaSueldoEntity generarPlanilla(String rutEmpleado, String mes) {
        // Obtener datos de los microservicios
        List<Object> marcas = marcasClient.obtenerMarcasPorEmpleado(rutEmpleado, mes);
        List<Object> autorizaciones = autorizacionesClient.obtenerAutorizacionesPorRut(rutEmpleado);
        List<Object> justificativos = justificativosClient.obtenerJustificativosPorRut(rutEmpleado);

        // Cálculo ficticio para este ejemplo
        int horasTrabajadas = marcas.size() * 8; // Suponiendo 8 horas por marca
        int horasExtras = autorizaciones.size() * 2; // Suponiendo 2 horas por autorización
        int bonificaciones = 50000; // Fijo
        int descuentos = justificativos.size() * 10000; // Suponiendo 10,000 por justificativo

        PlanillaSueldoEntity planilla = new PlanillaSueldoEntity();
        planilla.setRutEmpleado(rutEmpleado);
        planilla.setMes(mes);
        planilla.setHorasTrabajadas(horasTrabajadas);
        planilla.setHorasExtras(horasExtras);
        planilla.setBonificaciones(bonificaciones);
        planilla.setDescuentos(descuentos);
        planilla.setSueldoLiquido((horasTrabajadas * 10000) + (horasExtras * 15000) + bonificaciones - descuentos);

        return repositorie.save(planilla);
    }
    public List<PlanillaSueldoEntity> obtenerPlanillasPorMes(String mes) {
        return repositorie.findByMes(mes);
    }

}
