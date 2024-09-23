package com.example.climaapp

data class WeatherResponse(
    val main: Main,
    val clouds: Clouds
)

data class Main(
    val temp: Double,
    val humidity: Double,
    val pressure: Double,
    val temp_min: Double,
    val temp_max: Double
)

data class Clouds(
    val all: Int // Representa a porcentagem de cobertura de nuvens
)