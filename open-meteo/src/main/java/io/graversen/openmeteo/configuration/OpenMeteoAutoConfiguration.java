package io.graversen.openmeteo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(OpenMeteoConfiguration.class)
public class OpenMeteoAutoConfiguration {

}
