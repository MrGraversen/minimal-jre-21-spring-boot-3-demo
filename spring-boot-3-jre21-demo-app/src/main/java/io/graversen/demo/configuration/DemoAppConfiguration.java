package io.graversen.demo.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"io.graversen.demo.city", "io.graversen.demo.weather", "io.graversen.demo.forecast", "io.graversen.demo.api"})
public class DemoAppConfiguration {

}
