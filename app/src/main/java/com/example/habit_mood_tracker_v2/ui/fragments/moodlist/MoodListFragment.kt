package com.example.habit_mood_tracker_v2.ui.fragments.moodlist

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
import com.example.habit_mood_tracker_v2.data.models.Mood
import com.example.habit_mood_tracker_v2.ui.fragments.moodlist.adapter.MoodListAdapter
import com.example.habit_mood_tracker_v2.ui.viewmodels.MoodViewModel
import kotlinx.android.synthetic.main.fragment_habit_list.swipeRefresh
import kotlinx.android.synthetic.main.fragment_mood_list.*

class MoodListFragment : Fragment(R.layout.fragment_mood_list) {
    private lateinit var moodList: List<Mood>

    private lateinit var moodViewModel: MoodViewModel
    private lateinit var adapter: MoodListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MoodListAdapter()
        recyclerMood.adapter = adapter
        recyclerMood.layoutManager = LinearLayoutManager(context)

        moodViewModel = ViewModelProvider(this).get(MoodViewModel::class.java)

        moodViewModel.getAllMoods.observe(
            viewLifecycleOwner,
            Observer {
                adapter.setData(it)
                moodList = it

                if (it.isEmpty()) {
                    recyclerMood.visibility =
                        View.GONE
                    emptyMoodList.visibility = View.VISIBLE
                } else {
                    recyclerMood.visibility = View.VISIBLE
                    emptyMoodList.visibility = View.GONE
                }

            })
        setHasOptionsMenu(true)
        swipeRefresh.setOnRefreshListener {
            adapter.setData(moodList)
            swipeRefresh.isRefreshing = false
        }

        addMoodButton.setOnClickListener {
            findNavController().navigate(R.id.action_moodFragment_to_addMoodFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_delete -> moodViewModel.deleteAllMoods()

        }
        return super.onOptionsItemSelected(item)
    }
}