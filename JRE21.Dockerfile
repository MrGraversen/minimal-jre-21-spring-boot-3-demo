FROM eclipse-temurin:21-jre-alpine
MAINTAINER MrGraversen

# Set the JAVA_HOME environment variable to the default JRE path
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="$PATH:$JAVA_HOME/bin"

# Create an application directory and add a non-root user 'app'
RUN mkdir /app && \
    addgroup --system app && \
    adduser -S -s /bin/false -G app app

# Copy the application JAR file
ARG JAR_FILE
COPY --chown=app:app ${JAR_FILE} /app/app.jar
VOLUME /tmp

# Set the working directory
WORKDIR /app

# Use non-root user
USER app

# Start the application using the default JRE 21
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "/app/app.jar"]
