package com.example.habit_mood_tracker_v2.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.habit_mood_tracker_v2.data.models.Mood

@Dao
interface MoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMood(mood: Mood)

    @Update
    suspend fun updateMood(mood: Mood)

    @Delete
    suspend fun deleteMood(mood: Mood)

    @Query("SELECT * FROM mood_table ORDER BY id DESC")
    fun getAllMoods(): LiveData<List<Mood>>

    @Query("DELETE FROM mood_table")
    suspend fun deleteAllMoods()
}