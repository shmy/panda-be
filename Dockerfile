FROM openjdk:8u212-jdk-alpine3.9
ARG JAR_FILE=build/libs/panda-be-0.0.1-SNAPSHOT.jar
VOLUME /tmp
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]