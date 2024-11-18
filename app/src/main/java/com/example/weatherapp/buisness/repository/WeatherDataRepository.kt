package com.example.weatherapp.buisness.repository

import com.example.weatherapp.buisness.data.WeatherDataResponse
import com.example.weatherapp.buisness.data.geolocation.GeoLocationResponse

interface WeatherDataRepository {
    suspend fun getWeather(lat: Double, long: Double): WeatherDataResponse

    suspend fun getGeoLocation(city:String): GeoLocationResponse
}