package com.josef.weathermicroservice.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.josef.weathermicroservice.dto.*;
import com.josef.weathermicroservice.translator.WeatherCodeTranslator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class WeatherService
{
    private final RestClient geoClient;
    private final RestClient weatherClient;

    public WeatherService(@Qualifier("geocodingClient") RestClient geoClient,
            @Qualifier("weatherClient") RestClient weatherClient)
    {
        this.geoClient = geoClient;
        this.weatherClient = weatherClient;
    }

    public EnhancedWeatherReport getReport(String city)
    {
        GeocodingResponse geo = geoClient.get().uri("/v1/search?name={c}&count=1&language=en", city).retrieve()
                .onStatus(HttpStatusCode::isError, (req, res) -> {
                    throw new IllegalStateException("Geocoding failed");
                }).body(GeocodingResponse.class);

        if (geo == null || geo.results() == null || geo.results().isEmpty())
        {
            throw new IllegalArgumentException("City boundary data not found: " + city);
        }

        GeoLocation loc = geo.results().get(0);
        WeatherResponse w = weatherClient.get().uri(
                "/v1/forecast?latitude={lat}&longitude={lon}&current=temperature_2m,weather_code&daily=temperature_2m_max,temperature_2m_min&past_days=7",
                loc.latitude(), loc.longitude()).retrieve().onStatus(HttpStatusCode::isError, (req, res) -> {
                    throw new IllegalStateException("Weather API failed");
                }).body(WeatherResponse.class);

        if (w == null || w.current() == null || w.daily() == null)
        {
            throw new IllegalStateException("Failed to retrieve complete weather data.");
        }

        List<DailyTemperature> history = new ArrayList<>();
        var daily = w.daily();

        List<String> times = Objects.requireNonNullElse(daily.time(), Collections.emptyList());
        List<Double> maxes = Objects.requireNonNullElse(daily.max(), Collections.emptyList());
        List<Double> mines = Objects.requireNonNullElse(daily.min(), Collections.emptyList());

        int maxLen = Math.min(7, Math.min(times.size(), Math.min(maxes.size(), mines.size())));
        for (int i = 0; i < maxLen; i++)
        {
            history.add(new DailyTemperature(times.get(i), maxes.get(i), mines.get(i)));
        }

        return new EnhancedWeatherReport(loc.name(), loc.country(), loc.latitude(), loc.longitude(), w.current().time(),
                w.current().temp(), WeatherCodeTranslator.get(w.current().code()), history);
    }
}