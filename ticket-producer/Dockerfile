FROM amazoncorretto:21-alpine
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]

# docker build -t ticket-producer -f Dockerfile .
# docker run -d -p 8080:8080 --name ticket-producer-container ticket-producer