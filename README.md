# Meli challenge
Este proyecto se genero con [Spring Boot](http://projects.spring.io/spring-boot/) 
- Proyecto: Maven
- Lenguaje: Java 17
- Version 3.0.5
  
## Requerimientos 📋
Para construir y ejecutar la aplicación necesita:

- [Java 17](https://www.oracle.com/java/technologies/downloads/#java8)
- [Maven](https://maven.apache.org)

## Ejecutar la aplicación localmente 🚀
1. Clonar el repositorio
```shell
git clone https://github.com/hawerforerouniandes/meli-challenge.git
```
2. Hay varias formas de ejecutar una aplicación Spring Boot en su máquina local:
- Opción 1. Ejecute el método `main` en la clase `com.challenge.meli.MeliChallengeApplication` desde su IDE.
- Opción 2. Con [Maven](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) asi:
```shell
./mvnw spring-boot:run 
```
![run](https://raw.githubusercontent.com/hawerforerouniandes/assets/master/img/run.png)
<sup><sub>Nota: Verificar que el puerto 8080 este libre</sub></sup>
3. El servidor Apache Tomcat integrado de Spring Boot actúa como un servidor web y escucha las solicitudes en el puerto localhost 8080. Abra su navegador y e ingrese la siguiente dirección para acceder directamente al Swagger de api:
```shell
http://localhost:8080/swagger-ui/index.html
```
![Swagger](https://raw.githubusercontent.com/hawerforerouniandes/assets/master/img/swagger.png)
4. (Opcional) ejecución con Docker
```shell
docker build -t meli-challenge-v1 . 
docker run -d -p 8080:8080 meli-challenge-v1
```
## Ejecutar pruebas unitarias 🐞
1. Hay varias formas de ejecutar las pruebas unitarias en su máquina local:
- Opción 1. Ejecute el método `Run tests` en el directorio  `com.challenge.meli` desde su IDE.
- Opción 2. Con [Maven](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) asi:
```shell
./mvnw test
```
![test](https://raw.githubusercontent.com/hawerforerouniandes/assets/master/img/test.png)
2. Una vez ejecutadas las pruebas podemos visualizar el reporte de covertura en la siguiente ruta `target/site/jacoco/index.html`
![Coverage](https://raw.githubusercontent.com/hawerforerouniandes/assets/master/img/coverage2.png)
## Estructura proyecto 📂
````
├── .github   # Pipeline github actions
├── src       # Código fuente de la aplicación
|   ├── main
|   |    └── appengine   # Despliegue en appengine gcp
|   |    └── java         # Código java
|   |       └── config       # Archivos de configuración
|   |       └── controllers  # Controladores peticiones http
|   |       └── dto          # Objetos de transferencia
|   |       └── models       # Información persistente
|   |       └── repositories # Acceso a los datos
|   |       └── services     # Lógica de negocio
|   |       └── utils        # Clases de utilitarias
|   |    └── resources   # propiedades de la app
|   ├── test     # Test unitarios
|   ├── taget    # Archivos generados para despliegue
└────── pom.xml # Descriptor del proyecto
└────── README.md # Estás aquí
````
## Despliegue en producción 🌍
1. La aplicación Spring Boot es desplegada en el entorno estándar de App Engine de GCP, en el siguiente se encuentra el codelab oficial de Google con el paso a paso actualizado en abr 21, 2023

[https://codelabs.developers.google.com/codelabs/cloud-app-engine-springboot#0](https://codelabs.developers.google.com/codelabs/cloud-app-engine-springboot#0)

2. Actualmente la aplicación se encuentra desplegada en las siguintes rutas:

- URL del API: [https://meli-challengue.uc.r.appspot.com/](https://meli-challengue.uc.r.appspot.com/)
- Swagger: [https://meli-challengue.uc.r.appspot.com/swagger-ui/index.html](https://meli-challengue.uc.r.appspot.com/swagger-ui/index.html)
- Postman: [https://documenter.getpostman.com/view/1968395/2s946idX9T](https://documenter.getpostman.com/view/1968395/2s946idX9T)

## Lógica matemática 🔢
El método `float[] getLocation(float[] distances, float[][] positions)` se resolvio con la ecuación matemática de promedio ponderado. La ecuación se deriva del concepto de centro de masa ponderado y se utiliza para calcular la ubicación estimada.
Dada una serie de puntos de referencia con coordenadas (xᵢ, yᵢ) y distancias di desde cada punto de referencia hasta la ubicación desconocida, la ecuación se define de la siguiente manera:
```shell
x = Σ(xᵢ * wᵢ) / Σwᵢ
y = Σ(yᵢ * wᵢ) / Σwᵢ
```
- x, y son las coordenadas de la ubicación estimada.
- xᵢ, yᵢ son las coordenadas  del i-ésimo punto de referencia.
- di es la distancia desde el i-ésimo punto de referencia hasta la ubicación desconocida.
- wᵢ es el peso asignado al i-ésimo punto de referencia, calculado como el inverso del cuadrado de la distancia: wᵢ = 1 / (di^2).
- Σ denota la suma sobre todos los puntos de referencia.


