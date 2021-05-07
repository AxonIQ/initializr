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

Then, in a separate terminal, use cURL to access
the [initializr help](https://docs.spring.io/initializr/docs/current/reference/html/#configuration-access):

maven-java project:
```shell
curl http://localhost:8080/starter.zip -d dependencies=axon-starter,axon-test,web,data-jpa,h2 -o my-project.zip
```

maven-java project with reactor extension:
```shell
curl http://localhost:8080/starter.zip -d dependencies=axon-starter,axon-reactor-starter,axon-test,web,data-jpa,h2 -o my-project-with-reactor.zip
```

maven-java project with kotlin extension:
```shell
curl http://localhost:8080/starter.zip -d language=kotlin -d dependencies=axon-starter,axon-kotlin,axon-test,web,data-jpa,h2 -o my-project-with-kotlin.zip
```

## References and further reading

- [https://github.com/benwilcock/axon-initializr](https://github.com/benwilcock/axon-initializr)
- [https://docs.spring.io/initializr/docs/current/reference/html/](https://docs.spring.io/initializr/docs/current/reference/html/)
- [https://github.com/spring-io/start.spring.io](https://github.com/spring-io/start.spring.io)

---
Created with :heart: by [AxonIQ](https://axoniq.io/)

[maven]: https://maven.apache.org/ (Maven)

