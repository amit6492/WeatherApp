package com.example.weatherapp.databse

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.buisness.data.weatherentity.WeatherEntity

@Database(entities = [WeatherEntity::class], version = 1, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}