package com.example.calendar.ui.calendar

data class CalendarItem(
    val building: String? = null,
    val date: String? = null,
    val day: String? = null,
    val description: String? = null,
    val fio: String? = null,
    val room: String? = null,
    val timeBegin: String? = null,
    val timeEnd: String? = null,
    val typeWorkName: String? = null,
    val weekNumber: Int? = null,
    val weekTypeName: String? = null
)