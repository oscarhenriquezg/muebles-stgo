# Usar una imagen base de Java
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el JAR generado al contenedor
COPY target/ms-importador-marcas-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto (en este caso, es dinámico por config server)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
