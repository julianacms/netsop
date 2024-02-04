FROM openjdk:23-slim-bullseye
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]