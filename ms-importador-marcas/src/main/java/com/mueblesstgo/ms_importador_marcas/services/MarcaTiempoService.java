package com.mueblesstgo.ms_importador_marcas.services;

import com.mueblesstgo.ms_importador_marcas.entities.MarcaTiempoEntity;
import com.mueblesstgo.ms_importador_marcas.repositories.MarcaTiempoRepositorie;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarcaTiempoService {
    private final MarcaTiempoRepositorie repositorie;

    public MarcaTiempoService(MarcaTiempoRepositorie repositorie) {
        this.repositorie = repositorie;
    }

    public int procesarArchivo(InputStreamReader input) throws Exception {
        BufferedReader reader = new BufferedReader(input);
        List<MarcaTiempoEntity> marcas = new ArrayList<>();
        String line;

        // Formateador para el formato yyyy/MM/dd
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            if (parts.length != 3) {
                continue; // Ignorar líneas inválidas
            }

            MarcaTiempoEntity marca = new MarcaTiempoEntity();
            // Parsear la fecha y la hora con el formato adecuado
            marca.setFecha(LocalDate.parse(parts[0], dateFormatter));
            marca.setHora(LocalTime.parse(parts[1]));
            marca.setRutEmpleado(parts[2]);

            marcas.add(marca);
        }

        repositorie.saveAll(marcas);
        return marcas.size();
    }

    public List<MarcaTiempoEntity> obtenerMarcas(String rutEmpleado, String mes) {
        // Filtrar las marcas por RUT y mes
        return repositorie.findByRutEmpleado(rutEmpleado)
                .stream()
                .filter(marca -> marca.getFecha().toString().startsWith(mes))
                .collect(Collectors.toList());
    }
}
