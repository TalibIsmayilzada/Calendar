package com.example.calendar.data

import com.example.calendar.ui.calendar.CalendarItem
import retrofit2.http.GET
import retrofit2.http.Path

interface API {

    @GET("calendar/{date}")
    suspend fun getCalendar(@Path("date") date: Int): List<CalendarItem>
}