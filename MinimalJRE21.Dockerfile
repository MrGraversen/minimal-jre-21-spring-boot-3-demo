FROM eclipse-temurin:21-jdk-alpine as JRE_BUILDER
MAINTAINER MrGraversen

# Install OpenJDK 21 JDK and jmods
RUN apk --no-cache add openjdk21-jdk openjdk21-jmods

ENV MINIMAL_JAVA="/opt/minimal-java"

# Build minimal JRE using jlink
# This JRE is suitable to run most Spring Boot based applications while minimising memory footprint and startup time
RUN /usr/lib/jvm/java-21-openjdk/bin/jlink \
    --verbose \
    --add-modules java.base,jdk.unsupported,jdk.management,jdk.crypto.ec,java.net.http,java.sql,java.naming,java.desktop,java.management,java.security.jgss,java.instrument \
    --compress 2 --strip-debug --no-header-files --no-man-pages \
    --release-info="add:IMPLEMENTOR=MrGraversen:IMPLEMENTOR_VERSION=minimal_jre" \
    --output "$MINIMAL_JAVA"

FROM alpine:3
MAINTAINER MrGraversen

# Set the environment variables to point to the custom JRE
ENV JAVA_HOME=/opt/minimal-java
ENV PATH="$PATH:$JAVA_HOME/bin"

# Create an application directory and add a non-root user 'app'
RUN mkdir /app
RUN addgroup --system app && adduser -S -s /bin/false -G app app

# Copy the custom minimal JRE from the builder stage
COPY --from=JRE_BUILDER "$JAVA_HOME" "$JAVA_HOME"

# Copy the application JAR file
ARG JAR_FILE
COPY --chown=app:app ${JAR_FILE} /app/app.jar

# Set the working directory
WORKDIR /app

# Use non-root user
USER app

# Start the application using the minimal JRE 21
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-Xss512k", "-jar", "/app/app.jar"]
