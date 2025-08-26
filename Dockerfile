FROM gradle:8-jdk21 AS builder
WORKDIR /app
COPY . .

RUN chmod +x gradlew
RUN ./gradlew bootJar


FROM amazoncorretto:21-alpine
WORKDIR /app

RUN apk --no-cache add curl
COPY --from=builder /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
