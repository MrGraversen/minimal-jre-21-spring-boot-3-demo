package io.graversen.openmeteo.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import io.graversen.openmeteo.client.OpenMeteo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OpenMeteoConfiguration {
    private final @NonNull ObjectMapper objectMapper;

    @Bean
    public OpenMeteo openMeteo() {
        return Feign.builder()
                .decoder(new JacksonDecoder(objectMapper))
                .target(OpenMeteo.class, "https://api.open-meteo.com");
    }
}
