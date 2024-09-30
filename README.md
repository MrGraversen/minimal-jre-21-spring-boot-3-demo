# Minimal JRE 21 Docker Image for Java/Spring Boot Applications

Welcome to the Minimal JRE 21 Docker Image project! 🎉

This project demonstrates how to create a slim, secure Docker image for Java 21/Spring Boot applications by building a custom minimal Java Runtime Environment (JRE) using `jlink`. The goal is to optimize the Docker image for size, security, and performance.

As a working example, the demo application leverages JRE 21's **Virtual Threads** to achieve concurrent I/O while retrieving simple weather reports for the Nordic cities: Copenhagen, Stockholm, Oslo, and Helsinki. The weather forecasts are fetched concurrently and combined using Java's `CompletableFuture` API.

## 🚀 Goals

* **Java 21 Compatibility:** Ensure the ability to run applications using JRE 21.
* **Non-Root Execution:** Run the application as a non-root user for enhanced security.
* **Minimal JRE:** Create a custom JRE that includes only the necessary modules to reduce image size and memory footprint.
* **Spring Boot 3 Demo:** Showcase a sample Spring Boot 3 app utilizing app that applies the JRE 21 feature of **Virtual Threads**. 

## 🔧 How It Works

The Dockerfile uses a multi-stage build:

1. **Builder Stage:** Uses `eclipse-temurin:21-jdk-alpine` to build a minimal JRE with `jlink`, including only the modules required by the application.
2. **Final Stage:** Based on `alpine:3`, it sets up a non-root user, copies the minimal JRE and the application JAR, and runs the application with optimized JVM parameters.

## 📊 Results

By building a minimal JRE and optimizing the Docker image, we achieved significant reductions in image size:

| Image                      | Dockerfile                | Image Size |
|----------------------------|---------------------------|------------|
| Reference Image (Full JRE) | `JRE21.Dockerfile`        | 261 MB     |
| Minimal JRE Image          | `MinimalJRE21.Dockerfile` | 99 MB      |

**Image Size Reduction:** We saved a total of **62%** on the final Docker image size! 🎉

## 🛠️ Usage

Build the application and create the Docker image using Maven:
```shell
mvn clean install docker:build
```
To run the Docker application while exposing port `8080`, do the following:
```shell
docker run --rm -p 8080:8080 ghcr.io/mrgraversen/minimal-jre-21-spring-boot-3-demo
```

To call the API, use the following `curl` command:
```shell
curl http://localhost:8080/forecast
```

*Example Response*
```
Copenhagen: Temperature: 14.4°C, Wind Speed: 19.1 km/h
Stockholm: Temperature: 13.1°C, Wind Speed: 8.3 km/h
Oslo: Temperature: 11.7°C, Wind Speed: 7.9 km/h
Helsinki: Temperature: 12.1°C, Wind Speed: 9.4 km/h
```

## 🤔 Why a Customised, Minimal JRE?

There are several reasons to select this approach when developing Java and Spring Boot applications:
* **Reducing Image Size:** Smaller images are faster to download and deploy. By applying these principles to this demo project, we saved a total of 62% on the final Docker image size.
* **Lower Memory Footprint:** Including only necessary modules reduces RAM usage at runtime, as the JVM loads fewer classes and resources.
* **Improved Security:** Fewer components reduce the attack surface, and avoiding the use of the `root` user further enhances security.
