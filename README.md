# Meli challenge
Este proyecto se genero com [Spring Boot](http://projects.spring.io/spring-boot/) 
- Proyecto: Maven
- Lenguaje: Java
- Version 3.1.1
  
## Requerimientos
Para construir y ejecutar la aplicación necesita:

- [Java 17](https://www.oracle.com/java/technologies/downloads/#java8)
- [Maven](https://maven.apache.org)

## Ejecutar la aplicación localmente
1. Clonar el repositorio
```shell
git clone https://github.com/hawerforerouniandes/meli-challenge.git
```
2. Hay varias formas de ejecutar una aplicación Spring Boot en su máquina local:
- Opción 1. Ejecute el método `main` en la clase `com.challenge.meli.MeliChallengeApplication` desde su IDE.
- Opción 2. Con [Maven](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) asi:
```shell
mvnw.cmd spring-boot:run
```
3. El servidor Apache Tomcat integrado de Spring Boot actúa como un servidor web y escucha las solicitudes en el puerto localhost 8080. Abra su navegador y e ingrese la siguiente dirección para acceder directamente al Swagger de api:
```shell
http://localhost:8080/swagger-ui/index.html
```
## Ejecutar pruebas unitarias
1. Hay varias formas de ejecutar las pruebas unitarias en su máquina local:
- Opción 1. Ejecute el método `Run tests` en el directorio  `com.challenge.meli` desde su IDE.
- Opción 2. Con [Maven](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) asi:
```shell
mvn test
```
2. Una vez ejecutadas las pruebas podemos visualizar el reporte de covertura en la siguiente ruta `target/site/jacoco/index.html`

