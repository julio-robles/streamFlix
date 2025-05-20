## Conectar a la BD en memoria que usa Spring Boot

1. **Asegúrate** de que la aplicación está arrancada (es la que crea la BD en memoria).
2. Abre el navegador en **<http://localhost:8080/h2-console>**.
3. Rellena el formulario como sigue:

| Campo        | Valor correcto                         |
|--------------|----------------------------------------|
| Driver Class | `org.h2.Driver` (ya aparece por defecto) |
| JDBC URL     | `jdbc:h2:mem:testdb`                   |
| User Name    | `sa`                                   |
| Password     | *(vacío)*                              |
