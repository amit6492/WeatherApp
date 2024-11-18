package com.example.weatherapp.buisness.repository

import com.example.weatherapp.buisness.api.WeatherApiService
import com.example.weatherapp.buisness.data.WeatherDataResponse
import com.example.weatherapp.buisness.data.geolocation.GeoLocationResponse
import javax.inject.Inject

const val APP_ID = "adfaa684d7dc7d07d19552c88461e5cd"

class WeatherDataRepositoryImpl @Inject constructor(
    private val weatherApiService: WeatherApiService
): WeatherDataRepository {
    override suspend fun getWeather(lat: Double, long: Double): WeatherDataResponse {
        val response = weatherApiService.getWeather(lat, long, APP_ID)
        return response.copy(
            main = response.main.copy(
                temp = (response.main.temp - 273.15).format(2).toDouble(),
                feelsLike = (response.main.feelsLike - 273.15).format(2).toDouble(),
                tempMax = (response.main.tempMax - 273.15).format(2).toDouble(),
                tempMin = (response.main.tempMin - 273.15).format(2).toDouble()
            )
        )
    }

    override suspend fun getGeoLocation(city: String): GeoLocationResponse {
        return weatherApiService.getGeoLocation(city, "5", APP_ID)
    }

    private fun Double.format(digits: Int) = String.format("%.${digits}f", this)

}