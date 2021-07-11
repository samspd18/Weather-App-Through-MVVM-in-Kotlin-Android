package com.example.weatherapp.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.model.Location
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.network.BASE_URL
import com.example.weatherapp.network.WeatherNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivityRepository(val application: Application) {

    val showProgress = MutableLiveData<Boolean>()
    val locationList = MutableLiveData<List<Location>>()

    //if the value is not null then the progress of the progressbar is null ,
    // and value is null the progressbar show
    fun changeState(){
        showProgress.value = !(showProgress.value != null && showProgress.value!!)
    }

    //After network call we have to set the show progress value is false
    fun searchLocation(searchString: String){
        showProgress.value = true
        //network call

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherNetwork::class.java)

        service.getLocatiion(searchString).enqueue(object : Callback<List<Location>>{
            override fun onResponse(
                call: Call<List<Location>>,
                response: Response<List<Location>>
            ) {
                showProgress.value = false
                locationList.value = response.body()
                Log.d("satya", "onResponse: ${response.body()}")
            }

            override fun onFailure(
                call: Call<List<Location>>,
                t: Throwable
            ) {
                showProgress.value = false
                Toast.makeText(application, "Error while accesing the server", Toast.LENGTH_SHORT).show()
            }
        })
    }
}