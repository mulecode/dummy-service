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

publish:
	./gradlew publish

################################################################################
# Docker
################################################################################
CURRENT_VERSION := `sed 1q version.txt`
DOCKER_REPO := ghcr.io/mulecode/dummy-service

docker-login:
	echo $(GITHUB_TOKEN) | docker login ghcr.io -u mulecode --password-stdin

docker-build:
	docker build --build-arg JAR_FILE=./build/libs/*.jar -t $(DOCKER_REPO):$(CURRENT_VERSION) . &&\
	docker tag $(DOCKER_REPO):$(CURRENT_VERSION) $(DOCKER_REPO):latest

docker-push:
	docker push $(DOCKER_REPO):latest &&\
	docker push $(DOCKER_REPO):$(CURRENT_VERSION)

.ONESHELL:
read-version:
	echo ">>$(CURRENT_VERSION)<<"
