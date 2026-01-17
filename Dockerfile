FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY . .
RUN mvn clean package -DskipTests

COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

ENTRYPOINT ["java", "-jar", "app.jar"]
