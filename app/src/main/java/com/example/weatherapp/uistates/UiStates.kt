package com.example.weatherapp.uistates

import java.lang.Exception

sealed class UiStates<out T> {
    data class Success<out T>(val data: T): UiStates<T>()
    data class Error(val message: String, val exception: Throwable? = null): UiStates<Nothing>()
    object Loading: UiStates<Nothing>()

}