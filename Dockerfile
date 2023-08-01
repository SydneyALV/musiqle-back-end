# Use an appropriate base image for Java applications
FROM openjdk:11-jdk

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/Musiqle-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application is listening on
EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "app.jar"]
