package com.example.habit_mood_tracker_v2.ui.fragments.addmood

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habit_mood_tracker_v2.R
import com.example.habit_mood_tracker_v2.data.models.Mood
import com.example.habit_mood_tracker_v2.ui.viewmodels.MoodViewModel
import kotlinx.android.synthetic.main.fragment_add_mood.*
import java.text.SimpleDateFormat
import java.util.*

class AddMoodFragment : Fragment(R.layout.fragment_add_mood) {
    private var moodName = ""
    private var moodDate = ""

    private lateinit var moodViewModel: MoodViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moodViewModel = ViewModelProvider(this).get(MoodViewModel::class.java)

        confirmMoodButton.setOnClickListener {
            addMoodToDB()
        }
    }

    private fun addMoodToDB() {

        if (goodMoodCheckBox.isChecked) {
            moodName = "Good"

            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val date = sdf.format(Date())
            moodDate = date
        }
        if (badMoodCheckBox.isChecked) {
            moodName = "Bad"
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val date = sdf.format(Date())
            moodDate = date
        }
        if (goodMoodCheckBox.isChecked && badMoodCheckBox.isChecked) {
            Toast.makeText(context, "Please choose only one", Toast.LENGTH_SHORT).show()
        } else if (!(moodName.isEmpty() || moodDate.isEmpty())) {
            val mood = Mood(0, moodName, moodDate)
            moodViewModel.addMood(mood)
            Toast.makeText(context, "Mood added :)", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addMoodFragment_to_moodFragment)
        } else {
            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }
    }

}