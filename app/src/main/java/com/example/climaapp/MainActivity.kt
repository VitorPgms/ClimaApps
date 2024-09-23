package com.example.climaapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val cityTextView: TextView = findViewById(R.id.city)
        val tempTextView: TextView = findViewById(R.id.temp)
        val humidityTextView: TextView = findViewById(R.id.humidity)
        val pressureTextView: TextView = findViewById(R.id.pressure)
        val cloudsTextView: TextView = findViewById(R.id.clouds)
        val tempMaxTextView: TextView = findViewById(R.id.temp_max)
        val tempMinTextView: TextView = findViewById(R.id.temp_min)

        val city:String = "São Paulo"
        val apiKey = "20b8b0b6449d6eabe7ce014536169892"

        cityTextView.text = city

        RetrofitClient.weatherAPI.getWeather(city, apiKey).enqueue(object : Callback<WeatherResponse>{
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful){
                    val weatherResponse = response.body()

                    val temp = weatherResponse?.main?.temp?.toInt() ?: "N/A"
                    val humidity = weatherResponse?.main?.humidity ?: "N/A" //Aqui estou chamando o Umidade do OpenWeather
                    val pressure = weatherResponse?.main?.pressure ?: "N/A"
                    val tempMax = weatherResponse?.main?.temp_max?.toInt() ?: "N/A"
                    val tempMin = weatherResponse?.main?.temp_min?.toInt() ?: "N/A"
                    val clouds = weatherResponse?.clouds?.all?: "N/A"

                    tempTextView.text = "$temp ºC"
                    humidityTextView.text ="$humidity%"
                    pressureTextView.text ="$pressure'hPA"
                    cloudsTextView.text ="$clouds'm/s"
                    tempMaxTextView.text ="Temp Max: $tempMax"
                    tempMinTextView.text ="Temp Min: $tempMin"
                } else{
                    tempTextView.text = "Erro: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                tempTextView.text = "Falha ${t.message}"
            }
        })
    }
}