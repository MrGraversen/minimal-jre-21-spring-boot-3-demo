package io.graversen.demo.integration.weather;

import io.graversen.demo.weather.Weather;
import io.graversen.openmeteo.client.OpenMeteo;
import io.graversen.openmeteo.client.WeatherResponseDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
public class OpenMeteoWeatherClient implements WeatherClient {
    private final @NonNull OpenMeteo openMeteo;

    @Override
    public Optional<Weather> getWeather(@NonNull String latitude, @NonNull String longitude) {
        final var weatherResponse = openMeteo.getForecast(latitude, longitude);

        if (weatherResponse != null) {
            return mapWeather()
                    .andThen(Optional::of)
                    .apply(weatherResponse);
        }

        return Optional.empty();
    }

    Function<WeatherResponseDTO, Weather> mapWeather() {
        return weatherResponse -> new Weather(
                weatherResponse.getCurrentWeather().getTemperature(),
                weatherResponse.getCurrentWeather().getWindSpeed()
        );
    }
}
