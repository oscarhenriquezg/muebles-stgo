### **Próximo Microservicio: `ms-calculo-planilla-sueldos`**

---

### **Objetivo del Microservicio**

El microservicio `ms-calculo-planilla-sueldos` realiza el cálculo completo del sueldo neto para los empleados. Integra datos de otros microservicios (marcas de asistencia, autorizaciones de horas extras, justificativos y datos de empleados) para aplicar las reglas de negocio necesarias en la generación de la planilla de sueldos.

---

### **Responsabilidades**

#### **Funciones del Microservicio**
1. **Recopilación de Datos**:
    - Obtener las marcas de asistencia del empleado (de `ms-importador-marcas`).
    - Validar autorizaciones de horas extras (de `ms-autorizacion-hhee`).
    - Verificar justificativos por ausencias o atrasos (de `ms-justificativos`).
    - Obtener datos básicos del empleado, como sueldo base y categoría (de `ms-empleados`).

2. **Aplicación de Reglas de Negocio**:
    - **Bonificaciones por Antigüedad**:
        - < 5 años: 0%
        - 5–10 años: 5%
        - 10–15 años: 8%
        - 15–20 años: 11%
        - 20–25 años: 14%
        - >= 25 años: 17%
    - **Cálculo de Horas Extras**:
        - Pago por hora extra según categoría.
    - **Descuentos por Atrasos e Inasistencias**:
        - Descuento por atrasos según minutos de retraso.
        - Descuento por inasistencias si no existe justificativo.
    - **Descuentos Legales**:
        - Cotización previsional: 10%.
        - Cotización salud: 8%.

3. **Cálculo del Sueldo Neto**:
    - Fórmula:
      ```
      Sueldo Neto = Sueldo Base + Bonificaciones + Pago Horas Extras - Descuentos
      ```

4. **Generación de la Planilla de Sueldos**:
    - Almacenar los resultados en la base de datos local.

---

### **Modelo de Datos**

#### **Tabla: `planilla_sueldos`**
Esta tabla ya existe en la base de datos y tiene la siguiente estructura:

| **Campo**            | **Tipo**       | **Descripción**                                    |
|-----------------------|----------------|---------------------------------------------------|
| `id_planilla_sueldos`| `SERIAL`       | Identificador único de la planilla.               |
| `rut_empleado`       | `VARCHAR(12)`  | RUT del empleado.                                 |
| `mes`                | `INT`          | Mes de cálculo.                                   |
| `anio`               | `INT`          | Año de cálculo.                                   |
| `sueldo_base`        | `NUMERIC`      | Sueldo fijo mensual del empleado.                |
| `bonificacion`       | `NUMERIC`      | Monto de bonificación por antigüedad.            |
| `horas_extras`       | `NUMERIC`      | Pago por horas extras trabajadas.                |
| `descuentos`         | `NUMERIC`      | Total de descuentos aplicados.                   |
| `sueldo_bruto`       | `NUMERIC`      | Sueldo bruto antes de descuentos legales.        |
| `cotizacion_previsional` | `NUMERIC`  | Descuento por cotización previsional (10%).      |
| `cotizacion_salud`   | `NUMERIC`      | Descuento por cotización de salud (8%).          |
| `sueldo_neto`        | `NUMERIC`      | Sueldo neto después de todos los descuentos.     |

---

### **Endpoints Planeados**

| **Método** | **Endpoint**            | **Descripción**                                    |
|------------|-------------------------|--------------------------------------------------|
| `POST`     | `/api/planilla`         | Generar la planilla de sueldos para un mes y año específicos. |
| `GET`      | `/api/planilla`         | Consultar todas las planillas generadas.         |
| `GET`      | `/api/planilla/{rut}`   | Consultar la planilla de un empleado específico. |

---

### **Plan de Desarrollo**

1. **Entidad (`PlanillaSueldoEntity`)**:
    - Representará la tabla `planilla_sueldos` en el código.

2. **Repositorio (`PlanillaSueldoRepository`)**:
    - Gestionará la interacción con la base de datos.

3. **Servicio (`PlanillaSueldoService`)**:
    - Realizará el cálculo del sueldo aplicando las reglas de negocio.

4. **Controlador (`PlanillaSueldoController`)**:
    - Expondrá los endpoints definidos.

5. **Archivo `application.properties`**:
    - Configuración según el estándar definido.

6. **Comunicación con otros Microservicios**:
    - Implementar clientes REST usando `RestTemplate` para consumir los datos necesarios.

---

### **Configuración Base**

#### **Archivo `application.properties`**
```properties
# Definición de variables
eureka.server.url=http://localhost:8761/eureka
spring.datasource.url=jdbc:postgresql://localhost:5432/bd_calculo_planilla_sueldos
spring.datasource.username=postgres
spring.datasource.password=mysecretpassword

# Configuración de la aplicación
spring.application.name=ms-calculo-planilla-sueldos

# Configuración de Eureka
eureka.client.service-url.defaultZone=${eureka.server.url}
server.port=8083
server.error.include-message=always

# Configuración de JPA
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.show-sql=true
spring.jpa.properties.hibernate.use_sql_comments=false
spring.jpa.properties.hibernate.format_sql=true

# Configuración de logging
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} ${LOG_LEVEL_PATTERN:-%5p} %m%n
```

---

### **¿Sigo con la implementación del código para este microservicio?**