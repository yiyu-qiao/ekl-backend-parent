# base image for ekl-backend-ws
FROM alpine:3.19 AS ekl-alpine-jre
RUN apk add openjdk21-jre=21.0.3_p9-r0

FROM ponyworld/ekl-alpine-jre-21:21.0.3 AS ekl-backend-ws
ARG ARTIFACT_EKL_BE_WS_NAME
COPY target/$ARTIFACT_EKL_BE_WS_NAME /usr/local/bin/ekl-backend-ws.jar
RUN chmod +x /usr/local/bin/ekl-backend-ws.jar
ENV ARTIFACT_EKL_BACKEND_WS=$ARTIFACT_EKL_BE_WS_NAME
COPY start_ekl_backend_ws.sh /usr/local/bin/
RUN chmod +x /usr/local/bin/start_ekl_backend_ws.sh
ENTRYPOINT ["/usr/local/bin/start_ekl_backend_ws.sh"]
