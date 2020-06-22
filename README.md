# tasks-crud-rest
Application created during bootcamp.

### Demo
 - demo: https://sebeni.github.io/
(due to heroku free server limitations, first page visit may take a while to load info from database)
 - API documentation  (swagger): https://rocky-citadel-33586.herokuapp.com/swagger-ui.html#/

### Description
Frontend created in html + css + javascript (biblioteka jQuery 3.4.1)

Backend (Java 1.8) build with Gradle. Used dependencies: 
spring boot (web, data-jpa, validation, mail, actuator, thymeleaf, junit), h2database, mysql, postgresql, lombok, swagger, gson

App gives ability to save, modify and delete tasks in database and connected Trello account. It also automatically sends informational email (thymeleaf templates) once a day and after creation of new task.
