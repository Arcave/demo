# Build using gradle
FROM gradle:jdk11 as build

COPY --chown=gradle:gradle . /home/gradle/project

USER gradle

WORKDIR /home/gradle/project

RUN gradle clean build

# Copy fat jar into distroless container
FROM gcr.io/distroless/java:11

COPY --from=build /home/gradle/project/build/libs/demo-0.0.1-SNAPSHOT.jar ./app.jar

CMD ["./app.jar"]
