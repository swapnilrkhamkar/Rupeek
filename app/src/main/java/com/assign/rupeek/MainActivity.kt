package com.assign.rupeek

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assign.rupeek.adapter.WeatherAdapter
import com.assign.rupeek.model.DataW
import com.assign.rupeek.network.Error
import com.assign.rupeek.network.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var weatherList: List<DataW>
    private lateinit var weatherAdapter: WeatherAdapter
    private lateinit var model: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager: RecyclerView.LayoutManager
        rvWeather.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        rvWeather.layoutManager = layoutManager
        weatherList = ArrayList()

        weatherAdapter = WeatherAdapter(this, weatherList)
        rvWeather.adapter = weatherAdapter;

        model = ViewModelProvider(this).get(WeatherViewModel::class.java)

        model.getWeather().observe(this, Observer { weatherList ->
            Log.e("RESOURCE", " $weatherList")

            if (weatherList.data != null) {
                progressBar.visibility = View.GONE

                weatherAdapter.setList(weatherList.data)

            } else {
                progressBar.visibility = View.GONE
                if (weatherList.message != null) {
                    Error(this, weatherList.message).showError()
                }

            }

        })

    }
}