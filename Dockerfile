
# Utiliza la imagen oficial de Maven como imagen base para compilar el proyecto
FROM maven:3.8.4-openjdk-17 AS build

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo pom.xml y los archivos fuente necesarios al contenedor
COPY pom.xml .
COPY src/ ./src/

# Compila el proyecto usando Maven y copia los archivos compilados en el directorio "target/classes"
RUN mvn package

# Crea un nuevo stage (etapa) para el contenedor final con JRE
FROM eclipse-temurin:17-jdk-alpine

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia los archivos compilados y el archivo Manifest.txt desde el stage anterior (build) al contenedor
COPY --from=build /app/target/classes/ ./classes/
COPY Manifest.txt .

# Genera el archivo JAR con el archivo Manifest y las clases compiladas
RUN jar cfm app.jar Manifest.txt -C classes .

# Establece el comando para ejecutar el JAR
CMD ["java", "-jar", "app.jar"]





