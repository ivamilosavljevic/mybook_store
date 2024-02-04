FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /lab
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src src
RUN mvn package -DskipTests

FROM openjdk:17
WORKDIR /lab
COPY --from=build /lab/target/*.jar lab.jar
EXPOSE 8080
CMD ["java", "-jar", "lab.jar"]