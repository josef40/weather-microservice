package com.josef.weathertracker.dto;

public record DailyTemperature(
        String date,
        double maxTemp,
        double minTemp) {
}