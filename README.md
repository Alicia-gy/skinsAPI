RestAPI that manages skins for a videogame system

This project was build with:
- Java 21
- Spring Boot
- Gradle
- MySQL

Default properties are as follow. Make sure to follow them or change as needed:

    server.port=8080
  
    spring.datasource.url= jdbc:mysql://localhost:3306/testdb?useSSL=false
    spring.datasource.username= root
    spring.datasource.password= 1234
  
Endpoints:

- GET api/v1/skins/available - Return list of all available skins to buy.
- POST api/v1/skins/buy/{id] - Allows user to buy a skin and save it in database.
- GET api/v1/skins/myskins - Return list of all owned skins.
- PUT api/v1/skins/colour/{id} - Allow user to change colour of an owned skin.
- DELETE api/v1/skins/delete/{id} - Allow user to delete an owned skin.
- GET api/v1/skin/getskin/{id} – Return a specific skin information.
