# Dockerfile to build the Java project with Gradle

FROM openjdk:21-jdk-bullseye

WORKDIR /app

# Copy Gradle wrapper and configuration first for caching
COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle .
COPY settings.gradle .

# Copy source code and Git config files
COPY src/ src/
COPY .gitattributes .gitattributes
COPY .gitignore .gitignore

# Grant execution permissions to the Gradle wrapper
RUN chmod +x gradlew

# Download dependencies and build the project
RUN ./gradlew build --no-daemon

# Expose the port your application listens on
EXPOSE 8080

# Run the built jar by default
CMD ["java", "-jar", "build/libs/pricing-service-1.0.0.jar"]
