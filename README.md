#Ejercicio 1 - Verificación de Palíndromo

Este proyecto contiene un microservicio desarrollado en **Spring Boot** que recibe un JSON con una cadena y devuelve información sobre si es un **palíndromo**, la longitud de la cadena y la cantidad de caracteres especiales.  

El servicio está organizado siguiendo **buenas prácticas**, con separación en Controller, Service y DTOs (Record), y está documentado con **Swagger** para pruebas y visualización de la API.

---

## Funcionalidades
- Recibe un JSON con el parámetro `palindromo` en el body de la petición.
- Devuelve:
  - `lengthCadena`: longitud total de la cadena.
  - `isPalindromo`: 1 si es palíndromo, 0 si no lo es.
  - `lengthCaracteresEspeciales`: cantidad de caracteres especiales (excluyendo letras y números, opcionalmente espacios según implementación).
- Documentado en Swagger para prueba interactiva.
- Manejo de excepciones en el Service.

---

## Estructura del proyecto
src/main/java/com/Ejercicios/ApiREST/ejercicio1/
├── controller/
│ └── LogicaController.java # Recibe el JSON de entrada y devuelve la respuesta
├── service/
│ └── LogicaService.java # Contiene la lógica para verificar palíndromos y contar caracteres
├── dto/
│ └── PalindromoRequest.java # Record para recibir el JSON de entrada
│ └── PalindromoResponse.java # Record para devolver la información procesada


---

### Detalles de cada capa

- **Controller**
  - Endpoint `POST /ejercicio1/palindromo`.
  - Recibe el JSON de entrada (`palindromo`) y llama al Service.
  - Devuelve `200 OK` con el JSON de salida.

- **Service**
- `palindromo(LogicaRequest request)`  
  - Evalúa si la cadena es palíndromo (`isPalindromo`).
  - Calcula la longitud total de la cadena (`lengthCadena`).
  - Calcula la cantidad de caracteres especiales (`lengthCaracteresEspeciales`).
  - Devuelve un `LogicaResponse` con todos los resultados.

- `isPalindromo(String palabras)`  
  - Normaliza la cadena (minúsculas, elimina espacios).
  - Compara con el reverso de la cadena.
  - Devuelve `true` si es palíndromo, `false` en caso contrario.

- `lengthCadena(String cadena)`  
  - Devuelve la longitud total de la cadena.
  - Retorna `0` si la cadena es `null`.

- `lengthCaracteresEspeciales(String cadena)`  
  - Cuenta caracteres que **no son letras, números ni espacios**.
  - Retorna `0` si la cadena es `null`.

---
- **DTOs / Records**
  - `PalindromoRequest` → recibe la cadena a evaluar.
  - `PalindromoResponse` → devuelve los resultados de la evaluación en JSON.

---

## Pruebas Unitarias
- Se utilizan **JUnit 5**.
- Escenarios probados:
  
## lengthCadena(String cadena)
- "hola!" → 5
- null → 0

## isPalindromo(String palabras)
- "yo Hago yoga hoy" → true
- "hola mundo" → false
- null → false

## lengthCaracteresEspeciales(String cadena)
- "#$%&!" → 5
- "hola mundo" → 0
- null → 0

## palindromo(LogicaRequest request) (flujo completo)
- "anita lava la tina" →
  - lengthCadena: 18
  - isPalindromo: 1
  - lengthCaracteresEspeciales: 0
---

## 📖 Swagger / OpenAPI

- Swagger UI permite probar directamente el endpoint `POST /ejercicio1/palindromo`.
- Documenta el **request body** y el **response body**.
- Ejemplo en Swagger:

**Request Body:**
```json
{
  "palindromo": "anita lava la tina"
}
Response Body:

json
Copiar código
{
  "lengthCadena": 18,
  "isPalindromo": 1,
  "lengthCaracteresEspeciales": 0
}
```

# Ejercicio2 - Microservicio REST - Consumo de API Externa

