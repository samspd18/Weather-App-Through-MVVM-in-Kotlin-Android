package com.example.weatherapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.weatherapp.model.Location
import com.example.weatherapp.repository.SearchActivityRepository

//Here we take android view model instead of view model coz view model does not give the context which is helpful for the text and all , that provided by the Android View model
class SearchActivityViewModel(application: Application) : AndroidViewModel(application) {
    //To set this mutable live data of SearchActivityRepository value to live data we are create a repository object in our view model
    // and pass the context , here the context is application

    //sometime we directly call the value from the repository instead of view model , that's why we make repository private
    private val repository = SearchActivityRepository(application)
    val showProgress : LiveData<Boolean>
    val locationList : LiveData<List<Location>>

    init {
        this.showProgress = repository.showProgress
        this.locationList = repository.locationList
    }

    fun changeState(){
        repository.changeState()
    }
//Here we send the details to retrofit to do the network call
    fun searchLocation(searchString: String){
        repository.searchLocation(searchString)
    }
}