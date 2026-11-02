package com.josef.weathermicroservice.dto;

import java.util.List;

public record GeocodingResponse(List<GeoLocation> results)
{
}