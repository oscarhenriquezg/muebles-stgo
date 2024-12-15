# Usar una imagen base de OpenJDK
FROM openjdk:21-jdk-slim-bullseye

# Crear un directorio para la aplicación
WORKDIR /app

# Copiar el archivo JAR generado por tu aplicación al contenedor
COPY target/ms-calculo-planilla-sueldos-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto del microservicio (8083 en este caso)
EXPOSE 8083

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