Este proyecto es un microservicio desarrollado en **Spring Boot** que consume un API REST externo, procesa la respuesta y la expone a través de un endpoint. Está diseñado siguiendo buenas prácticas de arquitectura, con separación de capas, manejo de excepciones, pruebas unitarias y documentación con Swagger.

---

## Funcionalidades
- Consumir un API REST externo usando HTTP GET.
- Recibir un parámetro `nombre` desde la URL.
- Extraer información del JSON devuelto por el API externo (nombre y fecha).
- Manejar códigos de respuesta:
  - `302` → Coincidencia encontrada.
  - `404` → Sin resultados.
- Imprimir en consola los resultados y mensajes según el código de respuesta.
- Documentación de la API disponible con **Swagger UI**.
- Pruebas unitarias para validar la lógica de servicio.

---

## Estructura del proyecto
src/main/java/com/Ejercicios/ApiREST/ejercicioMicroservicio/
├── controller/
│ └── MicroservicioController.java # Maneja las peticiones HTTP y devuelve ResponseEntity
├── service/
│ └── MicroservicioService.java # Lógica de consumo del API externo y procesamiento
├── dto/
│ └── MicroservicioResponse.java # Objeto de transferencia de datos (fecha y nombre)

---

### Detalles de cada capa
- **Controller**
  - Se encarga de recibir la petición HTTP.
  - Valida si la respuesta del servicio es `null` y devuelve `404` si no hay resultados.
  - Devuelve `302` cuando hay coincidencias.
  - Documentado con anotaciones `@Operation` de Swagger.

- **Service**
  - Contiene la lógica de consumo del API externo.
  - Maneja códigos de respuesta HTTP del API externo (`200`, `302`, `404`) y excepciones.
  - Extrae el nombre y la fecha del JSON utilizando `split` de Java, como se especifica en el ejercicio.
  - Imprime en consola los resultados y mensajes según el código.

- **DTOs**
  - `MicroservicioResponse` encapsula la información devuelta (`fecha` y `nombre`) en formato JSON.
  - Permite separar la lógica de negocio del formato de salida de la API.

---

## Pruebas Unitarias
- Se utilizan **JUnit 5** y **Mockito**.
- Se prueban los siguientes escenarios:
  - Coincidencia encontrada → verificar que `MicroservicioResponse` no sea `null`.
  - Sin resultados → manejo correcto de `404` y mensaje en consola.
  - Manejo de excepciones → simular errores de conexión y validar mensajes de error.

---

## Swagger / OpenAPI
- Swagger UI está configurado para documentar el endpoint del microservicio.
- Permite probar la API directamente desde el navegador.
- Documenta los códigos HTTP esperados (`302`, `404`, `500`) y el formato de respuesta.
- URL de Swagger UI : `http://localhost:8081/swagger-ui.html` .

---

## Buenas Prácticas Implementadas
- **Separación de capas**: Controller → Service → DTO.
- **Manejo de excepciones** con `try-catch` y mensajes claros en consola.
- **No exposición de objetos `null`** en la respuesta HTTP; se usan códigos HTTP adecuados (`404`, `302`).
- **Documentación con Swagger** para facilitar pruebas y entendimiento de la API.
- **Pruebas unitarias** para garantizar el correcto funcionamiento de la lógica de negocio.

---

## Tecnologías
- Java 22
- Spring Boot 3.x
- Swagger (springdoc-openapi)
- JUnit 5 + Mockito
- Maven

---

## Ejemplo de uso
**Endpoint:**
Uso correcto:
GET /ejercicio2/consumir-api?nombre=Adrian

**Respuesta 302 (coincidencia encontrada):**
```json
{
  "fecha": "Sun Feb 28 18:17:27 CST 2021",
  "nombre": "Adrian"
}
```
Uso incorrecto:
GET /ejercicio2/consumir-api?nombre=Eduardo
Respuesta 404 (sin resultados):
```json
{
  "mensaje": "Sin resultados."
}
```
