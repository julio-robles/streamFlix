# StreamFlix

Aplicación de streaming de películas desarrollada con Java, Spring Boot y Maven.

## Requisitos previos

- Java 17 o superior
- Maven 3.8+
- (Opcional) Docker para bases de datos o despliegue

## Configuración

1. Clona el repositorio:
   ```
   git clone https://github.com/tu-usuario/streamflix.git cd streamflix
   ```
2. Configura la base de datos en `src/main/resources/application.properties`:
 Para MySQL:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/streamflix
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   ```
3. (Opcional) Levanta la base de datos con Docker:
```bash
   docker run --name streamflix-db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=streamflix -p 3306:3306 -d mysql:latest
```

## Ejecución

Para compilar y ejecutar la aplicación:
```bash
  mvn clean install mvn spring-boot:run
```

La aplicación estará disponible en [http://localhost:8080](http://localhost:8080).

## Pruebas

Para ejecutar los tests:
```bash
    mvn test
```

## Endpoints principales

- `GET /movies` — Lista todas las películas
- `POST /users/register` — Registro de usuario
- `POST /ratings` — Añadir valoración a una película

## Notas

- Modifica los parámetros de conexión según tu entorno.
- Consulta la documentación de cada endpoint en el código fuente o añade Swagger para documentación automática.

---
