package com.assign.rupeek.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assign.rupeek.R
import com.assign.rupeek.model.DataW
import kotlinx.android.synthetic.main.adapter_weather.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class WeatherAdapter(val context: Context, var weatherList: List<DataW>) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    lateinit var onItemClick: ((DataW) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_weather, parent, false)


        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(weatherList[position])

    }

    fun setList(data: List<DataW>) {
        weatherList = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                onItemClick.invoke(weatherList[adapterPosition])
            }
        }

        fun setData(dataW: DataW) {

            itemView.txtTemp.text = dataW.temp + context.getString(R.string.temp_unit)
            itemView.txtRain.text = dataW.rain + context.getString(R.string.rain_unit)
            itemView.txtWind.text = dataW.wind + context.getString(R.string.wind_unit)

            val d = Date(dataW.time * 1000)
            val df: DateFormat = SimpleDateFormat("MMM dd yyyy")
            itemView.txtDate.text = df.format(d)

        }

    }
}