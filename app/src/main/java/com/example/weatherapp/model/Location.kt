package com.example.weatherapp.model

//class Location : ArrayList<LocationItem>()

data class Location(
    val latt_long: String,
    val location_type: String,
    val title: String,
    val woeid: Int
)