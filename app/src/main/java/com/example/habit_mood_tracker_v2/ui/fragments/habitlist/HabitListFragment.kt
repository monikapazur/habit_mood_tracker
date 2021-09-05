package com.example.habit_mood_tracker_v2.ui.fragments.habitlist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habit_mood_tracker_v2.R
import com.example.habit_mood_tracker_v2.data.models.Habit
import com.example.habit_mood_tracker_v2.ui.fragments.habitlist.adapter.HabitListAdapter
import com.example.habit_mood_tracker_v2.ui.viewmodels.HabitViewModel
import kotlinx.android.synthetic.main.fragment_habit_list.*

class HabitListFragment : Fragment(R.layout.fragment_habit_list) {
    private lateinit var habitList: List<Habit>

    private lateinit var habitViewModel: HabitViewModel
    private lateinit var adapter: HabitListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HabitListAdapter()
        recyclerHabit.adapter = adapter
        recyclerHabit.layoutManager = LinearLayoutManager(context)

        habitViewModel = ViewModelProvider(this).get(HabitViewModel::class.java)

        habitViewModel.getAllHabits.observe(
            viewLifecycleOwner,
            Observer {
                adapter.setData(it)
                habitList = it

                if (it.isEmpty()) {
                    recyclerHabit.visibility =
                        View.GONE
                    emptyList.visibility = View.VISIBLE
                } else {
                    recyclerHabit.visibility = View.VISIBLE
                    emptyList.visibility = View.GONE
                }

            })
        setHasOptionsMenu(true)
        swipeRefresh.setOnRefreshListener {
            adapter.setData(habitList)
            swipeRefresh.isRefreshing = false
        }

        addButton.setOnClickListener {
            findNavController().navigate(R.id.action_habitListFragment_to_addHabitFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_delete -> habitViewModel.deleteAllHabits()

        }
        return super.onOptionsItemSelected(item)
    }
}