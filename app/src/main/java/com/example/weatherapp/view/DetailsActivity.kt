package com.example.weatherapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.viewModel.DetailsActivityViewModel
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailsActivityViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        viewModel = ViewModelProvider(this).get(DetailsActivityViewModel::class.java)

        if (intent.hasExtra("name")) {
            tv_location.text = intent.getStringExtra("name")
        }

        if (intent.hasExtra("Location")) {
            // Do network call
            val location = intent.getIntExtra("Location", 0)

            if (location > 0 && viewModel.response.value == null) {
                GlobalScope.launch(Dispatchers.IO) {
                    viewModel.getWeather(location)
                }

            }
        }

        viewModel.showProgress.observe(this, Observer {
            if (it)
                details_progress.visibility = VISIBLE
            else
                details_progress.visibility = GONE
        })

        viewModel.response.observe(this, Observer {
            if (it != null)
                tv_temp.text = it.consolidated_weather[0].the_temp.toString()+" C"
        })

    }
}