package com.josef.weathertracker.dto;

public record GeoLocation(
        String name,
        double latitude,
        double longitude,
        String country) {
}