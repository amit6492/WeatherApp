package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.databse.WeatherDao
import com.example.weatherapp.databse.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase{
        return Room.databaseBuilder(
            context = context,
            WeatherDatabase::class.java,
            "weather_data"
        ).build()
    }

    @Provides
    fun provideWeatherDao(database: WeatherDatabase): WeatherDao{
        return database.weatherDao()
    }
}