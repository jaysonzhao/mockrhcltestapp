FROM registry.access.redhat.com/ubi9/openjdk-17:latest as builder

# Set working directory
WORKDIR /app

# Copy Maven configuration files
COPY pom.xml .

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Second stage: runtime
FROM registry.access.redhat.com/ubi9/openjdk-17-runtime:latest

# Set working directory
WORKDIR /app

# Copy the built artifact from the builder stage
COPY --from=builder /app/target/test-pages-app-0.0.1-SNAPSHOT.jar ./app.jar

# Set the user to run the application (security best practice)
USER 1001

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]