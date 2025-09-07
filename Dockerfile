FROM bellsoft/liberica-openjdk-alpine:21.0.7

# install utilities curl and jq
RUN apk add curl jq

#Workspace
WORKDIR /home/selenium-docker

#Add required files
ADD target/docker-resources   ./
ADD runner.sh     runner.sh

#fix for windows
RUN dos2unix runner.sh

# Run the command to start the runner.sh script
ENTRYPOINT sh runner.sh
