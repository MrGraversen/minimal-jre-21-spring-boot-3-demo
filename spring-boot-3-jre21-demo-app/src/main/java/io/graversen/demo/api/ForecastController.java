package io.graversen.demo.api;

import io.graversen.demo.forecast.ForecastFacade;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ForecastController {
    private final @NonNull ForecastFacade forecastFacade;

    @GetMapping("/forecast")
    public String getForecast() {
        return forecastFacade.getForecast();
    }
}
