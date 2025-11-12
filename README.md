# Rabbit Back-end Server

## Installation

``` sh
git clone https://github.com/undeadpelmen/terarium-server.git

cd terarium-server
```

### What you need to do before you start

1. Install docker

2. Setup the PostgreSQL

``` sh
docker pull postgres:16

docker run -d --name postgres -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=terarium postgres:16
```

3. Install RabbitMQ

```sh
docker pull rabbitmq:4.0-management

docker run -d --name rabbitmq -p 15672:15672 -p 5672:5672 rabbitmq:4.0-management
```

4. Setup env variables

```sh
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

### Now you can run the server

``` sh
./gradlew build

java -jar ./build/libs/server-0.0.1.jar
```

## API

Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

OpenAPI Json: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
