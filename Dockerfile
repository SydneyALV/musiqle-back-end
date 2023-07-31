FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar musiqle-back-end.jar
ENTRYPOINT ["java","-jar","/musiqle-back-end.jar"]
EXPOSE 8080