# Usar una imagen base de OpenJDK adecuada para Java 21
FROM openjdk:21-jdk-slim

# Crear un directorio para la aplicación
WORKDIR /app

# Copiar el archivo JAR generado al contenedor
ARG JAR_FILE=target/ms-config-server-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Exponer el puerto donde correrá el microservicio
EXPOSE 8888

# Comando para ejecutar el microservicio
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
