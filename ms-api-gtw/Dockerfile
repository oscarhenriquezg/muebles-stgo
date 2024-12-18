FROM openjdk:21-jdk-slim-bullseye

# Crear directorio de trabajo
WORKDIR /app

# Copiar el JAR al contenedor
COPY target/ms-api-gtw-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto del API Gateway
EXPOSE 8080

# Comando de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]
