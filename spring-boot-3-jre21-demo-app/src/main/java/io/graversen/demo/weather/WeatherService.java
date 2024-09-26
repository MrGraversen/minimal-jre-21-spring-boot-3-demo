package io.graversen.demo.weather;

import io.graversen.demo.integration.weather.WeatherClient;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherService {
    private final @NonNull WeatherClient weatherClient;

    public Optional<Weather> getWeather(@NonNull String latitude, @NonNull String longitude) {
        log.info("Requesting weather forecast for location [{}, {}]", latitude, longitude);
        return weatherClient.getWeather(latitude, longitude);
    }
}
