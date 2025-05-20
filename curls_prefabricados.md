# Crear
curl -X POST http://localhost:8080/api/v1/rentals ^
-H "Content-Type: application/json" ^
-d "{\"userId\":\"11111111-1111-1111-1111-111111111111\",\"movieId\":\"11111111-1111-1111-1111-111111111111\",\"resolution\":\"HD\"}"

# Finalizar
curl -X PATCH http://localhost:8080/api/v1/rentals/11111111-1111-1111-1111-111111111111/finish

# Listar paginado (page=0, size=20 por defecto)
curl "http://localhost:8080/api/v1/rentals?userId=11111111-1111-1111-1111-111111111111"

# Errores comunes
| Mensaje                          | Causa                                                          | Solución                                    |
| -------------------------------- | -------------------------------------------------------------- | ------------------------------------------- |
| 409 Conflict en el POST          | Ya existe un alquiler **ACTIVE** para ese `userId` + `movieId` | Cambia `movieId` o espera 48 h / finaliza   |
| 400 Bad Request con “resolution” | El valor no es `HD` ni `4K`                                    | Corrige el *payload*                        |
| 404 Not Found al finalizar       | `rentalId` inexistente                                         | Asegúrate de usar el ID devuelto en el POST |
| 422 Unprocessable Entity         | Campos nulos / UUID mal formados                               | Valida los datos de entrada                 |
