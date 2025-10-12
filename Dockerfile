FROM  openjdk:21-jdk
WORKDIR /app
COPY  target/*.jar /app/spring-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/spring-app.jar"]