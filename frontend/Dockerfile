# Build Env
FROM maven:3.6-jdk-8-slim AS build
WORKDIR /app
COPY pom.xml ./
RUN mvn verify --fail-never
COPY src src
RUN mvn -Dmaven.test.skip=true package


FROM openjdk:8-jdk-alpine
ENV PORT 8080
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]