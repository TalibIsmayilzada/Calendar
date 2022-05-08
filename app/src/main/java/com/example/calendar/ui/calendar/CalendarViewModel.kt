package com.example.calendar.ui.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calendar.data.API
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CalendarViewModel : ViewModel() {

    private var date = 0


    val data = MutableStateFlow<List<CalendarItem>>(listOf())

    fun getData() {
        viewModelScope.launch {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()

            val retrofit = Retrofit.Builder().baseUrl("http://192.168.1.3:3000/")
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
            val api: API = retrofit.create(API::class.java)

            try {
                val response = api.getCalendar(date)
                data.emit(response)
            }catch (e: Exception){

            }
        }
    }

    fun next() {
        date++
        getData()
    }

    fun prev() {
        date--
        getData()
    }
}