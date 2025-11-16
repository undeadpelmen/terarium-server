# Terarium Back-end Server

## Installation

``` sh
git clone https://github.com/undeadpelmen/terarium-server.git

cd terarium-server
```

### Before you run the server, you need to configure the database and message broker

You can start the database and message broker locally,
 with docker or use [ready docker-compose repository](https://github.com/undeadpelmen/terarum)

### Setup environment variables

``` sh
echo "
export SERVER_PORT=65535
export SPRING_DATASOURCE_USERNAME=postgres
export SPRING_DATASOURCE_PASSWORD=password
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/server
export SPRING_RABBITMQ_HOST=localhost
export SPRING_RABBITMQ_PORT=5672
export SPRING_RABBITMQ_USERNAME=guest
export SPRING_RABBITMQ_PASSWORD=guest" >> ~/.bashrc
```

### Run the server

``` sh
./gradlew build

java -jar ./build/libs/server-0.0.1.jar
```

## API

Swagger UI: [http://localhost:65535/swagger-ui/index.html](http://localhost:65535/swagger-ui/index.html)

OpenAPI Json: [http://localhost:65535/v3/api-docs](http://localhost:65535/v3/api-docs)
