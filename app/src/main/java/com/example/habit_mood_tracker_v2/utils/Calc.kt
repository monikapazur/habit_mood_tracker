package com.example.habit_mood_tracker_v2.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object Calc {

    fun calcTimeBetweenDates(startDate: String): String {
        val endDate = timeStampToString(System.currentTimeMillis())
        val sdf = SimpleDateFormat("dd/MM/yyyy")

        val date1 = sdf.parse(startDate)
        val date2 = sdf.parse(endDate)

        var isNegative = false

        var difference = date2.time - date1.time

        if (difference < 0) {
            difference = -(difference)
            isNegative = true
        }

        val minutes = difference / 60 / 1000
        val hours = difference / 60 / 1000 / 60
        val days = (difference / 60 / 1000 / 60) / 24
        val months = (difference / 60 / 1000 / 60) / 24 / (356 / 12)
        val years = difference / 60 / 1000 / 60 / 24 / 356

        if (isNegative) {
            return when {
                minutes < 240 -> "Starts in $minutes minutes"
                hours < 48 -> "Starts in $hours hours"
                days < 61 -> "Starts in $days days"
                months < 24 -> "Starts in $months months"
                else -> "Starts in $years years"
            }
        }
        return when {
            minutes < 240 -> "$minutes minutes ago"
            hours < 48 -> "$hours hours ago"
            days < 61 -> "$days days ago"
            months < 24 -> "$months months ago"
            else -> "$years years ago"
        }

    }

    private fun timeStampToString(timeStamp: Long): String {
        val stamp = Timestamp(timeStamp)
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val date = sdf.format((Date(stamp.time)))

        return date.toString()
    }

    fun cleanDate(_day: Int, _month: Int, _year: Int): String {
        var day = _day.toString()
        var month = _month.toString()
        if (_day < 10) {
            day = "0$_day"
        }
        if (_month < 9) {
            month = "0${_month + 1}"
        }
        return "$day/$month/$_year"

    }
}