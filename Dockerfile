FROM maven:3.9-eclipse-temurin-23-alpine AS build

WORKDIR /usr/app
COPY src ./src
COPY pom.xml .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /usr/app
COPY --from=build /usr/app/target/*.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]