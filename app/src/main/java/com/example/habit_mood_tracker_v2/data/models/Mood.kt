package com.example.habit_mood_tracker_v2.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "mood_table")
data class Mood(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val addDate: String
) : Parcelable {
}