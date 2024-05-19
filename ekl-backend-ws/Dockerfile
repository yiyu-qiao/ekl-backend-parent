# base image for ekl-backend-ws
FROM alpine:3.19 AS ekl-alpine-jre
RUN apk add openjdk21-jre=21.0.3_p9-r0

FROM ponyworld/ekl-alpine-jre-21:21.0.3 AS ekl-backend-ws
ARG ARTIFACT_EKL_BE_WS_VERSION
COPY target/$ARTIFACT_EKL_BE_WS_VERSION.jar /usr/local/bin/
RUN cd /usr/local/bin
RUN mv $ARTIFACT_EKL_BE_WS_VERSION.jar ekl-backend-ws.jar
ENV ARTIFACT_EKL_BACKEND_WS_VERSION=$ARTIFACT_EKL_BE_WS_VERSION
ENTRYPOINT ["java -jar /usr/local/bin/ekl-backend-ws.jar"]
