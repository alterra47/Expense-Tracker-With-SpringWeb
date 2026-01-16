# 1. Start with a base Linux image that has Java 17 pre-installed
FROM eclipse-temurin:25-jdk

# 2. Create a folder inside the container
WORKDIR /app

# 3. Copy the JAR we just built from your Host machine -> into the Container
COPY target/expenseTracker-0.0.1-SNAPSHOT.jar app.jar

# 4. The command to run when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
