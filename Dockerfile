# Stage 1: Build (The "Kitchen")
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app

# Copy the maven wrapper and pom first to cache dependencies
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

# Copy source and build
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Stage 2: Run (The "Dining Room")
FROM eclipse-temurin:25-jre
WORKDIR /app

# We only copy the final jar from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]