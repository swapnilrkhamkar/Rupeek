package com.assign.rupeek.urlInterface

import com.assign.rupeek.model.Result
import io.reactivex.Single
import retrofit2.http.GET

interface APIInterface {

    @GET("5d3a99ed2f0000bac16ec13a")
    fun getWeather(): Single<Result>
}


