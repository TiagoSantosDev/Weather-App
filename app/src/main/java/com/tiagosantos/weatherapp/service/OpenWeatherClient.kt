package com.tiagosantos.weatherapp.service

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val KEY = "e07873c1d4869e0df34d120c34a28b4b"

class OpenWeatherClient {

    private var retrofit: Retrofit? = null
    private val gson = GsonBuilder().setLenient().create()

    val client: Retrofit
        get() {
            if (retrofit == null) {
                synchronized(Retrofit::class.java) {
                    if (retrofit == null) {

                        retrofit = Retrofit.Builder()
                            .baseUrl("https://api.openweathermap.org/data/2.5/")
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build()
                    }
                }
            }
            return retrofit!!
        }
}
