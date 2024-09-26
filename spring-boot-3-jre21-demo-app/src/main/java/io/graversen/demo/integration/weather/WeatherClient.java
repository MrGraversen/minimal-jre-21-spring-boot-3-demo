package io.graversen.demo.integration.weather;

import io.graversen.demo.weather.Weather;
import lombok.NonNull;

import java.util.Optional;

public interface WeatherClient {
    Optional<Weather> getWeather(@NonNull String latitude, @NonNull String longitude);
}
