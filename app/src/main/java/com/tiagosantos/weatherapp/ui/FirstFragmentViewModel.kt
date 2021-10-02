package com.tiagosantos.weatherapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tiagosantos.weatherapp.models.CurrentWeather

class FirstFragmentViewModel : ViewModel() {

    var currentWeather = MutableLiveData<CurrentWeather>()

    fun getWeatherForCityList() {
    }
}
