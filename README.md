# tasks-crud-rest
Backend dla aplikacji tasks

### Demo
 - link do frontu: https://sebeni.github.io/
 - link do dokumentacji api (swagger): https://rocky-citadel-33586.herokuapp.com/swagger-ui.html#/

### Opis
Frontend napisany w html + css + javascript (biblioteka jQuery 3.4.1)

Backend działa na darmowym serwerze Heroku, dlatego po pierwszym uruchomieniu należy chwilę poczekać.

Zbudowany w Java 1.8 za pomocą Gradle. Użyte zależności: 
- spring boot (web, data-jpa, validation, mail, actuator, thymeleaf, junit), h2database, mysql, postgresql, lombok, swagger, gson

Aplikacja pozwala na zapis, modyfikację i usuwanie zadań w bazie danych oraz podłączonym koncie Trello.
