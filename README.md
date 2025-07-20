# SpringBootTemplate

Template for spring boot web application.

## Description

Using latest versions (hopefully) in a modular way with:
* APIs
* embedded DB
* JPA
* security (to be extended with oauth2 ...)
* actuators
* Open API
* async
* spotless


## Getting Started

### Dependencies

* spring-boot-starter-parent|web|actuator|security|data-jpa|test
* thymeleaf-extras-springsecurity6
* h2
* junit

### Installing

* build using maven, will run tests
* ```mvn clean install```


### Executing

* Run com.template.Application in the main-module
* ```java com.template.Application```

* Deploy as jar and run
* ```java -cp YourJar.jar com.template.Application```


### Maven versions plugin

* Allows to check if dependencies are up to date
* ```mvn versions:display-dependency-updates```


### Maven spotless plugin

* Allows to format all project files
* ```mvn spotless::apply```


## APIs


### Main entry points

* Managed in the security module
* Any request will be redirected to the login page, http://localhost:8080/login, so a user can authenticate (basic)
* Main home for http://localhost:8080/ will be redirected automatically to http://localhost:8080/?continue which corresponds to http://localhost:8080/home

### Admin entry points

* Managed in the admin module
* Displays beans instantiated by Spring in the application: http://localhost:8080/beans
* Displays APIs via Swagger: * http://localhost:8080/swagger-ui/index.html


* Displays actuators:
* * http://localhost:8080/actuator
* * http://localhost:8080/actuator/health
* * http://localhost:8080/actuator/env
* * http://localhost:8080/actuator/configprops


### Trade and Book services

* Managed in the main module
* Using h2 embedded db and Spring data JPA
* Controllers for get, post and getAll
* http://localhost:8080/trades
* http://localhost:8080/trade/{id}
* http://localhost:8080/trade (POST)
* http://localhost:8080/counterparties
* http://localhost:8080/counterparty/{id}
* http://localhost:8080/counterparty (POST)


### Slow service

* Managed in the async module
* Asynchronous service skeleton simulating a slow response
* http://localhost:8080/slow


## Authors

[Benoit Antelme](https://github.com/benoitantelme)


## Version History

* 0.1
    * Initial(ly not) Release(d)

## License

This project is licensed under the MIT License - see the LICENSE.md file for details