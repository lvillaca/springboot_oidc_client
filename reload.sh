#!/bin/bash
docker stop oidc-bootclient
docker rm oidc-bootclient
docker rmi -f luis/oidc-bootclient:1.0

# VARIABLES TO BE INJECTED BY JENKINS/CICD orchestrator
export group=luis
export name=oidc-bootclient
export calculatedVersion=1.0

./gradlew build buildDocker
docker run --name "${name}" -d -p 8994:8999  -e "TZ=America/Sao_Paulo" "${group}/${name}:${calculatedVersion}"
docker logs -f $name 

