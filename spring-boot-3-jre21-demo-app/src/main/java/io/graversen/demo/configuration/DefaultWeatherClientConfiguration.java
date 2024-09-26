package io.graversen.demo.configuration;

import io.graversen.demo.integration.weather.OpenMeteoWeatherClient;
import io.graversen.demo.integration.weather.WeatherClient;
import io.graversen.openmeteo.client.OpenMeteo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DefaultWeatherClientConfiguration {
    @Bean
    @Primary
    public WeatherClient openMeteoWeatherClient(OpenMeteo openMeteo) {
        return new OpenMeteoWeatherClient(openMeteo);
    }
}
