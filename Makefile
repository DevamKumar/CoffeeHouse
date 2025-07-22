IMAGE_NAME=devamkumar/jenkins-agent-coffee
TAG=latest

.PHONY: build push rebuild

build:
	docker build -t $(IMAGE_NAME):$(TAG) -f Dockerfile.jenkins-agent .

push:
	docker push $(IMAGE_NAME):$(TAG)

rebuild: build push
