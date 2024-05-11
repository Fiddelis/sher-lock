FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=admin
ENV MYSQL_DATABASE=sherlock

COPY ./sherlock_bd.sql /docker-entrypoint-initdb.d/script.sql

EXPOSE 3306

FROM maven:3.8.3-openjdk-17 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn install

FROM openjdk:21

COPY --from=build /app/target/sherlock-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]