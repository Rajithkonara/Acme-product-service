## Acme Product Service

Acme product service.

## Requirements
* Open JDK 1.8
* Postgresql 12.5
* Maven 3.6 (only to run maven commands)

## Dependencies
All dependencies are available in pom.xml.

## Configuration
- Configure the relevant configurations in application.yml in
src/main/resources before building the application. 
- Create a database as given in your configuration.

## Run Unit Test
```
mvn test
```

## Build
```
mvn clean clen install
```

## Run
```
java -jar target/product-service-1.0.0-SNAPSHOT.jar
```
- Insert the sample data available in src/main/resources/script.sql to the product table
- Use the provided postman collection in src/main/resources/Acme.postman_collection.json to try out the API's
### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/maven-plugin/)


## License

Copyright (c) Acme -
This source code is licensed under the  license. 
