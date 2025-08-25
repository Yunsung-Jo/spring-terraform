FROM gradle:8-jdk21 AS builder
WORKDIR /app
COPY . .

RUN chmod +x gradlew
RUN ./gradlew bootJar


FROM openjdk:21-slim
WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]
