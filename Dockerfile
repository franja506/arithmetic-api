FROM amazoncorretto:20

ARG appName
ARG version

WORKDIR /app
COPY target/$appName-$version.jar /app/app.jar

EXPOSE 8080
ENV JAVA_OPTS "-XX:+UseContainerSupport"
CMD ["java", "-jar", "app.jar"]
