package com.example.weatherapp.buisness.data.weatherentity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("weather_data")
data class WeatherEntity(
    @PrimaryKey val id: Int, // Matches the "id" from WeatherDataResponse
    val cityName: String, // Maps to "name" field
    val visibility: Int,
    val timezone: Int,
    val temp: Double, // Maps to main.temp
    val feelsLike: Double, // Maps to main.feels_like
    val tempMin: Double, // Maps to main.temp_min
    val tempMax: Double, // Maps to main.temp_max
    val humidity: Int, // Maps to main.humidity
    val clouds: Int, // Maps to clouds.all
    val windSpeed: Double, // Maps to wind.speed
    val windDeg: Int, // Maps to wind.deg
    val lat: Double, // Maps to coord.lat
    val lon: Double, // Maps to coord.lon
    val timestamp: Long, // Tracks when the data was cached
    val logo: String
)
