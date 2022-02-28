FROM gradle:7.4-jdk11-alpine as builder
USER root
WORKDIR /builder
ADD . /builder
RUN gradle build --stacktrace

FROM openjdk:11-jre-alpine
WORKDIR /app
EXPOSE 9090
COPY --from=builder /builder/build/libs/server.jar .
CMD ["java", "-jar", "server.jar"]