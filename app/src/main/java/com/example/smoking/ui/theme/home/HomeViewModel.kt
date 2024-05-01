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

    private val db = Firebase.firestore
//    private val userCollection = db.collection("use")
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
            db.collection("users").document(userId).collection("smokeFreeDays")
                .whereGreaterThanOrEqualTo("date", startOfDay)
                .whereLessThan("date", endOfDay)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    if (querySnapshot.isEmpty) {
                        println("No documents found for yesterday.")
                    } else {
                        val temp = querySnapshot.documents[0].get("cigarettesSmoked") as? Long
                        println("$temp")

                        _selectedDay.value = selectedDay.value.copy(counter = temp)

                    }
                }
                .addOnFailureListener { exception ->
                    println("Error getting documents: $exception")
                }
        }
        }

    fun getStreak(){
        var userId:String = ""
        curUser?.run {
            userId = uid
        }
        viewModelScope.launch {
            val temp = db.collection("users").document(userId).get().await()
            val s = temp.data?.get("streak") as? String ?: "0"
            _selectedDay.value = selectedDay.value.copy(streak = s)
        }
    }
    fun updateCounterForSelectedDay(newValue: Long) {
        val selectedDateString = selectedDay.value.localDate.format(DateTimeFormatter.ISO_DATE)
        var userId:String = ""
        curUser?.run {
            userId = uid
        }
        viewModelScope.launch {
            // Perform the query
            db.collection("users").document(userId).collection("smokeFreeDays")
                .document(selectedDateString).update("cigarettesSmoked", newValue)
                .addOnSuccessListener {
                    _selectedDay.value = selectedDay.value.copy(counter = newValue)
                    println("Success")
                }
                .addOnFailureListener { exception ->
                    println("Error getting documents: $exception")
                }
        }
    }
}

private fun formTime(d: LocalDate): List<String> {
    val formatDay = d.format(DateTimeFormatter.ofPattern("dd EEE"))
    return formatDay.split(" ")
}
data class Time(val localDate: LocalDate, val day: String, val dayOfWeek:String, val label: String, val counter: Any?, val streak: String)