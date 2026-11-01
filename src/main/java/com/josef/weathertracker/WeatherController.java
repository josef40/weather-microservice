package com.josef.weathertracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController
{
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService)
    {
        this.weatherService = weatherService;
    }

    @GetMapping("/api/weather/live")
    public String getLiveWeather(@RequestParam double latitude, @RequestParam double longitude)
    {
        String weatherData = weatherService.fetchLiveWeather(latitude, longitude);
        return weatherData;
    }
}