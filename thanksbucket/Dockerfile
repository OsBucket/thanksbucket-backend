FROM openjdk:17
ARG JAR_PATH=build/libs
WORKDIR /app
COPY ${JAR_PATH}/thanksbucket-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
