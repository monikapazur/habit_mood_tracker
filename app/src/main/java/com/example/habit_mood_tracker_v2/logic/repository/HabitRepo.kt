package com.example.habit_mood_tracker_v2.logic.repository

import androidx.lifecycle.LiveData
import com.example.habit_mood_tracker_v2.data.models.Habit
import com.example.habit_mood_tracker_v2.logic.dao.HabitDao

class HabitRepo(private val habitDao: HabitDao) {

    val getAllHabits: LiveData<List<Habit>> = habitDao.getAllHabits()

    suspend fun addHabit(habit: Habit) {
        habitDao.addHabit(habit)
    }

    suspend fun updateHabit(habit: Habit) {
        habitDao.updateHabit(habit)
    }

    suspend fun deleteHabit(habit: Habit) {
        habitDao.deleteHabit(habit)
    }

    suspend fun deleteAllHabits() {
        habitDao.deleteAllHabits()
    }
}