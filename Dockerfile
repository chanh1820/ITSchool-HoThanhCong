# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

WORKDIR /app

VOLUME /tmp

ADD itschool-0.0.1-SNAPSHOT.war itschool-0.0.1-SNAPSHOT.war

ENTRYPOINT ["java", "-jar", "itschool-0.0.1-SNAPSHOT.war"]
