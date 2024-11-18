package com.example.weatherapp.buisness.repository

import com.example.weatherapp.buisness.data.WeatherDataResponse
import com.example.weatherapp.buisness.data.geolocation.GeoLocationResponse
import com.example.weatherapp.buisness.data.weatherentity.WeatherEntity

interface WeatherDataRepository {
    suspend fun getWeather(lat: Double, long: Double): WeatherEntity

    suspend fun getGeoLocation(city:String): GeoLocationResponse
}