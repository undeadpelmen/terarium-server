FROM gradle:jdk24-alpine AS builder

WORKDIR /app

COPY . .

RUN chmod +x gradlew && ./gradlew build


FROM openjdk:25

WORKDIR /app

COPY --from=builder /app/build/libs/server-0.0.1.jar app.jar

CMD ["java", "-jar", "app.jar"]