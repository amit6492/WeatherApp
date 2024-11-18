package com.example.weatherapp.buisness.api

import com.example.weatherapp.buisness.data.WeatherDataResponse
import com.example.weatherapp.buisness.data.geolocation.GeoLocationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") appId: String
    ) : WeatherDataResponse

    @GET("geo/1.0/direct")
    suspend fun getGeoLocation(
        @Query("q") city: String,
        @Query("limit") limit: String,
        @Query("appid") appId: String
    ): GeoLocationResponse
}