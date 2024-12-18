
-- Cambiar a la base de datos BD_EMPLEADOS
\c BD_EMPLEADOS;

-- Crear la tabla empleados
CREATE TABLE empleados (
                           id_empleados SERIAL PRIMARY KEY,
                           rut VARCHAR(12) UNIQUE NOT NULL,
                           nombres VARCHAR(50) NOT NULL,
                           apellidos VARCHAR(50) NOT NULL,
                           fecha_nacimiento DATE NOT NULL,
                           categoria CHAR(1) NOT NULL CHECK (categoria IN ('A', 'B', 'C')),
                           fecha_ingreso DATE NOT NULL
);

-- Insertar datos de prueba en empleados
INSERT INTO empleados (rut, nombres, apellidos, fecha_nacimiento, categoria, fecha_ingreso)
VALUES
    ('11.234.123-6', 'Juan Carlos', 'Bodoque', '1980-01-15', 'A', '2010-03-01'),
    ('12.457.562-3', 'Juan', 'Perez', '1990-05-10', 'B', '2015-07-20'),
    ('21.142.354-k', 'Tulio', 'Trivino', '1985-09-25', 'C', '2020-11-15'),
    ('17.765.876-2', 'Martin', 'Martillo', '1992-12-05', 'B', '2017-01-12');

-- Cambiar a la base de datos BD_IMPORTADOR_MARCAS
\c BD_IMPORTADOR_MARCAS;

-- Crear la tabla marcas_asistencia
CREATE TABLE marcas_asistencia (
                                   id_marcas_asistencia SERIAL PRIMARY KEY,
                                   fecha DATE NOT NULL,
                                   hora TIME NOT NULL,
                                   rut_empleado VARCHAR(12) NOT NULL,
                                   tipo_marca VARCHAR(10) NOT NULL CHECK (tipo_marca IN ('ingreso', 'salida'))
);

-- Insertar datos de prueba en marcas_asistencia
INSERT INTO marcas_asistencia (fecha, hora, rut_empleado, tipo_marca)
VALUES
    ('2022-08-17', '08:00', '11.234.123-6', 'ingreso'),
    ('2022-08-17', '17:57', '11.234.123-6', 'salida'),
    ('2022-08-17', '07:58', '12.457.562-3', 'ingreso'),
    ('2022-08-17', '18:03', '12.457.562-3', 'salida'),
    ('2022-08-17', '08:07', '21.142.354-k', 'ingreso'),
    ('2022-08-17', '18:23', '21.142.354-k', 'salida'),
    ('2022-08-17', '08:15', '17.765.876-2', 'ingreso'),
    ('2022-08-17', '18:27', '17.765.876-2', 'salida');

-- Cambiar a la base de datos BD_AUTORIZACION_HHEE
\c BD_AUTORIZACION_HHEE;

-- Crear la tabla autorizaciones_hhee
CREATE TABLE autorizaciones_hhee (
                                     id_autorizaciones_hhee SERIAL PRIMARY KEY,
                                     rut_empleado VARCHAR(12) NOT NULL,
                                     fecha DATE NOT NULL,
                                     autorizado BOOLEAN NOT NULL DEFAULT TRUE
);

-- Insertar datos de prueba en autorizaciones_hhee
INSERT INTO autorizaciones_hhee (rut_empleado, fecha, autorizado)
VALUES
    ('12.457.562-3', '2022-08-17', TRUE),
    ('21.142.354-k', '2022-08-17', TRUE),
    ('17.765.876-2', '2022-08-17', TRUE);

-- Cambiar a la base de datos BD_JUSTIFICATIVOS
\c BD_JUSTIFICATIVOS;

-- Crear la tabla justificativos
CREATE TABLE justificativos (
                                id_justificativos SERIAL PRIMARY KEY,
                                rut_empleado VARCHAR(12) NOT NULL,
                                fecha DATE NOT NULL,
                                motivo TEXT NOT NULL
);

-- Insertar datos de prueba en justificativos
INSERT INTO justificativos (rut_empleado, fecha, motivo)
VALUES
    ('11.234.123-6', '2022-08-18', 'Licencia médica'),
    ('21.142.354-k', '2022-08-19', 'Permiso personal');

-- Cambiar a la base de datos BD_CALCULO_PLANILLA_SUELDOS
\c BD_CALCULO_PLANILLA_SUELDOS;

-- Crear la tabla planilla_sueldos
CREATE TABLE planilla_sueldos (
                                  id_planilla_sueldos SERIAL PRIMARY KEY,
                                  rut_empleado VARCHAR(12) NOT NULL,
                                  mes INT NOT NULL CHECK (mes BETWEEN 1 AND 12),
                                  anio INT NOT NULL,
                                  sueldo_base NUMERIC(12, 2) NOT NULL,
                                  bonificacion NUMERIC(12, 2) DEFAULT 0,
                                  horas_extras NUMERIC(12, 2) DEFAULT 0,
                                  descuentos NUMERIC(12, 2) DEFAULT 0,
                                  sueldo_bruto NUMERIC(12, 2) NOT NULL,
                                  cotizacion_previsional NUMERIC(12, 2) NOT NULL,
                                  cotizacion_salud NUMERIC(12, 2) NOT NULL,
                                  sueldo_neto NUMERIC(12, 2) NOT NULL
);

-- Insertar datos de prueba en planilla_sueldos
INSERT INTO planilla_sueldos (rut_empleado, mes, anio, sueldo_base, bonificacion, horas_extras, descuentos, sueldo_bruto, cotizacion_previsional, cotizacion_salud, sueldo_neto)
VALUES
    ('11.234.123-6', 8, 2022, 1700000, 0, 0, 0, 1700000, 170000, 136000, 1394000);
