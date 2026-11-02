package com.josef.weathertracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record WeatherResponse(CurrentWeather current, DailyForecast daily) {
    public record CurrentWeather(
            String time,
            @JsonProperty("temperature_2m") double temp,
            @JsonProperty("weather_code") int code) {
    }

    public record DailyForecast(
            List<String> time,
            @JsonProperty("temperature_2m_max") List<Double> max,
            @JsonProperty("temperature_2m_min") List<Double> min) {
    }
}