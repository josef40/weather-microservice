package com.josef.weathertracker;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WeatherService
{

    private final RestClient restClient;

    public WeatherService(RestClient restClient)
    {
        this.restClient = restClient;
    }

    public String fetchLiveWeather(double latitude, double longitude)
    {
        String baseUrl = "https://api.open-meteo.com";

        String apiEndpoint = "/v1/forecast?latitude={lat}&longitude={lon}&current=temperature_2m,weather_code";

        String rawJsonResponse = restClient.get().uri(baseUrl + apiEndpoint, latitude, longitude).retrieve()
                .body(String.class);

        return rawJsonResponse;
    }
}
