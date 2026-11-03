# 🌦️ Weather Microservice

A Spring Boot microservice that allows users to enter any city name and receive a weather report for the past 7 days, including human-readable condition descriptions translated from raw API codes.

## 🧬 Architecture
* **Frontend:** SpringDoc OpenAPI
* **Backend:** Spring Boot 4.1.2

## 🔧 Prerequisites
* `JDK 25+`
* `Maven 3.x`

##
```
weather-microservice/
└── src/
    └── main/
        └── java/
            └── com/
                └── josef/
                    └── weathermicroservice/
                        ├── config/
                        │   └── AppConfig.java
                        ├── controller/
                        │   └── WeatherController.java
                        ├── dto/
                        │   ├── DailyTemperature.java
                        │   ├── EnhancedWeatherReport.java
                        │   ├── GeocodingResponse.java
                        │   ├── GeoLocation.java
                        │   └── WeatherResponse.java
                        ├── exception/
                        │   └── GlobalExceptionHandler.java
                        ├── service/
                        │   └── WeatherService.java
                        ├── translator/
                        │   └── WeatherCodeTranslator.java
                        └── WeatherMicroservice.java
```

## 🚀 Getting Started
1. Run `WeatherMicroservice.java`
2. Open **http://localhost:8080/swagger-ui/index.html**