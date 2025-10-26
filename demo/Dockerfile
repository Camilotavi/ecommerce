# Etapa 1: Construcción
FROM gradle:8.5-jdk21 AS builder
WORKDIR /app
COPY demo/ .
RUN gradle bootJar --no-daemon

# Etapa 2: Ejecución
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

# Variables de entorno (opcional)
ENV DB_URL=""
ENV DB_USER=""
ENV DB_PASS=""

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]