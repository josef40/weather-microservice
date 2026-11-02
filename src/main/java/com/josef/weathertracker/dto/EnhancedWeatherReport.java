package com.josef.weathertracker.dto;

import java.util.List;

public record EnhancedWeatherReport(
        String cityName,
        String country,
        double latitude,
        double longitude,
        String timeOfReport,
        double currentTemperatureCelsius,
        String currentWeatherCondition,
        List<DailyTemperature> pastSevenDaysHistory) {
}