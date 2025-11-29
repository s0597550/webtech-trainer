FROM eclipse-temurin:25-jdk-alpine

WORKDIR /app

# kompletten Backend-Code ins Image kopieren
COPY . .

# Gradle-Build ohne Tests
RUN chmod +x ./gradlew && ./gradlew clean build -x test

EXPOSE 8080

# starte das erzeugte Spring-Boot-Jar (egal wie es genau heißt)
CMD ["java", "-jar", "build/libs/backend-0.0.1-SNAPSHOT.jar"]

