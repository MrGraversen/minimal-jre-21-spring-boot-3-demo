package io.graversen.demo.forecast;

import io.graversen.demo.city.City;
import io.graversen.demo.city.CityService;
import io.graversen.demo.weather.WeatherService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ForecastFacade {
    private final @NonNull CityService cityService;
    private final @NonNull WeatherService weatherService;

    private final ThreadFactory virtualThreadFactory = Thread.ofVirtual().factory();
    private final ExecutorService virtualThreadExecutorService = Executors.newThreadPerTaskExecutor(virtualThreadFactory);

    @SneakyThrows
    public String getForecast() {
        final var cities = cityService.getCities();
        final var cityNames = cities.stream()
                .map(City::name)
                .collect(Collectors.joining(", "));

        log.info("Imminently fetching weather forecasts for cities: {}", cityNames);

        final var getWeatherForecastTasks = cities.stream()
                .map(city -> getWeatherForecastTask(city.name(), city.latitude(), city.longitude()))
                .map(getWeatherForecastTask -> CompletableFuture.supplyAsync(getWeatherForecastTask, virtualThreadExecutorService))
                .toList();

        final var weatherForecasts = getWeatherForecastTasks.stream()
                .map(CompletableFuture::join)
                .toList();

        return weatherForecasts.stream().collect(Collectors.joining(System.lineSeparator()));
    }

    private Supplier<String> getWeatherForecastTask(@NonNull String cityName, @NonNull String latitude, @NonNull String longitude) {
        return () -> weatherService.getWeather(latitude, longitude)
                .map(weather -> String.format("%s: %s", cityName, weather))
                .orElse(String.format("%s: Unknown weather; Probably rainy 🌧️", cityName));
    }
}
