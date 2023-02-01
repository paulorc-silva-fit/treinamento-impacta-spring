FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/projeto-final-spring-impacta.jar app-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app.jar"]