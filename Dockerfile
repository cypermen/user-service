FROM openjdk:17
ARG JAR_FILE=./build/libs/user-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} user-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","user-service-0.0.1-SNAPSHOT.jar"]
EXPOSE 8081
