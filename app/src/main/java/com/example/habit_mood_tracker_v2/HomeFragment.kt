package com.example.habit_mood_tracker_v2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val date = dayOfMonth.toString() + "/" + (month + 1) + "/" + year
            dateView.text = date
        }

    }

}