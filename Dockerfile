FROM openjdk:11-jdk-slim

LABEL org.opencontainers.image.source="https://github.com/mulecode/dummy-service"

ARG JAR_FILE

WORKDIR /
ADD ${JAR_FILE} /app.jar

ENTRYPOINT [ "java", "-server", "-jar", "app.jar"]
