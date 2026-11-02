package com.josef.weathertracker.dto;

import java.util.List;

public record GeocodingResponse(List<GeoLocation> results) {
}