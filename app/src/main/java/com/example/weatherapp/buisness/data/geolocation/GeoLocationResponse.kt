package com.example.weatherapp.buisness.data.geolocation

import com.google.gson.annotations.SerializedName

typealias GeoLocationResponse = List<GeoLocationItem>

data class GeoLocationItem(@SerializedName("local_names")
                       val localNames: LocalNames,
                               @SerializedName("country")
                       val country: String = "",
                               @SerializedName("name")
                       val name: String = "",
                               @SerializedName("lon")
                       val lon: Double = 0.0,
                               @SerializedName("state")
                       val state: String = "",
                               @SerializedName("lat")
                       val lat: Double = 0.0)