package com.example.geckoweatherapp

import com.example.geckoweatherapp.city.WeatherForLocation
import retrofit2.http.GET
import retrofit2.http.Query

interface WebApi {

    @GET("data/2.5/weather/")
    suspend fun getWeatherForLocation(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float
    ): WeatherForLocation


}