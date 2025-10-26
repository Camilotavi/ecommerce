# Etapa 1: Construcción
FROM gradle:8.5-jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle bootJar --no-daemon

# Etapa 2: Ejecución
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

# Variables de entorno (opcional)
ENV DB_URL=""
ENV DB_USER=""
ENV DB_PASS=""

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]