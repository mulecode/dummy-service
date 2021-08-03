SHELL := /bin/bash

################################################################################
# Gradle
################################################################################

wrapper:
	gradle wrapper

dependencies:
	./gradlew dependencies

clean:
	./gradlew clean

compile: clean
	./gradlew bootJar -x test

test:
	./gradlew test

################################################################################
# Docker
################################################################################
VERSION := 1.0.0
DOCKER_REPO := dummy-service

docker-build:
	docker build --build-arg JAR_FILE=./build/libs/*.jar -t $(DOCKER_REPO):$(VERSION) . &&\
	docker tag $(DOCKER_REPO):$(VERSION) $(DOCKER_REPO):latest
