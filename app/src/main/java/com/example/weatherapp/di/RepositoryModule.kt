package com.example.weatherapp.di

import com.example.weatherapp.buisness.repository.WeatherDataRepository
import com.example.weatherapp.buisness.repository.WeatherDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherDataRepositoryImpl: WeatherDataRepositoryImpl
    ): WeatherDataRepository

}