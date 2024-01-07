# Use an official Java runtime as a parent image
FROM openjdk:17-jdk

# Set the working directory in the container
#WORKDIR /app

# Copy the .jar file into the container at /app
COPY target/*.jar bughub.jar

#./target/bughub-0.0.1-SNAPSHOT.jar /app/bughub.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "bughub.jar"]
