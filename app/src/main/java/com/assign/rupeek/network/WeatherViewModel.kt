package com.assign.rupeek.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assign.rupeek.client.APIClient
import com.assign.rupeek.model.DataW
import com.assign.rupeek.model.Result
import com.assign.rupeek.urlInterface.APIInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherViewModel : ViewModel() {
    private lateinit var sources: MutableLiveData<Resource<List<DataW>>>
    private lateinit var compositeDisposable: CompositeDisposable
    val movie = MutableLiveData<DataW>()

    fun getWeather(): LiveData<Resource<List<DataW>>> {
        sources = MutableLiveData()
        compositeDisposable = CompositeDisposable()
        loadSources()
        return sources
    }

    private fun loadSources() {
        val apiService = APIClient.getClient().create(APIInterface::class.java)
        val call = apiService.getWeather()

        compositeDisposable.add(
            call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, this::handleError)
        )

    }

    private fun handleResponse(result: Result) {
        Log.e("JBN|SBH ", "res " + result)

        sources.postValue(Resource.success(result.data))
    }

    private fun handleError(error: Throwable) {
        Log.e("JBN|SBH ", " " + error)
        sources.postValue(Resource.error(error, null))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

//    fun sendMovie(movie: Movie) {
//        this.movie.value = movie
//    }
}

