package com.example.geckoweatherapp.city

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherForLocation(
    @Json(name = "coord") val coordinates: Coordinates,
    @Json(name = "main") val main: Main,
    @Json(name = "wind") val wind: Wind,
    @Json(name = "name") val name: String
) {

    @JsonClass(generateAdapter = true)
    data class Coordinates(
        @Json(name = "lat") val latitude: Float,
        @Json(name = "lon") val longitude: Float,
    )

    @JsonClass(generateAdapter = true)
    data class Main(
        @Json(name = "temp") val temp: Float,
        @Json(name = "pressure") val pressure: Int,
        @Json(name = "humidity") val humidity: Int,
    )

    @JsonClass(generateAdapter = true)
    data class Wind(
        @Json(name = "speed") val speed: Float
    )
}