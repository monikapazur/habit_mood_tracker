package com.example.habit_mood_tracker_v2.logic.repository

import androidx.lifecycle.LiveData
import com.example.habit_mood_tracker_v2.data.models.Mood
import com.example.habit_mood_tracker_v2.logic.dao.MoodDao

class MoodRepo(private val moodDao: MoodDao) {

    val getAllMoods: LiveData<List<Mood>> = moodDao.getAllMoods()

    suspend fun addMood(mood: Mood) {
        moodDao.addMood(mood)
    }

    suspend fun updateMood(mood: Mood) {
        moodDao.updateMood(mood)
    }

    suspend fun deleteMood(mood: Mood) {
        moodDao.deleteMood(mood)
    }

    suspend fun deleteAllMoods() {
        moodDao.deleteAllMoods()
    }
}