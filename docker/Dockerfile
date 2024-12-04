FROM eclipse-temurin:21-jre-jammy
VOLUME /tmp
COPY application/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"] 