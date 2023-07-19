
# arithmetic API
[![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white)](https://localhost:8080/swagger-ui/index.html)
[![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white)](devTools/postman)

## Description:
API para prueba de concepto de las tecnologías utilizadas

## Index:
- [Stack](#stack)
- [How to Run](#how-to-run)
    - [Config](#config)
    - [Build](#build)
    - [Run](#run)
    - [Unit Tests](#unit-tests)

### Stack 🛠️
- java 20
- spring-boot 3.1.1
- [postgresql](https://www.postgresql.org/)
- [apache-kafka](https://kafka.apache.org/)


### How to Run

#### Config

#### Build

Ejecuta éste comando para instalar las dependencias y buildear el proyecto:

```bash
$ ./gradlew build
```

#### Run

Para correr el proyecto:

```bash
$ ./gradlew bootRun
```

El entorno de desarrollo corre sobre <http://localhost:8080>. Ejecuta una llamada GET de prueba en <http://localhost:8080/actuator>

#### Unit Tests

Para correr los tests unitarios
```bash
$ ./gradlew test
```