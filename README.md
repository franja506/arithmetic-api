
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
- [liquibase](https://www.liquibase.org/)
- [docker](https://www.docker.com/)
  - [docker-compose](https://docs.docker.com/compose/)


### How to Run

#### Prerequisites

Posicionarse en la carpeta devTools y ejecutar el [docker-compose](devTools/docker-compose.yml). 
Esto levanta un postgresql y un kafka local.


#### Config
- ##### datasource
  Configurar la conexión a la base de datos
  ```yaml
  datasource:
    url: ${POSTGRES_URL}
    driver-class-name: org.postgresql.Driver
    username: ${POSTGRES_USER}
    password: ${POSTGRES_PASS}
  ```

  En _Run/Debug Configurations_ -> _Build and run_ -> _VM Options_ agregar
    `POSTGRES_URL=jdbc:postgresql://localhost:5432/arithmetic` 
    `POSTGRES_USER=franjagonca`
    `POSTGRES_PASS=franjagonca`

- ##### brokers
  Configurar la conexión a kafka
  ```yaml
  kafka:
    bootstrap-servers: ${KAFKA_SERVERS}
  ```

  En _Run/Debug Configurations_ -> _Build and run_ -> _VM Options_ agregar
  `KAFKA_SERVERS=localhost:9092`


#### Build

Ejecuta éste comando para instalar las dependencias y buildear el proyecto:

```bash
$ ./gradlew build
```

#### Build Docker Images

Ejecuta éste comando para generar una imagen de docker del proyecto. Dentro del directorio raiz del 
proyecto ejecutar el siguiente script 

```bash
$ sudo docker build --build-arg appName=arithmetic-api --build-arg version=1.0.0 --tag arithmetic-api .
```

cambiar la version de acuerdo a la actual del proyecto.

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