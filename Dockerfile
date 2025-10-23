# ---- Stage 1: Build ----
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy the pom.xml and download dependencies first (for Docker caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source and build the app
COPY src ./src
RUN mvn clean package -DskipTests

# ---- Stage 2: Runtime ----
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy only the built jar (exclude tests, etc.)
COPY --from=builder /app/target/*.jar app.jar

# Set entrypoint for your main class
ENTRYPOINT ["java", "-jar", "app.jar"]
