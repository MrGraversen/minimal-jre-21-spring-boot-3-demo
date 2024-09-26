package io.graversen.openmeteo;

import feign.Param;
import feign.RequestLine;

public interface OpenMeteo {
    @RequestLine("GET /v1/forecast?latitude={latitude}&longitude={longitude}&current_weather=true")
    WeatherResponseDTO getForecast(@Param("latitude") String latitude, @Param("longitude") String longitude);
}
