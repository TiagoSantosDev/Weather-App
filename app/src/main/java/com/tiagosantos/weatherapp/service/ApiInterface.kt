package com.tiagosantos.weatherapp.service

import com.tiagosantos.weatherapp.models.CurrentWeather
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("weather")
    suspend fun fetchCurrentWeatherForCity(@Query("id") id: String): CurrentWeather
}
