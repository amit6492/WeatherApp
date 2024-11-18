package com.example.weatherapp.databse

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.buisness.data.weatherentity.WeatherEntity

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherData(weatherEntity: WeatherEntity)

    @Query("Select * FROM weather_data WHERE lat = :lat AND lon = :lon")
    suspend fun getWeather(lat: Double, lon: Double): WeatherEntity

    @Query("DELETE FROM weather_data where timestamp < :timeThreshold")
    suspend fun deleteData(timeThreshold: Long)
}