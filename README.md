# social-web-app-example
Example web app for Java developers who are learning Scala. 

## Features

1. Written in TDD with E2E test support
2. Scala
3. Configured to for Heroku
4. Java tecnologies: Spring Boot + Jetty + Maven
5. OAuth2 integration (working on this)

## Run locally
```$bash
$ mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=${port} --server.url=http://localhost --google.client.id=${google_client_id} --google.client.secret=${google_client_secret}" 
```
