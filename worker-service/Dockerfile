FROM maven:3.8.7-eclipse-temurin-17-alpine AS builder
WORKDIR /app
COPY . .
RUN mvn package -DskipTests

FROM eclipse-temurin:17.0.5_8-jre-alpine as final
WORKDIR /app
COPY --from=builder /app/target/worker-service-1.0-SNAPSHOT.jar .
ENV PORT=8000
EXPOSE 8000
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar ./worker-service-1.0-SNAPSHOT.jar