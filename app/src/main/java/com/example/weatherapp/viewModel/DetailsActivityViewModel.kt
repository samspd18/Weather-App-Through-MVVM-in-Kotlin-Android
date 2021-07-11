package com.example.weatherapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.repository.DetailedActivityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DetailedActivityRepository(application)

    val showProgress : LiveData<Boolean> = MutableLiveData()
    val response : LiveData<WeatherResponse> = MutableLiveData()


    init {
        viewModelScope.launch {
            response as MutableLiveData
            response.value = getWeather(woied)
        }
    }

    private fun getWeather(woied: Any): WeatherResponse? {

    }


}