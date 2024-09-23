package com.example.climaapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{
    private const val BASE_URL = "https://api.openweathermap.org/" //URL do site que irei consumi a api

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherAPI: WeatherApi = retrofit.create(WeatherApi::class.java)
}