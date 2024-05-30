FROM openjdk:17-jdk-alpine
MAINTAINER phunguyen
VOLUME /tmp
COPY target/*.jar Daily-Reflect-Backend-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Daily-Reflect-Backend-0.0.1-SNAPSHOT.jar"]