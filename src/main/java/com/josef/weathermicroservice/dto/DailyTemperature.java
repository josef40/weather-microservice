package com.josef.weathermicroservice.dto;

public record DailyTemperature(String date, double maxTemp, double minTemp)
{
}