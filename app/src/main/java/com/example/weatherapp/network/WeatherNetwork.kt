package com.example.weatherapp.network

import com.example.weatherapp.model.Location
import com.example.weatherapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


const val BASE_URL = "https://www.metaweather.com/api/location/"
interface WeatherNetwork {

    @GET("search")
    fun getLocatiion(@Query("query")searchString: String) : Call<List<Location>>

    @GET("{woeid}")
    suspend fun getWeather(@Path("woeid") woeid : Int) : Response<WeatherResponse>
}

