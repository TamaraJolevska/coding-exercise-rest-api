# coding-exercise-rest-api
Coding exercise using REST APIs.

This is a Spring Boot application for fetching data from REST API endpoint and displaying on the web. The application features:
- Displaying data from REST API endpoint and filtering the matches based on team name or status (live, completed)
- Thymeleaf UI for frontend display
- Swagger UI documentation
- Caching and scheduled job for reading source data
- Unit tests

There are four REST API endpoints:
- show all matches (/matches)
- show live matches (/matches/live)
- show completed matches (/matches/completed)
- filter matches by team name (/matches/filter)

Project structure:
```
src/
├── main/
│   ├── java/com/codingexercise/restapi     → Application source code
│   ├── resources/
│   │   ├── templates/                      → Thymeleaf HTML views
│   │   └── static/                         → CSS assets
├── test/                                   → Unit tests
```

### OPTION A:

To test the application without the source code, download the .jar file from the releases or create a .jar file using the command ./mvnw clean package.
Run the .jar file in the command prompt using the command:
java -jar restapi-codingexercize-0.0.1.jar

### OPTION B:

### 1. Prerequisites

- Java 17 (https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html#amazon-corretto-yum-urls)
- Maven (https://maven.apache.org/download.cgi)
- Visual Studio Code with Java extensions (Extension Pack for Java)

### 2. Clone the Repository

git clone https://github.com/TamaraJolevska/coding-exercise-rest-api.git

### 3. Run the application using Maven

 ./mvnw clean spring-boot:run 


### 4. Open the application in the browser

http://localhost:9090/matches

### 5. Swagger documentation

Access the Swagger documentation using the following url after deploying the application: http://localhost:9090/swagger-ui/index.html

### 6. Test the unit tests in RestapiApplicationTests.java

### 7. Test caching and scheduled job
The scheduled job is done every minute - debug log example: Data refreshed 2025-06-23T22:11:00.095728900
To test the caching, load the data in the browser. On the first load you can see the log "Fetching data without cache.". After refreshing or changing the view, the log is 
no longer visible.
You can modify caching or scheduling in MatchService.java
