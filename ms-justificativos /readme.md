¡Perfecto! Continuemos con el siguiente microservicio.

---

### **Próximo Microservicio: `ms-justificativos`**

#### **Objetivo del Microservicio**
El microservicio `ms-justificativos` se encargará de gestionar los justificativos de empleados por ausencias o atrasos. Proveerá funcionalidades para registrar y listar justificativos, los cuales son clave para validar ausencias y evitar descuentos en la planilla de sueldos.

---

### **Requisitos y Funcionalidades**

#### **Responsabilidades del Microservicio**
1. **Registrar Justificativos**:
    - Permitir ingresar justificativos con RUT, fecha, y motivo.
    - Validar que no existan justificativos duplicados para un mismo empleado y fecha.

2. **Listar Justificativos**:
    - Consultar justificativos almacenados para un empleado específico o todos los empleados.

---

### **Modelo de Datos**

#### **Tabla: `justificativos`**
Esta tabla ya existe en la base de datos y tiene la siguiente estructura:

| **Campo**           | **Tipo**         | **Descripción**                          |
|----------------------|------------------|------------------------------------------|
| `id_justificativos`  | `SERIAL`         | Identificador único del justificativo.   |
| `rut_empleado`       | `VARCHAR(12)`    | RUT del empleado.                        |
| `fecha`              | `DATE`           | Fecha del justificativo.                 |
| `motivo`             | `TEXT`           | Motivo del justificativo.                |

---

### **Endpoints Planeados**

| **Método** | **Endpoint**           | **Descripción**                                    |
|------------|------------------------|--------------------------------------------------|
| `POST`     | `/api/justificativos`  | Registrar un nuevo justificativo.               |
| `GET`      | `/api/justificativos`  | Listar todos los justificativos.                |
| `GET`      | `/api/justificativos/{rut}` | Listar justificativos de un empleado específico. |

---

### **Plan de Desarrollo**

1. **Entidad (`JustificativoEntity`)**:
    - Representará la tabla `justificativos` en el código.

2. **Repositorio (`JustificativoRepository`)**:
    - Gestionará la interacción con la base de datos.

3. **Servicio (`JustificativoService`)**:
    - Manejará la lógica de negocio:
        - Validar duplicados.
        - Consultar justificativos.

4. **Controlador (`JustificativoController`)**:
    - Expondrá los endpoints REST definidos.

5. **Archivo `application.properties`**:
    - Configuración según el estándar definido.

---

### **Configuración Base**

#### **Archivo `application.properties`**
```properties
# Definición de variables
eureka.server.url=http://localhost:8761/eureka
spring.datasource.url=jdbc:postgresql://localhost:5432/bd_justificativos
spring.datasource.username=postgres
spring.datasource.password=mysecretpassword

# Configuración de la aplicación
spring.application.name=ms-justificativos

# Configuración de Eureka
eureka.client.service-url.defaultZone=${eureka.server.url}
server.port=8081
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