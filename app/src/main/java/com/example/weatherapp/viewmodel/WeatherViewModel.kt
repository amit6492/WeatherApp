package com.example.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.buisness.data.WeatherDataResponse
import com.example.weatherapp.buisness.repository.WeatherDataRepository
import com.example.weatherapp.uistates.UiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherDataRepository: WeatherDataRepository
): ViewModel() {

    private val _weatherData = MutableStateFlow<WeatherDataResponse?>(null)
    val weatherData: StateFlow<WeatherDataResponse?> = _weatherData

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchWeather(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Fetch geo-coordinates
                val geoLocation = weatherDataRepository.getGeoLocation(city)
                if (geoLocation == null) {
                    _errorMessage.value = "Invalid city name or location not found."
                    return@launch
                }

                // Fetch weather data
                val response = weatherDataRepository.getWeather(geoLocation[0].lat, geoLocation[0].lon)
                _weatherData.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.localizedMessage}"
            }
        }
    }
}