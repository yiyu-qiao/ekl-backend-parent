# base image for ekl-backend-ws
FROM alpine:3.19 AS ekl-alpine-jre
RUN apk add openjdk21-jre=21.0.3_p9-r0

FROM ponyworld/ekl-alpine-jre-21:21.0.3 AS ekl-backend-ws
ARG ARTIFACT_EKL_BE_WS_NAME
COPY target/$ARTIFACT_EKL_BE_WS_NAME /usr/local/bin/
ENV ARTIFACT_EKL_BACKEND=$ARTIFACT_EKL_BE_WS_NAME
ENTRYPOINT ["java -jar /usr/local/bin/$ARTIFACT_EKL_BACKEND"]
