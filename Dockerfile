#FROM maven:3.9-eclipse-temurin-22-alpine AS build
#WORKDIR /app
#COPY pom.xml .
#RUN mvn dependency:go-offline
#COPY src/ ./src/
#RUN mvn clean package -DskipTests=true

FROM openjdk:22-slim
COPY ./target/*.jar app.jar
EXPOSE 9992
CMD ["java", "-jar", "app.jar"]