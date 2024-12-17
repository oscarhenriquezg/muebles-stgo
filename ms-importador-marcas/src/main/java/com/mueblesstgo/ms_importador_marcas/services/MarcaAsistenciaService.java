package com.mueblesstgo.ms_importador_marcas.services;

import com.mueblesstgo.ms_importador_marcas.entities.MarcaAsistenciaEntity;
import com.mueblesstgo.ms_importador_marcas.repositories.MarcaAsistenciaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarcaAsistenciaService {

    private final MarcaAsistenciaRepository repositorie;

    public MarcaAsistenciaService(MarcaAsistenciaRepository repositorie) {
        this.repositorie = repositorie;
    }

    public List<String> procesarArchivo(MultipartFile file) {
        List<String> errores = new ArrayList<>();
        // Definimos el formatter con el patrón de fecha yyyy/MM/dd
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                try {
                    String[] datos = linea.split(";");
                    if (datos.length != 3) {
                        throw new IllegalArgumentException("Formato inválido");
                    }

                    // Aquí utilizamos el formatter para parsear la fecha
                    LocalDate fecha = LocalDate.parse(datos[0].trim(), formatter);
                    LocalTime hora = LocalTime.parse(datos[1].trim());
                    String rut = datos[2].trim();

                    MarcaAsistenciaEntity marca = new MarcaAsistenciaEntity();
                    marca.setFecha(fecha);
                    marca.setHora(hora);
                    marca.setRutEmpleado(rut);
                    marca.setTipoMarca(hora.isBefore(LocalTime.NOON) ? "ingreso" : "salida");

                    repositorie.save(marca);
                } catch (Exception e) {
                    errores.add("Error en línea: " + linea + " - " + e.getMessage());
                }
            }
        } catch (Exception e) {
            errores.add("Error al procesar el archivo: " + e.getMessage());
        }
        return errores;
    }

    public List<MarcaAsistenciaEntity> listarMarcasPorRutYMes(String rutEmpleado, int mes, int anio) {
        LocalDate inicio = LocalDate.of(anio, mes, 1);
        LocalDate fin = inicio.withDayOfMonth(inicio.lengthOfMonth());
        return repositorie.findByRutEmpleadoAndFechaBetween(rutEmpleado, inicio, fin);
    }

    public List<MarcaAsistenciaEntity> listarTodasLasMarcas() {
        return repositorie.findAll();
    }
}
