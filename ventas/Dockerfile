FROM openjdk:11-jdk-slim

WORKDIR /app

COPY target/mi-aplicacion.jar /app/main.jar

EXPOSE 8082

ENV SPRING_DATA_MONGODB_URI=mongodb://mongo:27017/complemento

ENTRYPOINT ["java", "-jar", "/app/main.jar"]