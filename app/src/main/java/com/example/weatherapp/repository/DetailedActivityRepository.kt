package com.example.weatherapp.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.network.BASE_URL
import com.example.weatherapp.network.WeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailedActivityRepository(val application: Application) {

    var lastRequestTime : Long = -1

    suspend fun getWeather(woeid : Int) : Response<WeatherResponse>{

        //if the difference between them is greater than 10 sec then below api call executed else not

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherNetwork::class.java)

        return service.getWeather(woeid)
    }
}

//service.getWeather(woeid).enqueue(object : Callback<WeatherResponse>{
//    override fun onResponse(
//        call: Call<WeatherResponse>,
//        resp: Response<WeatherResponse>
//    ) {
//        lastRequestTime = System.currentTimeMillis()
//        response.value = resp.body()
//        showProgress.value = false
//    }
//
//    override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
//        showProgress.value = false
//        Toast.makeText(application, "Error while accesing the server", Toast.LENGTH_SHORT).show()
//    }
//
//})