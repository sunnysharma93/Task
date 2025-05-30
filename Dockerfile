# Start from official JDK base image
FROM openjdk:8-jdk-alpine

# Set working directory inside the container
WORKDIR /app

# Copy the built jar file into the container
COPY target/webapp-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on
EXPOSE 3001

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
