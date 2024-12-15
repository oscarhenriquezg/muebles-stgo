# Usar una imagen base de OpenJDK
FROM openjdk:21-jdk-slim-bullseye

# Crear un volumen temporal
VOLUME /tmp

# Agregar el archivo JAR generado por Maven
ARG JAR_FILE=target/ms-autorizacion-hhee-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Puerto expuesto (puerto dinámico, dependerá del cluster)
EXPOSE 8082

# Comando de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]
