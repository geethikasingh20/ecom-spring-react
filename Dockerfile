# Use official OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven project files
COPY pom.xml mvnw* ./
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline -B

# Copy source and build the app
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Copy in React frontend build (you can skip if already in static/)
# COPY ../frontend/build ./src/main/resources/static

# Limit Java memory usage
ENV JAVA_OPTS="-Xms512m -Xmx512m"

# Launch the app
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar target/*.jar"]