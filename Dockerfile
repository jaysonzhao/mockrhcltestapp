FROM registry.access.redhat.com/ubi9/openjdk-17:latest as builder

# Switch to root to handle permission issues
USER root

# Set working directory
WORKDIR /app

# Copy Maven configuration files
COPY pom.xml .

# Copy source code
COPY src ./src

# Ensure resources directory exists
RUN mkdir -p src/main/resources

# Build the application
RUN mvn clean package -DskipTests

# Second stage: runtime
FROM registry.access.redhat.com/ubi9/openjdk-17-runtime:latest

# Switch to root for setup
USER root

# Set working directory
WORKDIR /app

# Copy the built artifact from the builder stage
COPY --from=builder /app/target/test-pages-app-0.0.1-SNAPSHOT.jar ./app.jar

# Make sure the jar is readable by non-root users
RUN chmod 644 ./app.jar

# Switch back to non-root user for running the application
USER 1001

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]