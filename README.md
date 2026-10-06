# Price Service

Servicio REST desarrollado con Spring Boot para consultar el precio aplicable
a un producto de una cadena en una fecha determinada.

## Tecnologías

- Java 21
- Spring Boot 4.1.1
- Gradle
- Spring Web
- Spring Data JPA
- H2 Database
- Jakarta Validation
- JUnit 5
- MockMvc

## Arquitectura

El proyecto utiliza Arquitectura Hexagonal para desacoplar la lógica de negocio
de los detalles de infraestructura.

REST Adapter
↓
Input Port
↓
Application Service
↓
Output Port
↓
Persistence Adapter
↓
Spring Data JPA
↓
H2

## Regla de negocio

El servicio busca el precio aplicable según:

- Fecha de aplicación.
- Identificador de producto.
- Identificador de cadena.

Si existen varias tarifas aplicables en la misma fecha, se selecciona la de
mayor prioridad.

La selección se realiza directamente en base de datos para evitar recuperar
registros innecesarios.

Conceptualmente:

SELECT *
FROM prices
WHERE brand_id = ?
AND product_id = ?
AND start_date <= ?
AND end_date >= ?
ORDER BY priority DESC
LIMIT 1;

## Ejecutar la aplicación desde IntelliJ

### Requisitos

- Java 21

La aplicación estará disponible en:

http://localhost:9082

## Base de datos H2

La aplicación utiliza una base de datos H2 en memoria.

Consola:

http://localhost:9082/h2-console

Configuración:

JDBC URL: jdbc:h2:mem:pricedb
User: sa
Password:

Los datos del enunciado se cargan automáticamente al arrancar la aplicación.

## API

### Consultar precio aplicable

GET /api/v1/prices

Parámetros:

applicationDate
productId
brandId

Ejemplo:

GET http://localhost:9082/api/v1/prices?applicationDate=2020-06-14T16:00:00&productId=35455&brandId=1

Respuesta:

{
"productId": 35455,
"brandId": 1,
"priceList": 2,
"startDate": "2020-06-14T15:00:00",
"endDate": "2020-06-14T18:30:00",
"price": 25.45,
"currency": "EUR"
}

## Códigos HTTP

200 OK
Precio encontrado.

400 Bad Request
Parámetros de entrada inválidos.

404 Not Found
No existe un precio aplicable para los parámetros indicados.

500 Internal Server Error
Error inesperado.

## Tests

Para ejecutar los tests:

Windows:

gradlew.bat test

Linux/macOS:

./gradlew test

Para ejecutar el build completo:

gradlew.bat clean build

Los tests de integración validan los cinco escenarios indicados en el enunciado.

## Casos de prueba

| Fecha             | Product | Brand | Price List | Precio    |
|-------------------|---------|-------|------------|-----------|
| 2020-06-14 10:00  | 35455   | 1     | 1          | 35.50 EUR |
| 2020-06-14 16:00  | 35455   | 1     | 2          | 25.45 EUR |
| 2020-06-14 21:00  | 35455   | 1     | 1          | 35.50 EUR |
| 2020-06-15 10:00  | 35455   | 1     | 3          | 30.50 EUR |
| 2020-06-16 21:00  | 35455   | 1     | 4          | 38.95 EUR |

## Decisiones técnicas

- Arquitectura Hexagonal para separar dominio, aplicación e infraestructura.
- BigDecimal para representar importes monetarios.
- LocalDateTime para los rangos temporales.
- La selección de prioridad se realiza en base de datos.
- Se devuelve únicamente el precio aplicable.
- El dominio no depende de Spring ni JPA.
- H2 se utiliza únicamente como base de datos en memoria para la prueba.
- El manejo HTTP se mantiene en el adaptador REST.

## Versionado

v1.0.0

