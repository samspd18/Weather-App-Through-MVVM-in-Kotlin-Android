package com.example.weatherapp.model

data class WeatherResponse(
    val consolidated_weather: List<ConsolidatedWeather>,
    val latt_long: String,
    val location_type: String,
    val parent: Parent,
    val sources: List<Source>,
    val sun_rise: String,
    val sun_set: String,
    val time: String,
    val timezone: String,
    val timezone_name: String,
    val title: String,
    val woeid: Int
)


data class Parent(
    val latt_long: String,
    val location_type: String,
    val title: String,
    val woeid: Int
)

data class Source(
    val crawl_rate: Int,
    val slug: String,
    val title: String,
    val url: String
)