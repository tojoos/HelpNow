FROM maven:3.9.5-eclipse-temurin-17 AS jar-builder

COPY checkstyle.xml .
COPY pom.xml .
COPY src src

RUN mvn dependency:go-offline
RUN mvn package -DskipTests


# Final container base image
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the JAR file from the builder stage into final container
COPY --from=jar-builder /target/HelpNow-1.0.0.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]