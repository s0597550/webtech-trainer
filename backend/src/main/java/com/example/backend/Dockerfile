FROM eclipse-temurin:25-jdk-alpine

WORKDIR /app

COPY . .

RUN ./gradlew clean build -x test

EXPOSE 8080

CMD ["java", "-jar", "build/libs/backend-0.0.1-SNAPSHOT.jar"]