package io.graversen.openmeteo.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(onConstructor_ = {@JsonCreator})
public class CurrentWeatherDTO {
    @JsonProperty("temperature")
    private final String temperature;

    @JsonProperty("windspeed")
    private final String windSpeed;

    @Override
    public String toString() {
        return "Temperature: " + temperature + "°C, Wind Speed: " + windSpeed + " km/h";
    }
}
