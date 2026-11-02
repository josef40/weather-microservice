package com.josef.weathermicroservice.translator;

public class WeatherCodeTranslator
{
    public static String get(int c)
    {
        return switch (c) {
        case 0 -> "Clear sky";
        case 1, 2, 3 -> "Partly cloudy / Overcast";
        case 45, 48 -> "Foggy";
        case 51, 53, 55 -> "Drizzle";
        case 61, 63, 65 -> "Rainy";
        case 71, 73, 75 -> "Snowfall";
        case 80, 81, 82 -> "Rain showers";
        case 95, 96, 99 -> "Thunderstorm";
        default -> "Variable conditions (" + c + ")";
        };
    }
}