#!/bin/sh
echo "start ekl-backend-ws with spring profiles $EKL_BACKEND_WS_PROFILES"
java -Dspring.profiles.active=$EKL_BACKEND_WS_PROFILES -XX:+AlwaysActAsServerClassMachine -XX:+UseParallelGC -Xmx1000M -XX:MaxDirectMemorySize=40M -XX:MaxMetaspaceSize=255M -jar /usr/local/bin/ekl-backend-ws.jar
