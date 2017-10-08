#!/bin/bash

mvn clean install -DskipTests=true

java -jar nmm-server/target/nmm-server-1.0-SNAPSHOT.jar