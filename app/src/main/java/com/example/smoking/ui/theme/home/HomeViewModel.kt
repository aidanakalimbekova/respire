package com.example.smoking.ui.theme.home

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HomeViewModel : ViewModel() {
    private val d = LocalDate.now()
    private val r = formTime(d)
    private val _selectedDay = mutableStateOf(Time(d, r[0], r[1], "Today"))
    val selectedDay: State<Time> = _selectedDay

    fun selectYesterday() {
       val yy = selectedDay.value.localDate.minusDays(1)
        val rr = formTime(yy)
        _selectedDay.value = Time(yy, rr[0], rr[1], "Yesterday")
    }

    fun selectTomorrow() {
        val ttt = selectedDay.value.localDate.plusDays(1)
        val rr = formTime(ttt)
        _selectedDay.value = Time(ttt, rr[0], rr[1], "Today")
    }
}

private fun formTime(d: LocalDate): List<String> {
    val formatDay = d.format(DateTimeFormatter.ofPattern("dd EEE"))
    return formatDay.split(" ")
}
data class Time(val localDate: LocalDate, val day: String, val dayOfWeek:String, val label: String )