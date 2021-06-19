package com.example.geckoweatherapp.city

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.geckoweatherapp.WebApi
import com.example.geckoweatherapp.WebApiFactory
import com.example.geckoweatherapp.home.Bookmark
import kotlinx.coroutines.launch
import java.io.IOException

class CityViewModel(application: Application) : AndroidViewModel(application) {

    private val webApi: WebApi by lazy { WebApiFactory.webApi }

    private val _weatherLiveData = MutableLiveData<WeatherForLocation>()
    val weatherLiveData: LiveData<WeatherForLocation> = _weatherLiveData

    fun getWeatherForLocation(bookmark: Bookmark) {
        if (_weatherLiveData.value != null) return

        viewModelScope.launch {
            _weatherLiveData.value = try {
                webApi.getWeatherForLocation(
                    bookmark.latitude, bookmark.longitude
                )
            } catch (e: IOException) {
                Log.e("CityViewModel", "getWeatherForLocation", e)
                null
            } catch (e: RuntimeException) {
                Log.e("CityViewModel", "getWeatherForLocation", e)
                null
            }
        }
    }

}