# Usar una imagen base de OpenJDK
FROM openjdk:21-jdk-slim-bullseye

WORKDIR /app

ARG JAR_FILE=target/ms-justificativos-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
