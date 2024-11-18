package com.example.weatherapp.buisness.repository

import com.example.weatherapp.buisness.api.WeatherApiService
import com.example.weatherapp.buisness.data.WeatherDataResponse
import com.example.weatherapp.buisness.data.geolocation.GeoLocationResponse
import com.example.weatherapp.buisness.data.weatherentity.WeatherEntity
import com.example.weatherapp.databse.WeatherDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val APP_ID = "adfaa684d7dc7d07d19552c88461e5cd"

class WeatherDataRepositoryImpl @Inject constructor(
    private val weatherApiService: WeatherApiService,
    private val weatherDao: WeatherDao
): WeatherDataRepository {
    override suspend fun getWeather(lat: Double, long: Double): WeatherEntity {
        return withContext(Dispatchers.IO) {
            try {
                val apiResponse = weatherApiService.getWeather(lat, long, APP_ID)
                val weatherEntity = apiResponse.weather?.first()?.icon?.let {
                    WeatherEntity(
                        id = apiResponse.id,
                        cityName = apiResponse.name,
                        lat = lat,
                        lon = long,
                        visibility = apiResponse.visibility,
                        timezone = apiResponse.timezone,
                        temp = (apiResponse.main.temp - 273.15).format(2).toDouble(),
                        tempMin = (apiResponse.main.tempMin - 273.15).format(2).toDouble(),
                        tempMax = (apiResponse.main.tempMax - 273.15).format(2).toDouble(),
                        humidity = apiResponse.main.humidity,
                        feelsLike = (apiResponse.main.feelsLike - 273.15).format(2).toDouble(),
                        clouds = apiResponse.clouds.all,
                        windDeg = apiResponse.wind.deg,
                        windSpeed = apiResponse.wind.speed,
                        timestamp = apiResponse.timezone.toLong(),
                        logo = it

                    )
                }
                weatherEntity?.let { weatherDao.insertWeatherData(it) }
                weatherEntity
            } catch (e: Exception){
                weatherDao.getWeather(lat, long)
                    ?: throw Exception("No cached data available")
            }!!
        }
    }

    override suspend fun getGeoLocation(city: String): GeoLocationResponse {
        return weatherApiService.getGeoLocation(city, "5", APP_ID)
    }

    private fun Double.format(digits: Int) = String.format("%.${digits}f", this)

}