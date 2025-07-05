# SpringBootTemplate

Template for spring boot web application.

## Description

Using latest versions (hopefully), with APIs, security (to be extended with oauth2 ...), actuators, async one day.

## Getting Started

### Dependencies

* spring-boot-starter-parent
* spring-boot-starter-web
* spring-boot-starter-actuator
* spring-boot-starter-security
* thymeleaf-extras-springsecurity6
* spring-boot-starter-test
* spring-security-test
* junit

### Installing

* build using maven, will run tests
```
mvn clean install
```


### Executing program

* Run com.template.Application
```
java com.template.Application
```

* Deploy as jar and run
```
java -cp YourJar.jar com.template.Application
```

### Actuators

* http://localhost:8080/actuator
* http://localhost:8080/actuator/health
* http://localhost:8080/actuator/env
* http://localhost:8080/actuator/configprops


### Trade service

* Using h2 embedded db as trades repository
* Scripts in resources/db/sql
* name varchar(50) | counterparty varchar(50) | amount decimal | currency varchar(3)
* Controller for get and getAll


### Swagger Open API

* http://localhost:8080/swagger-ui/index.html



## Authors

Me


[Benoit Antelme](https://github.com/benoitantelme)

## Version History

* 0.1
    * Initial Release

## License

This project is licensed under the [NAME HERE] License - see the LICENSE.md file for details