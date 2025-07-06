# fintestq

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/fintestq-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- LangChain4j Ollama ([guide](https://docs.quarkiverse.io/quarkus-langchain4j/dev/index.html)): Provides the basic integration of Ollama with LangChain4j
- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Document your REST APIs with OpenAPI - comes with Swagger UI


## Project specific set-up

The project implements kafka services. Until I push the kubernetes scripts to automatically deploy the full environment you can trace the following steps:

- Create docker network:
```shell script
docker network create kafka-net
```
- Pull kafka image:
```shell script
  docker run -d --name kafka --network kafka-net -e KAFKA_CFG_NODE_ID=1 -e KAFKA_CFG_PROCESS_ROLES=broker,controller -e KAFKA_CFG_CONTROLLER_LISTENER_NAMES=CONTROLLER -e KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=INTERNAL:PLAINTEXT,CONTROLLER:PLAINTEXT -e KAFKA_CFG_LISTENERS=INTERNAL://0.0.0.0:9092,CONTROLLER://0.0.0.0:9093 -e KAFKA_CFG_ADVERTISED_LISTENERS=INTERNAL://kafka:9092 -e KAFKA_CFG_INTER_BROKER_LISTENER_NAME=INTERNAL -e KAFKA_CFG_CONTROLLER_QUORUM_VOTERS=1@kafka:9093 -e BITNAMI_DEBUG=yes -v kafka_data:/bitnami/kafka bitnami/kafka:3.4
```
- Build your image (currently I maintain the .jvm option)
```shell script
docker build -f Dockerfile.jvm -t fintestq-app .
```
- Run your image:
```shell script
docker run -d -p 8080:8080 --network kafka-net --name fintestq-app fintestq-app
```
- Optional, deploy Kafka UI:
```shell script
docker run -d --name kafka-ui --network kafka-net -p 8081:8080 -e KAFKA_CLUSTERS_0_NAME=local -e KAFKA_CLUSTERS_0_BOOTSTRAPSERVERS=kafka:9092 -e KAFKA_CLUSTERS_0_METRICS_PORT=9997 provectuslabs/kafka-ui:latest
```