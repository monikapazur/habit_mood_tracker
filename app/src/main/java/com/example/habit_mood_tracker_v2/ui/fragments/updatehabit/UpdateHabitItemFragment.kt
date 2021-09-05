package com.example.habit_mood_tracker_v2.ui.fragments.updatehabit

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.habit_mood_tracker_v2.R
import com.example.habit_mood_tracker_v2.data.models.Habit
import com.example.habit_mood_tracker_v2.ui.viewmodels.HabitViewModel
import com.example.habit_mood_tracker_v2.utils.Calc
import kotlinx.android.synthetic.main.fragment_update_habit_item.*
import java.util.*

class UpdateHabitItemFragment : Fragment(R.layout.fragment_update_habit_item),
    DatePickerDialog.OnDateSetListener {

    private var habitName = ""
    private var timeStamp = ""
    private var day = 0
    private var month = 0
    private var year = 0
    private var cleanDate = ""
    private lateinit var habitViewModel: HabitViewModel

    private val args by navArgs<UpdateHabitItemFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        habitViewModel = ViewModelProvider(this).get(HabitViewModel::class.java)

        habitNameUpdate.setText(args.selectedHabit.name)

        pickDate()

        confirmButtonUpdate.setOnClickListener {
            updateHabit()
        }

        setHasOptionsMenu(true)
    }

    private fun updateHabit() {
        habitName = habitNameUpdate.text.toString()
        timeStamp = "$cleanDate"
        if (!(habitName.isEmpty() || timeStamp.isEmpty())) {
            val habit = Habit(args.selectedHabit.id, habitName, timeStamp)
            habitViewModel.updateHabit(habit)
            Toast.makeText(context, "Habit updated!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateHabitItemFragment_to_habitListFragment)
        } else {
            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun pickDate() {
        selectDateButtonUpdate.setOnClickListener {
            getDateCalendar()
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }

    }

    override fun onDateSet(view: DatePicker?, yearX: Int, monthX: Int, dayOfMonthX: Int) {
        cleanDate = Calc.cleanDate(dayOfMonthX, monthX, yearX)
        startDateSelectedUpdate.text = cleanDate
    }

    private fun getDateCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.single_item_menu, menu)
    }

    private fun deleteHabitNow(habit: Habit) {
        habitViewModel.deleteHabit(habit)
        Toast.makeText(context, "Habit deleted", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_updateHabitItemFragment_to_habitListFragment)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteItem -> deleteHabitNow(args.selectedHabit)
        }
        return super.onOptionsItemSelected(item)
    }

}