FROM openjdk:25

COPY ./build/libs/server-0.0.1.jar ./app.jar

EXPOSE 8080

CMD [ "java", "-jar", "./app.jar" ]