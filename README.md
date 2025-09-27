# SpringBootTemplate

Template for spring boot web application.

## Description

Using latest versions (hopefully) in a modular way with:
* APIs
* embedded DB
* JPA
* security (to be extended with oauth2 ...)
* async
* websockets
* actuators
* Open API
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


## Modules


### Main module

* Get all the dependent modules and runs an application


### Security module

* Manage the first entry points of the application
* Any request will be redirected to the login page, http://localhost:8080/login, so a user can authenticate (basic)
* Main home for http://localhost:8080/ will be redirected automatically to http://localhost:8080/?continue which corresponds to http://localhost:8080/home

![Screenshot of the chat.](/security/src/main/resources/content/login.jpg)


### Admin module

* Managed in the admin related APIs
* Displays beans instantiated by Spring in the application: http://localhost:8080/beans
* Displays APIs via Swagger: * http://localhost:8080/swagger-ui/index.html
* Displays actuators:
* * http://localhost:8080/actuator
* * http://localhost:8080/actuator/health
* * http://localhost:8080/actuator/env
* * http://localhost:8080/actuator/configprops


### JPA module

* Expose the trade and counterparty APIs
* Using h2 embedded db and Spring data JPA
* Controllers for get, post and getAll
* http://localhost:8080/trades
* http://localhost:8080/trade/{id}
* http://localhost:8080/trade (POST)
* http://localhost:8080/counterparties
* http://localhost:8080/counterparty/{id}
* http://localhost:8080/counterparty (POST)


### Paged JPA module

* Expose the book API
* Using h2 embedded db and Spring data JPA
* Controllers for getAll and getAll by page
* http://localhost:8080/books
* http://localhost:8080/books/{page}/{size}


### Async module

* Expose the slow API
* Asynchronous service skeleton simulating a slow response
* http://localhost:8080/slow


### Websockets module

* Expose the chat API
* Override the security to open the chat for websockets connection and subscription, while securing the messages 
with a channel interceptor
* Asynchronous chat service using websockets
* http://localhost:8080/chat.html

![Screenshot of the chat.](/websockets/src/main/resources/content/Chat.jpg)



## Authors

[Benoit Antelme](https://github.com/benoitantelme)


## Version History

* 1.0
    * Initial Release

## License

This project is licensed under the MIT License - see the LICENSE.md file for details