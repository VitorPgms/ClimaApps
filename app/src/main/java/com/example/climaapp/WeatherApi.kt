package com.example.climaapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/weather")
    fun getWeather(
        @Query("q") city: String,  //Aqui vai passar o nome da cidade para a API
        @Query("appid") apiKey: String,  //Pega a a chave da API
        @Query("units") units: String = "metric" // Para obter a temperatura em Celsius
    ): Call<WeatherResponse>
}