# Axon Intializr

Everything starts here ;)



## Development

This project is driven using [maven].

### Build and Test?

```shell
$ ./mvnw clean verify -DskipTests
```
or with tests (`it can take some time, please be patient`):

```shell
$ ./mvnw clean verify
```

### Run locally

You can run the following command to start your project locally:

```shell
$ ./mvnw spring-boot:run
```

### CURL the generator

In a separate terminal, use cURL to access the generator

maven-java project:
```shell
curl http://localhost:8080/starter.zip -d dependencies=web,data-jpa,h2 -o my-project.zip
```

maven-java project with reactor extension:
```shell
curl http://localhost:8080/starter.zip -d dependencies=axon-reactor-starter,web,data-jpa,h2 -o my-project-with-reactor.zip
```

maven-java project with kotlin:
```shell
curl http://localhost:8080/starter.zip -d language=kotlin -d dependencies=web,data-jpa,h2 -o my-project-with-kotlin.zip
```

## References and further reading

- [https://github.com/benwilcock/axon-initializr](https://github.com/benwilcock/axon-initializr)
- [https://docs.spring.io/initializr/docs/current/reference/html/](https://docs.spring.io/initializr/docs/current/reference/html/)
- [https://github.com/spring-io/start.spring.io](https://github.com/spring-io/start.spring.io)

---
Created with :heart: by [AxonIQ](https://axoniq.io/)

[maven]: https://maven.apache.org/ (Maven)

