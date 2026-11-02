package com.josef.weathermicroservice.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;

import com.josef.weathermicroservice.dto.EnhancedWeatherReport;
import com.josef.weathermicroservice.service.WeatherService;

@RestController @RequestMapping("/api/weather")
public class WeatherController
{
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService)
    {
        this.weatherService = weatherService;
    }

    @GetMapping("/report")
    public EnhancedWeatherReport getWeatherReport(
            @Parameter(description = "Enter any city name", schema = @Schema(type = "string")) @RequestParam String city)
    {
        if (city == null || city.isBlank())
        {
            throw new IllegalArgumentException("City name cannot be empty");
        }
        return weatherService.getReport(city.trim());
    }
}