package com.example.habit_mood_tracker_v2.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.habit_mood_tracker_v2.data.database.HabitDB
import com.example.habit_mood_tracker_v2.data.models.Habit
import com.example.habit_mood_tracker_v2.logic.repository.HabitRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HabitViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: HabitRepo
    val getAllHabits: LiveData<List<Habit>>

    init {
        val habitDao = HabitDB.getDB(application).habitDao()
        repository = HabitRepo(habitDao)

        getAllHabits = repository.getAllHabits
    }

    fun addHabit(habit: Habit) {
        viewModelScope.launch {
            Dispatchers.IO
            repository.addHabit(habit)
        }
    }

    fun updateHabit(habit: Habit) {
        viewModelScope.launch {
            Dispatchers.IO
            repository.updateHabit(habit)
        }
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch {
            Dispatchers.IO
            repository.deleteHabit(habit)
        }
    }

    fun deleteAllHabits() {
        viewModelScope.launch {
            Dispatchers.IO
            repository.deleteAllHabits()
        }
    }
}