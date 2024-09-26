package io.graversen.demo.integration.weather;

import io.graversen.demo.weather.Weather;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class NoOpWeatherClient implements WeatherClient {
    @Override
    public Optional<Weather> getWeather(@NonNull String latitude, @NonNull String longitude) {
        // I got nothing, sorry 🤷‍♂️
        log.info("Not returning any weather for location: [{}, {}]", latitude, longitude);
        return Optional.empty();
    }
}
