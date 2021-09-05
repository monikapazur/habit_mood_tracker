package com.example.habit_mood_tracker_v2.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.habit_mood_tracker_v2.data.database.MoodDB
import com.example.habit_mood_tracker_v2.data.models.Mood
import com.example.habit_mood_tracker_v2.logic.repository.MoodRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoodViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MoodRepo
    val getAllMoods: LiveData<List<Mood>>

    init {
        val moodDao = MoodDB.getMoodDB(application).moodDao()
        repository = MoodRepo(moodDao)

        getAllMoods = repository.getAllMoods
    }

    fun addMood(mood: Mood) {
        viewModelScope.launch {
            Dispatchers.IO
            repository.addMood(mood)
        }
    }

    fun updateMood(mood: Mood) {
        viewModelScope.launch {
            Dispatchers.IO
            repository.updateMood(mood)
        }
    }

    fun deleteMood(mood: Mood) {
        viewModelScope.launch {
            Dispatchers.IO
            repository.deleteMood(mood)
        }
    }

    fun deleteAllMoods() {
        viewModelScope.launch {
            Dispatchers.IO
            repository.deleteAllMoods()
        }
    }
}