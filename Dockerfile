FROM gradle:jdk25-alpine AS builder

WORKDIR /app

COPY . .

RUN ./gradlew build


FROM openjdk:26-ea-trixie

WORKDIR /app

COPY --from=builder /app/build/libs/server-0.0.1.jar app.jar

CMD ["java", "-jar", "app.jar"]