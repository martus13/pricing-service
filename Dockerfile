# Dockerfile to build the Java project with Gradle

FROM openjdk:21-jdk-bullseye

WORKDIR /app

# Copy the necessary files for layer-based build
COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle .
COPY settings.gradle .
COPY src/ src/
COPY .gitattributes .gitattributes
COPY .gitignore .gitignore

# Grant execution permissions to the Gradle wrapper
RUN chmod +x gradlew

# Download dependencies and build the project
RUN ./gradlew build --no-daemon

CMD ["java", "-jar", "build/libs/pricing-service-1.0.0.jar"]
