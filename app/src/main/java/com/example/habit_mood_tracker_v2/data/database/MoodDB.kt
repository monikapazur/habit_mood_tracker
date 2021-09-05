package com.example.habit_mood_tracker_v2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.habit_mood_tracker_v2.data.models.Mood
import com.example.habit_mood_tracker_v2.logic.dao.MoodDao

@Database(entities = [Mood::class], version = 1, exportSchema = false)
abstract class MoodDB : RoomDatabase() {

    abstract fun moodDao(): MoodDao

    companion object {
        @Volatile
        private var INSTANCE: MoodDB? = null

        fun getMoodDB(context: Context): MoodDB {
            val tempInstance: MoodDB? = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoodDB::class.java,
                    "mood_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}