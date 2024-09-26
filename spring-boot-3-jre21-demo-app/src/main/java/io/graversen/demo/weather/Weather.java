package io.graversen.demo.weather;

public record Weather(String temperature, String windSpeed) {
    @Override
    public String toString() {
        return "Temperature: " + temperature + "°C, Wind Speed: " + windSpeed + " km/h";
    }
}
