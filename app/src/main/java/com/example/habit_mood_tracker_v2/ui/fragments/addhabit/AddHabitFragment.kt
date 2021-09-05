package com.example.habit_mood_tracker_v2.ui.fragments.addhabit


import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habit_mood_tracker_v2.R
import com.example.habit_mood_tracker_v2.data.models.Habit
import com.example.habit_mood_tracker_v2.ui.viewmodels.HabitViewModel
import com.example.habit_mood_tracker_v2.utils.Calc
import kotlinx.android.synthetic.main.fragment_add_habit.*
import java.util.*

class AddHabitFragment : Fragment(R.layout.fragment_add_habit),
    DatePickerDialog.OnDateSetListener {
    private var habitName = ""
    private var timeStamp = ""
    private var day = 0
    private var month = 0
    private var year = 0
    private var cleanDate = ""

    private lateinit var habitViewModel: HabitViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        habitViewModel = ViewModelProvider(this).get(HabitViewModel::class.java)

        confirmButton.setOnClickListener {
            addHabitToDB()
        }
        pickDate()
    }

    private fun pickDate() {
        selectDateButton.setOnClickListener {
            getDateCalendar()
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }
    }

    private fun addHabitToDB() {
        habitName = habitNameEditText.text.toString()
        timeStamp = "$cleanDate"

        if (!(habitName.isEmpty() || timeStamp.isEmpty())) {
            val habit = Habit(0, habitName, timeStamp)
            habitViewModel.addHabit(habit)
            Toast.makeText(context, "Habit create! Good luck :)", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addHabitFragment_to_habitListFragment)
        } else {
            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDateSet(view: DatePicker?, yearX: Int, monthX: Int, dayOfMonthX: Int) {
        cleanDate = Calc.cleanDate(dayOfMonthX, monthX, yearX)
        startDateSelected.text = cleanDate
    }

    private fun getDateCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }


}