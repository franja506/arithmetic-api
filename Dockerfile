FROM amazoncorretto:20

ARG appName
ARG version

WORKDIR /app
COPY ./build/libs/$appName-$version.jar /app/app.jar
EXPOSE 8080
ENV JAVA_OPTS "-XX:+UseContainerSupport"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS  -jar /app/app.jar"]
