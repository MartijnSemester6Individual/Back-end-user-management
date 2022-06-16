FROM openjdk:16-alpine3.13

WORKDIR /app

COPY /.env ./
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run", "-Dspring.profiles.active=default,dev"]

EXPOSE 8084/tcp