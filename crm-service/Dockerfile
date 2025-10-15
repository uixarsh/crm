# Use a lightweight Java runtime image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run Spring Boot from compiled classes
ENTRYPOINT ["java", "-cp", "/app/classes:/app/dependency/*", "org.crm.CrmServiceApplication"]
