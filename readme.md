### 1 Herramientas de desarrollo

* IntelliJ
* JAVA 11
* Maven
*******

### 2 Ejecutar aplicación

- Debe tener el puerto 8080 libre
- mvn clean install
- mvn spring-boot:run

Luego de lo anterior la aplicación se ejecutar en http://localhost:8080/

### 2 Acceder al swagger
#### Swagger http://localhost:8080/swagger-ui/index.html#/
#### GetUser  curl -X GET "http://localhost:8080/user" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJkaWVnb2FsaXJpb2dtQGdtYWlsLmNvbSIsInN1YiI6IkFkc2QiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjcxMjkyMTgzLCJleHAiOjE2NzEyOTI3ODN9.tXvYZrP-V4qufJpQBF9SyJAx55juWiyRo37I-CRKGiPIpLcszJ9eJPefpcFUveiPXxVcV86I69dgbmM73Mh2Eg"
#### CreateUser curl -X POST "http://localhost:8080/user" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"email\": \"diegoaliriogm@gmail.com\", \"name\": \"Adsd\", \"password\": \"A...wdd#1212\", \"phones\": [ { \"cityCode\": \"string\", \"contryCode\": \"string\", \"number\": \"string\" } ]}"