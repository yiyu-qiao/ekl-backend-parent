#!/bin/sh
echo "start ekl-backend-ws with spring profiles $EKL_BACKEND_WS_PROFILES"
java -Dspring.profiles.active=$EKL_BACKEND_WS_PROFILES -jar /usr/local/bin/ekl-backend-ws.jar