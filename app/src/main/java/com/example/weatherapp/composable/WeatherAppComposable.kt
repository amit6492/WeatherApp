package com.example.weatherapp.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.buisness.data.WeatherDataResponse
import com.example.weatherapp.buisness.data.weatherentity.WeatherEntity
import com.example.weatherapp.uistates.UiStates
import com.example.weatherapp.viewmodel.WeatherViewModel

@Composable
fun WeatherAppComposable(viewModel: WeatherViewModel) {

    val weatherData by viewModel.weatherData.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    var city by remember { mutableStateOf("") }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0E0E0))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 76.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
                .background(Color(0xFFE0E0E0)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Input for location
            TextField(
                value = city,
                onValueChange = {city = it},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location Icon",
                        tint = Color.Gray
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { city = "" }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear Text",
                            tint = Color.Gray
                        )
                    }
                },
                placeholder = { Text(text = "Enter City", color = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.fetchWeather(city = city) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Get Weather")
            }
            Spacer(modifier = Modifier.height(32.dp))

            if (errorMessage != null) {
                Text(text = errorMessage!!, color = Color.Red)
            } else if (weatherData != null) {
                WeatherContent(weatherData!!)
            } else {
                Text("Enter a city to get weather updates")
            }
        }
    }

}

@Composable
fun WeatherContent(weatherData: WeatherEntity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Weather Icon
        WeatherIcon(icon = weatherData.logo) }

        // City Name
        Text(
            text = weatherData.cityName,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )


        // Temperature Details
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Temp: ${weatherData.temp}°C",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Feels Like: ${weatherData.feelsLike}°C",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // Additional Weather Details
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Min: ${weatherData.tempMin}°C",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Max: ${weatherData.tempMax}°C",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // Atmospheric Details
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Humidity: ${weatherData.humidity}%",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Clouds: ${weatherData.clouds} hPa",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Wind Speed: ${weatherData.windSpeed} m/s",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

@Composable
fun WeatherIcon(icon: String) {
    val iconUrl = "https://openweathermap.org/img/wn/${icon}@2x.png"
    AsyncImage(
        model = iconUrl,
        contentDescription = "Weather Icon",
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop,
    )
}
