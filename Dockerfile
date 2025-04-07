FROM openjdk:21-jdk-slim
COPY build/libs/*.jar video-streaming-app.jar
EXPOSE 8089
ENTRYPOINT ["java","-jar","video-streaming-app.jar"]
