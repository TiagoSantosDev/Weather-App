package com.tiagosantos.weatherapp.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OpenWeatherClient {

    fun initClient() {
        var OpenWeatherClient = retrofit.create(OpenWeatherClient::class.java)
    }

    private val retrofit by lazy {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val url = chain.request().url.newBuilder()
                    .addQueryParameter("appid", "")
                    .addQueryParameter("units", "metric").build()
                val request = chain.request().newBuilder()
                    .url(url).build()
                chain.proceed(request)
            }.build()
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()
    }
}

/*
    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }*/
