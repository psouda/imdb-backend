# Java API Backend Project using Spring Boot, Lombok, JPA, and Hibernate
This is a backend web application for a mini-MVP of an IMDB application. The application provides persistence layer, business logic and RESTful API for users to search for films, rate films, and receive recommendations based on previous ratings, genres, and directors.

## Project Setup
Before running this project, make sure you have the following software installed:
- Java Development Kit (JDK) 11 or higher
- Maven

## Technologies Used
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Lombok](https://projectlombok.org/)
- [JPA and Hibernate](https://hibernate.org/)
- [Spring Security](https://spring.io/projects/spring-security)
- [H2](https://www.h2database.com)

## Use
To use the project's APIs, follow these steps:

1. Register a user by sending a POST request to the /api/v1/users/register endpoint. The request should include a JSON payload with the user's information, such as username, email, and password.
2. Authenticate the user by sending a POST request to the /api/v1/users/authenticate endpoint. The request should include the user's username and password. If the authentication is successful, the response will include a JWT token.
3. To call each API, include the JWT token as an Authorization header in your requests. The header value should be in the format Bearer <token>. For example, if your token is eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c, the header value should be Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c.

Note that before calling any API, you must register a user and authenticate them to obtain a JWT token.

## Call APIs using postman
The postman collection for APIs is added under the docs/postman directory. You can import this collection into Postman and use it to test the API endpoints.

## Project Structure
This project has the following directory structure:
```
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───imdb
│   │   │           └───imdbbackend
│   │   │               ├───aop
│   │   │               │   └───logging
│   │   │               ├───config
│   │   │               │   └───security
│   │   │               ├───controller
│   │   │               │   └───security
│   │   │               ├───dto
│   │   │               │   ├───request
│   │   │               │   │   └───security
│   │   │               │   └───response
│   │   │               │       └───security
│   │   │               ├───entity
│   │   │               │   └───security
│   │   │               ├───enumeration
│   │   │               │   └───security
│   │   │               ├───exception
│   │   │               │   └───handler
│   │   │               ├───filter
│   │   │               │   └───security
│   │   │               ├───repository
│   │   │               │   └───security
│   │   │               ├───service
│   │   │               │   ├───mapper
│   │   │               │   └───security
│   │   │               └───util
│   │   └───resources
│   │       ├───static
│   │       └───templates
│   └───test
│       └───java
│           └───com
│               └───imdb
│                   └───imdbbackend
│                       ├───controller
│                       ├───repository
│                       └───service
│                           └───security
└───target
    ├───classes
    │   └───de
    │       └───com
    │           └───imdb
    │               ├───aop
    │               │   └───logging
    │               ├───config
    │               │   └───security
    │               ├───controller
    │               │   └───security
    │               ├───dto
    │               │   ├───request
    │               │   │   └───security
    │               │   └───response
    │               │       └───security
    │               ├───entity
    │               │   └───security
    │               ├───enumeration
    │               │   └───security
    │               ├───exception
    │               │   └───handler
    │               ├───filter
    │               │   └───security
    │               ├───repository
    │               │   └───security
    │               ├───service
    │               │   ├───mapper
    │               │   └───security
    │               └───util
    ├───generated-sources
    │   └───annotations
    ├───generated-test-sources
    │   └───test-annotations
    ├───maven-status
    │   └───maven-compiler-plugin
    │       ├───compile
    │       │   └───default-compile
    │       └───testCompile
    │           └───default-testCompile
    ├───surefire-reports
    └───test-classes
        └───com
            └───imdb
                └───imdbbackend
                    ├───controller
                    ├───repository
                    └───service
                        └───security

```

Here is an overview of the most important directories and files:

`src/main/java`: Contains the Java source code for the project.
`src/main/resources`: Contains the application properties and SQL files.
`src/test/java`: Contains the Java source code for the project's tests.
`src/test/resources`: Contains the application test properties.


## Configuration
The `application.properties` file located in the src/main/resources directory contains the project configuration. Here is an example:

```properties
# Database properties
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url=jdbc:h2:mem:imdbdb
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.generate-ddl=true
spring.jpa.properties.hibernate.show_sql=false
spring.jpa.defer-datasource-initialization=true

#Log
logging.level.com.imdb=debug

```

## API Endpoints
This project provides the following API endpoints:

### Authentication
`POST /api/v1/auth/register`
Register a new user account.

`POST /api/v1/auth/authenticate`
Authenticate a user and generate a JWT token for the session.

### Films
`POST /api/v1/films` - Create a new film

`GET /api/v1/films` - Retrieve a list of all films

`GET /api/v1/films/{id}` - Retrieve a specific film by ID

`GET /api/v1/films/search?title={title}` - Search for films by title

`POST /api/v1/films/{id}/rate` - Rate a film by ID

### Recommendations
`GET /api/v1/recommendations/{userId}` - Retrieve film recommendations for a given user ID.


## Authors
Payam Soudachi - psoudachi@gmail.com