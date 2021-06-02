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

## Steps to deploy to Dev
The code to build, test, and create a new image is found in this repo, while the code that executes the deploy to dev
is found in the [initializr-deploy](https://github.com/AxonIQ/initializr-deploy) repo.  
- On any merge to main branch, the action [Main-Compile-Test-Build-Deploy-Image](https://github.com/AxonIQ/initializr/actions/workflows/main-compile-test-build-deploy-image.yml) will execute the following steps
    - Calculate a hash for the action
    - Build the project and run all unit tests
    - Build a docker image and push to the DevOps project container registry, tagging the image with the hash
    - Check out the main branch from the [initializr-deploy](https://github.com/AxonIQ/initializr-deploy) repo
    - Update the file ```k8s/dev/kustomization.yaml``` with the tag of the image to deploy to the hash the latest image was tagged with
    - Push the changes to the main branch of the initializr-deploy repo.
    - This will trigger the execution of the '''Deploy to axoniq-initializr on DevOps Google Kubernetes Engine''' action found
      in the [initializr-deploy](https://github.com/AxonIQ/initializr-deploy) repo.


## Steps for deploy to Prod
The code to build, test, and create a new image is found in this repo, while the code that executes the deploy to prod is
is found in the [initializr-deploy](https://github.com/AxonIQ/initializr-deploy) repo.
- Prepare a release by manually executing the [CI: Perform a production release](https://github.com/AxonIQ/initializr/actions/workflows/release.yml) action.
    - This uses the maven release plugin which will tag the main branch with the Release TAG entered.
- Upon a tag being applied to the main branch, the action [Prepare production release](https://github.com/AxonIQ/initializr/actions/workflows/prepare-next-prod-release.yml) will run performing the following.
    - Build a docker image and push to the DevOps project container registry, tagging the image with the project version.
    - Check out the main branch from the [initializr-deploy](https://github.com/AxonIQ/initializr-deploy) repo.
    - Update the file ```k8s/prod/kustomization.yaml``` with the tag of the image to deploy to the project version
    - Push the changes to the main branch of the [initializr-deploy](https://github.com/AxonIQ/initializr-deploy) repo.
- To complete the deployment of this release to production, you must execute the [Deploy to axoniq-initializr-prod on DevOps Google Kubernetes Engine](https://github.com/AxonIQ/initializr-deploy/actions/workflows/k8s_prod_deployment.yml) action
found in the  [initializr-deploy](https://github.com/AxonIQ/initializr-deploy) repo

## References and further reading

- [https://github.com/benwilcock/axon-initializr](https://github.com/benwilcock/axon-initializr)
- [https://docs.spring.io/initializr/docs/current/reference/html/](https://docs.spring.io/initializr/docs/current/reference/html/)
- [https://github.com/spring-io/start.spring.io](https://github.com/spring-io/start.spring.io)

---
Created with :heart: by [AxonIQ](https://axoniq.io/)

[maven]: https://maven.apache.org/ (Maven)

