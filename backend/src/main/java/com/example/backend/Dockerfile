FROM eclipse-temurin:25-jdk-alpine

WORKDIR /app

# Backend-Code ins Image kopieren
COPY . .

# Gradle Wrapper ausführbar machen + Build (Tests werden übersprungen)
RUN chmod +x ./gradlew && ./gradlew clean build -x test

EXPOSE 8080

# Spring Boot Jar starten (egal wie es heißt)
CMD ["sh", "-c", "java -jar build/libs/*.jar"]

//