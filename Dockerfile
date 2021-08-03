FROM openjdk:11-jdk-slim

ARG JAR_FILE

WORKDIR /
ADD ${JAR_FILE} /app.jar

ENTRYPOINT [ "java", "-server", "-jar", "app.jar"]
