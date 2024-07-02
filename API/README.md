# HerdApp API

> RESTful Java API for managing an in-memory Herd database.</br>

---
### Table of Contents

 - [Description](#description)
 - [How to Use](#how-to-use)

---
## Description
HerdApp API is a Spring REST API for an in-memory PostgreSQL based Herd Management system. HerdApp API supports CRUD with all Create (POST), Read (GET), Update (PUT) and Delete (DELETE) functions for managing your herd.

#### Technology/Requirements:

- Postgres (_Version 14.5_)
- Java (_Oracle OpenJDK Version 18_)
- Maven (_Version 3.8.1_)
- Spring Web
- Spring Data JPA
- PostgreSQL Driver

_Made with Spring Initialzr_

__Dependencies Used:__
- org.springframework.boot:spring-boot-starter-data-jpa:2.7.2
- org.springframework.boot:spring-boot-starter-web:2.7.2
- org.postgresql:postgresql:42.3.6
- org.springframework.boot:spring-boot-test.2.7.2
- com.fasterxml.jackson.core:jackson-databind:2.13.3

---
## How to Use
### Installation:

- Ensure versions are correctly configured as shown under Technology/Requirements
- Java Version can be checked with 'java -version' and, if updated:
```
java version "18" 2022-03-22
Java(TM) SE Runtime Environment (build 18+36-2087)
Java HotSpot(TM) 64-Bit Server VM (build 18+36-2087, mixed mode, sharing)
```
- Configure application.properties to ensure your local path and username/password details are correct for the PostgreSQL database.
```
spring.datasource.url=jdbc:postgresql://localhost:5432/cow
spring.datasource.username=postgres
spring.datasource.password=password
```
- Navigate on terminal to target and run API-0.0.1-SNAPSHOT.jar with:
```
java -jar API-0.0.1-SNAPSHOT.jar
```
- Localhost should then be up and running on port 8080, or the port set manually by you.
- Navigate to: http://localhost:8080/cows
- To use the methods (POST, GET, PUT, DELETE), either configure HTTP headers in an IDE such as IntelliJ or through Postman.

### Method Call Syntax:
#### POST:
Post is our create method. </br>
Example:
```
POST http://localhost:8080/cows
Content-Type: application/json

{
    "collarId": "261",
    "cowNumber": "88261",
    "collarStatus": "Healthy"
}
```
This method will return with the expected output in the assignment outline as part of the Response Body.
</br>
However, note that cowNumber and collarId are considered unique with one-to-one mapping between individual collars and individual cows.
</br>
Two cows can't have the same cowId, or be wearing the same collar.
</br>
#### GET:
GET is our GET method.</br>
Example:
```
GET http://localhost:8080/cows
```
Will return all cows currently in the database.
</br>
#### PUT:
Put is for updating a cow entity.</br>
General Form:
```
PUT http://localhost:8080/cows/{id}?{attribute_to_change='x'}
```
Example:
```
PUT http://localhost:8080/cows/d90f6531-6156-4d53-b99f-6927105c59dc?collarId=616
Content-Type: application/json
```
#### DELETE:
DELETE removes a cow entity based on their cowId.
Therefore, it is best to use a get request first to ensure you have the right UUID2 Id.
Example:
```
DELETE http://localhost:8080/cows/d90f6531-6156-4d53-b99f-6927105c59dc
```
---

In general, cowId, collarId and cowNum must always be unique and there are designed error messages to notify the user of these.