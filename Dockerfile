FROM openjdk:17-jdk-alpine
MAINTAINER Amit-Gupta
WORKDIR /app
EXPOSE 8080
COPY target/Product-service.jar /app/Product-service.jar
ENTRYPOINT ["java","-jar","/app/Product-service.jar"]