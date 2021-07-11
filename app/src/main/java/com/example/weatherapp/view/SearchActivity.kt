package com.example.weatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.adapter.LocationAdapter
import com.example.weatherapp.viewModel.SearchActivityViewModel
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private lateinit var viewModel: SearchActivityViewModel
    private lateinit var adapter: LocationAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        //We don't want to create view model object again and again , that's why we are using view model provider
        viewModel = ViewModelProvider(this).get(SearchActivityViewModel::class.java)

        //On clicl of search the value of the et_search passed to the searchLocation
        //method which is present in the view model
        iv_search.setOnClickListener{
            if (et_search != null) {
                viewModel.searchLocation(et_search.text.toString())
            }else{
                Toast.makeText(this, "Please enter a city name", Toast.LENGTH_SHORT).show()
            }
        }
        //Inside observe we pass two parameter
        //1.lifecycle owner
        //2.observer
        //using the life cycle owner the view model can know the visibility of an activity or a fragment
        //According to to visibility of the owner , it will trigger the observer

        viewModel.showProgress.observe(this, Observer {
            //observer is the call back method which is triggered , when the value changes
            //if means true in boolean
            if(it){
                search_progressbar.visibility = VISIBLE
            }else{
                search_progressbar.visibility = GONE
            }
        })

        viewModel.locationList.observe(this, Observer {
            adapter.setlocationlist(it)
        })

        adapter = LocationAdapter(this)
        rv_search.adapter = adapter
    }
}