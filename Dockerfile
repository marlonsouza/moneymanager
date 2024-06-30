FROM maven:3.9.7-sapmachine-22 AS build-stage
COPY . /build
WORKDIR /build
RUN mvn clean package -DskipTests=true

FROM openjdk:22-jdk AS runtime
WORKDIR .
COPY --from=build-stage /build/target/moneymanager*.jar moneymanager.jar
ARG PROPERTIES_FILE=/src/main/resources/application-docker.yml
COPY ${PROPERTIES_FILE} application.yml

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/moneymanager.jar"]