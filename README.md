## Web Quiz Engine

This is a training project from JetBrains Academy (Challenging level).<br>
In this project: REST API,
multi-users web service for creating and solving quizzes,
the service has the ability to answer questions (or quizzes)
and then see some results.

#### Tools and Technologies Used

* Java 11
* SpringBoot
* Spring Data JPA
* Spring Security
* H2 Embedded Database
* Gradle
* HTTP Basic Auth
* REST API

### API Description

<b>Register a user</b><br>
HTTP  Method: POST<br>
Path: /api/register<br>
Status: 201 (Created)<br>
Description: A new user is being registered.<br>

<b>Get all quizzes</b><br>
HTTP  Method: GET<br>
Path: /api/quizzes<br>
Status: 200 (OK)<br>
Description: All quizzes are fetched.<br>

<b>Get a quiz</b><br>
HTTP  Method: GET<br>
Path: /api/quizzes/{id}<br>
Status: 200 (OK)<br>
Description: One quiz is fetched.<br>

<b>Post a quiz</b><br>
HTTP  Method: POST<br>
Path: /api/quizzes<br>
Status: 201 (Created)<br>
Description: A new quiz is created.<br>

<b>Delete a aquiz</b><br>
HTTP  Method: DELETE<br>
Path: /api/quizzes/{id}<br>
Status: 204 (No content)<br>
Description: The quiz is deleted.<br>

<b>Solvea a quiz</b><br>
HTTP  Method: POST<br>
Path: /api/quizzes/{id}/solve<br>
Status: 200 (OK)<br>
Description: The quiz is completed.<br>

<b>Get all completed quizzes</b><br>
HTTP  Method: GET<br>
Path: /api/quizzes/completed<br>
Status: 200 (OK)<br>
Description: All completions of quizzes for a specified user are fetched.<br>

#### Usefull links
* <a href="https://reqres.in/">Free API server Reqres to make our example requests</a>
* <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/index.html">Spring Framework Documentation</a>
* <a href="https://start.spring.io/">Spring Initializr</a>
* <a href="https://www.baeldung.com/jackson-ignore-properties-on-serialization">Jackson Ignore Properties on Marshalling</a>
* <a href="https://www.baeldung.com/exception-handling-for-rest-with-spring#responsestatusexception">Error Handling for REST with Spring</a>
* <a href="https://stackoverflow.com/questions/12505141/only-using-jsonignore-during-serialization-but-not-deserialization">Only using @JsonIgnore during serialization ...</a>
* <a href="https://www.baeldung.com/java-dao-pattern">The DAO Pattern in Java</a>
* <a href="https://www.javatpoint.com/restful-web-services-404-not-found">Implementing Exception Handling- 404 Resource Not Found</a>
* <a href="https://reflectoring.io/bean-validation-with-spring-boot/">All You Need To Know About Bean Validation With Spring Boot</a>
* <a href="https://www.baeldung.com/spring-boot-bean-validation">Validation in Spring Boot</a>
* <a href="https://hyperskill.org/learn/step/8780">Theory: Exception handling</a>
* <a href="https://attacomsian.com/blog/spring-data-jpa-h2-database">Spring Data JPA with H2 DataBase and Spring Boot</a>
* <a href="Spring Boot CRUD REST API + Spring Data JPA + H2 Database Example">https://www.javaguides.net/2019/08/spring-boot-crud-rest-api-spring-data-jpa-h2-database-example.html</a>
* <a href="https://spring.io/guides/gs/accessing-data-jpa/">Accessing Data with JPA</a>
* <a href="https://logicalsapien.com/spring-boot-rest-api-crud-with-spring-data-h2/">Spring Boot REST API CRUD with Spring Data H2</a>
* <a href="https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application">Entity To DTO Conversion for a Spring REST API</a>
* <a href="https://auth0.com/blog/automatically-mapping-dto-to-entity-on-spring-boot-apis/">Automatically Mapping DTO to Entity on Spring Boot APIs</a>
* <a href="https://www.springboottutorial.com/securing-rest-services-with-spring-boot-starter-security">Secure Rest Services and Web Applications with Spring Boot Security Starter</a>
* <a href="https://howtodoinjava.com/spring-boot2/security-rest-basic-auth-example/">Spring boot security rest basic authentication example</a>
* <a href="https://www.devglan.com/spring-security/spring-boot-security-rest-basic-authentication">Spring Boot Security + REST + Basic Authentication</a>
* <a href="https://www.baeldung.com/spring-security-authentication-with-a-database">Spring Security: Authentication with a Database-backed UserDetailsService</a>
* <a href="https://josdem.io/techtalk/spring/spring_boot_security_database/">Spring Boot Security using Database</a>
* <a href="http://progressivecoder.com/implementing-spring-boot-security-using-userdetailsservice/">Implementing Spring Boot Security using UserDetailsService</a>
* <a href="https://habr.com/ru/company/otus/blog/488418/">Аутентификация REST API с помощью Spring Security и MongoDB</a>
* <a href="https://www.baeldung.com/spring-data-jpa-query">Spring Data JPA @Query</a>
* <a href="https://howtodoinjava.com/spring-boot2/pagination-sorting-example/">Spring boot pagination and sorting example</a>
* <a href="https://stackoverflow.com/questions/32995179/convert-type-of-spring-data-jpa-page-content">Convert type of Spring Data JPA Page content	</a>
