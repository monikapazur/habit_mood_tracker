package com.example.habit_mood_tracker_v2.ui.fragments.updatemood

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.habit_mood_tracker_v2.R
import com.example.habit_mood_tracker_v2.data.models.Mood
import com.example.habit_mood_tracker_v2.ui.viewmodels.MoodViewModel
import kotlinx.android.synthetic.main.fragment_update_mood_item.*
import java.text.SimpleDateFormat
import java.util.*

class UpdateMoodItemFragment : Fragment(R.layout.fragment_update_mood_item) {
    private var moodName = ""
    private var moodDate = ""

    private lateinit var moodViewModel: MoodViewModel

    private val args by navArgs<UpdateMoodItemFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        moodViewModel = ViewModelProvider(this).get(MoodViewModel::class.java)

        confirmMoodButtonUpdate.setOnClickListener {
            updateMood()
        }

        setHasOptionsMenu(true)
    }

    private fun updateMood() {
        if (goodMoodCheckBoxUpdate.isChecked) {
            moodName = "Good"
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val date = sdf.format(Date())
            moodDate = date
        }
        if (badMoodCheckBoxUpdate.isChecked) {
            moodName = "Bad"
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val date = sdf.format(Date())
            moodDate = date
        }
        if (goodMoodCheckBoxUpdate.isChecked && badMoodCheckBoxUpdate.isChecked) {
            Toast.makeText(context, "Please choose only one", Toast.LENGTH_SHORT).show()
        } else if (!(moodName.isEmpty() || moodDate.isEmpty())) {
            val mood = Mood(args.selectedMood.id, moodName, moodDate)
            moodViewModel.updateMood(mood)
            Toast.makeText(context, "Mood updated!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateMoodItemFragment_to_moodFragment)
        } else {
            Toast.makeText(context, "Please choose one", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.single_item_menu, menu)
    }

    private fun deleteMoodNow(mood: Mood) {
        moodViewModel.deleteMood(mood)
        Toast.makeText(context, "Mood deleted", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_updateMoodItemFragment_to_moodFragment)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteItem -> deleteMoodNow(args.selectedMood)
        }
        return super.onOptionsItemSelected(item)
    }

}