# Build the application
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Create the runtime image
FROM eclipse-temurin-17-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENV JAVA_OPTS="-Xms512m -Xmx1024m"
ENTRYPOINT ["java", "-jar", "app.jar"]