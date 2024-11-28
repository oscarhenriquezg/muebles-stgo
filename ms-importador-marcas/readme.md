### **Diseño y Plan para `ms-importador-marcas`**

El microservicio `ms-importador-marcas` tiene como objetivo importar y procesar marcas de asistencia desde el archivo `DATA.TXT`. Se asegurará de que las marcas se registren en la base de datos `BD_IMPORTADOR_MARCAS` con validaciones adecuadas para garantizar la integridad de los datos.

---

### **Requerimientos**

#### **Responsabilidades del Microservicio**
1. **Importar Marcas desde Archivo**:
    - Recibir un archivo `DATA.TXT` a través de un endpoint REST.
    - Procesar cada línea y almacenar las marcas en la tabla `marcas_asistencia`.

2. **Validaciones**:
    - Validar el formato de cada línea.
    - Verificar que no existan duplicados para el mismo RUT, fecha, y tipo de marca.

3. **Manejo de Errores**:
    - Informar las líneas inválidas con detalles del error.

4. **Consulta de Marcas**:
    - Proveer un endpoint para listar las marcas de asistencia almacenadas.

---

### **Flujo del Proceso**

#### **1. Recepción del Archivo**
- **Input**: Archivo `DATA.TXT` cargado como `MultipartFile`.
- **Formato**:
  ```
  YYYY/MM/DD;HH:MM;RUT
  ```
    - **Fecha**: `YYYY/MM/DD`
    - **Hora**: `HH:MM`
    - **RUT**: Formato chileno con guion y dígito verificador.

#### **2. Procesamiento**
- Leer el archivo línea por línea.
- Separar las marcas en:
    - **Ingreso**: Si la hora es antes de las 12:00.
    - **Salida**: Si la hora es después de las 12:00.

#### **3. Almacenamiento**
- Insertar las marcas en la tabla `marcas_asistencia`.
- Rechazar marcas duplicadas o líneas mal formateadas.

#### **4. Validación**
- Comprobar:
    - Formato correcto (`YYYY/MM/DD;HH:MM;RUT`).
    - Hora válida (`HH:MM`).
    - Fecha válida (`YYYY/MM/DD`).
    - RUT válido (incluyendo dígito verificador).

#### **5. Respuesta**
- Reportar:
    - Cantidad de marcas procesadas exitosamente.
    - Líneas rechazadas y sus errores.

---

### **Endpoints del Microservicio**

| **Método** | **Endpoint**               | **Descripción**                                  |
|------------|----------------------------|------------------------------------------------|
| `POST`     | `/api/marcas/upload`       | Importar marcas desde un archivo `DATA.TXT`.   |
| `GET`      | `/api/marcas`              | Listar todas las marcas almacenadas.           |
| `GET`      | `/api/marcas/{rut}/{mes}`  | Consultar marcas por RUT y mes.                |

---

### **Modelo de Datos**

#### **Tabla: `marcas_asistencia`**
Esta tabla ya existe en la base de datos y tiene la siguiente estructura:

| **Campo**            | **Tipo**        | **Descripción**                               |
|-----------------------|----------------|-----------------------------------------------|
| `id_marcas_asistencia` | `SERIAL`       | Identificador único.                         |
| `fecha`               | `DATE`         | Fecha de la marca.                           |
| `hora`                | `TIME`         | Hora de la marca.                            |
| `rut_empleado`        | `VARCHAR(12)`  | RUT del empleado.                            |
| `tipo_marca`          | `VARCHAR(10)`  | Tipo de marca: `ingreso` o `salida`.         |

---

### **Clases y Componentes**

#### **1. Entidad (`MarcaAsistenciaEntity`)**
Representa una fila en la tabla `marcas_asistencia`.

#### **2. Repositorio (`MarcaAsistenciaRepositorie`)**
Acceso a datos usando Spring Data JPA.

#### **3. Servicio (`MarcaAsistenciaService`)**
Lógica de negocio:
- Procesar el archivo `DATA.TXT`.
- Validar marcas.
- Consultar marcas por RUT y mes.

#### **4. Controlador (`MarcaAsistenciaController`)**
Exponer los endpoints para importar y consultar marcas.

---

### **Plan de Desarrollo**

1. **Definir la Entidad**:
    - Crear la clase `MarcaAsistenciaEntity` basada en la estructura de la tabla.

2. **Crear el Repositorio**:
    - Definir `MarcaAsistenciaRepositorie` para consultas CRUD y personalizadas.

3. **Implementar el Servicio**:
    - Lógica para procesar el archivo y manejar las validaciones.

4. **Configurar el Controlador**:
    - Implementar endpoints REST según las especificaciones.

5. **Probar el Microservicio**:
    - Probar con archivos válidos e inválidos.
    - Validar la consulta de marcas por RUT y mes.

---

### **Configuraciones Técnicas**

#### **Archivo `application.properties`**
```properties
# Configuración de la base de datos
spring.datasource.url=jdbc:postgresql://localhost:5432/BD_IMPORTADOR_MARCAS
spring.datasource.username=postgres
spring.datasource.password=mysecretpassword
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Puerto del servidor
server.port=8081

# Nombre del microservicio para Eureka
spring.application.name=ms-importador-marcas
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

---

### **Próximo Paso**

Confirma si deseas avanzar con la implementación de alguna parte específica del microservicio (`Entidad`, `Repositorio`, `Servicio`, o `Controlador`) o si necesitas más análisis. 😊