package com.example.smoking.ui.theme.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

class HomeViewModel : ViewModel() {
    private val d = LocalDate.now()
    private val r = formTime(d)
    private val _selectedDay = mutableStateOf(Time(d, r[0], r[1], "Today", "", ""))
    val selectedDay: State<Time> = _selectedDay
    val auth = Firebase.auth
    val curUser = auth.currentUser

    fun retrieveCounterFromFirestore() {
        val selectedDateString = selectedDay.value.localDate.format(DateTimeFormatter.ISO_DATE)
        var userId:String = ""
        curUser?.run {
            userId = uid
        }
        viewModelScope.launch {
            val startOfDay = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant())
            val endOfDay = Date.from(d.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant().minusMillis(1))

            // Perform the query
            }
        }
}




private fun formTime(d: LocalDate): List<String> {
    val formatDay = d.format(DateTimeFormatter.ofPattern("dd EEE"))
    return formatDay.split(" ")
}
data class Time(val localDate: LocalDate, val day: String, val dayOfWeek:String, val label: String, val counter: Any?, val streak: String)