FROM maven:3.8.3-openjdk-17 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean package

FROM openjdk:21

COPY --from=build /app/target/sherlock-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 8080
EXPOSE 3306

CMD ["java", "-jar", "app.jar"]