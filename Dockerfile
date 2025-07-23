FROM openjdk:21-jdk-slim

#just checking

LABEL authors="devamkumar"
LABEL projectName="CoffeeHouse"

WORKDIR /app

COPY build/libs/*.jar app.jar

EXPOSE 8054

ENTRYPOINT ["java", "-jar", "app.jar"]