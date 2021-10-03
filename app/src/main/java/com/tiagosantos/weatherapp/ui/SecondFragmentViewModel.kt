package com.tiagosantos.weatherapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tiagosantos.weatherapp.models.CurrentWeather
import com.tiagosantos.weatherapp.service.OpenWeatherClient

class WeatherDetailsViewModel(private val cityId: String) : ViewModel() {

    var weatherRepo = MutableLiveData<CurrentWeather>()
    private val apiClient = OpenWeatherClient()

    suspend fun getWeather(): CurrentWeather {
        weatherRepo.value = apiClient.initClient()
        return weatherRepo.value!!
    }
}
