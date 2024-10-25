# API de Precios - Proyecto de Comercio Electrónico

Este proyecto es una API para la gestión de precios en un sistema de comercio electrónico. Permite a los clientes consultar los precios de productos específicos en función de varios parámetros como la fecha de aplicación, el ID del producto y el ID de la marca.

## Tabla de Contenidos

- [Descripción](#descripción)
- [Requisitos Previos](#requisitos-previos)
- [Instalación](#instalación)
- [Ejecución](#ejecución)
- [Documentación API](#documentación-api)
- [H2 Database](#h2-database)
- [Pruebas](#pruebas)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Contribuciones](#contribuciones)
- [Contacto](#contacto)

## Descripción

La API permite gestionar y consultar precios de productos dentro de un entorno de comercio electrónico. Proporciona puntos finales para obtener precios basados en parámetros como:

- **productId**: ID del producto.
- **brandId**: ID de la marca.
- **applicationDate**: Fecha y hora en la que se desea consultar el precio.

## Requisitos Previos

Antes de empezar, asegúrate de tener instalado lo siguiente:

- **Java 17** o superior
- **Maven 3.6.3** o superior
- **Spring Boot 3.x**
- **Git** (opcional, para clonar el repositorio)

## Instalación

1. Clona el repositorio:

   ```bash
   git clone https://github.com/luiscont88/capitole-inditex.git
   cd capitole-inditex

2. Compila y construye el proyecto usando Maven:

    ```bash
    mvn clean install

## Ejecución

Para ejecutar la aplicación localmente:

    mvn spring-boot:run

La aplicación se iniciará en http://localhost:8080.

## Documentación API

La documentación de la API está disponible mediante springdoc-openapi y se puede acceder a través de Swagger UI en la siguiente URL:

    http://localhost:8080/swagger-ui/index.html

## H2 Database

Visualizar la base de datos H2 que se utiliza en la aplicación:

    http://localhost:8080/h2-console/

## Pruebas

El proyecto incluye un conjunto de pruebas unitarias y de integración las cuales cuentan con su propio perfil.

Ejecutar test unitarios:

    mvn test

Ejecutar test de integración:

    mvn verify -P integration-test

Ejecutar clean install sin test:

    mvn clean install -DskipTests

## Tecnologías Utilizadas

- Java 17.
- Spring Boot 3.x
- Maven.
- springdoc-openapi para la documentación de la API
- JUnit para pruebas unitarias.
- H2 database 2.2.x

## Contribuciones

Las contribuciones son bienvenidas. Si deseas contribuir:

1. Haz un fork del proyecto.
2. Crea una nueva rama (git checkout -b feature/nueva-funcionalidad).
3. Realiza tus cambios.
4. Haz un commit de tus cambios (git commit -m 'Añadida nueva funcionalidad').
5. Sube los cambios a tu fork (git push origin feature/nueva-funcionalidad).
6. Abre un Pull Request en este repositorio.

## Contacto

Si tienes alguna pregunta o sugerencia, no dudes en contactar a:

- Nombre: Luis Contreras
- Email: luis.contreras.20@gmail.com
- LinkedIn: luiscontreras88