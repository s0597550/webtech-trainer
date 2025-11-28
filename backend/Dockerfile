FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# kompletten Backend-Code ins Image kopieren
COPY . .

# Gradle-Build ohne Tests
RUN chmod +x ./gradlew && ./gradlew clean build -x test

EXPOSE 8080

# starte das erzeugte Spring-Boot-Jar (egal wie es genau heißt)
CMD ["sh", "-c", "java -jar build/libs/*.jar"]
