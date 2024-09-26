package io.graversen.demo.configuration;

import io.graversen.demo.integration.weather.NoOpWeatherClient;
import io.graversen.demo.integration.weather.WeatherClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NoOpWeatherClientConfiguration {
    @Bean
    @ConditionalOnMissingBean(WeatherClient.class)
    public WeatherClient noOpWeatherClient() {
        return new NoOpWeatherClient();
    }
}
