# Usar una imagen base de OpenJDK adecuada para Java 21
FROM openjdk:21-jdk-slim

# Crear un directorio para la aplicación
WORKDIR /app

# Copiar el archivo JAR generado al contenedor
ARG JAR_FILE=target/ms-eureka-server-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Exponer el puerto de Eureka Server
EXPOSE 8761

# Comando para ejecutar Eureka Server
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
