package com.example.habit_mood_tracker_v2.ui.fragments.habitlist.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.habit_mood_tracker_v2.R
import com.example.habit_mood_tracker_v2.data.models.Habit
import com.example.habit_mood_tracker_v2.ui.fragments.habitlist.HabitListFragmentDirections
import com.example.habit_mood_tracker_v2.utils.Calc
import kotlinx.android.synthetic.main.recycle_habit_item.view.*

class HabitListAdapter : RecyclerView.Adapter<HabitListAdapter.MyViewHolder>() {

    var habitList = emptyList<Habit>()
    val TAG = "HabitListAdapter"

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.habitCardView.setOnClickListener {
                val position = adapterPosition
                Log.d(TAG, "Item clicked at: $position ")// to
                Log.d(
                    TAG,
                    "ID ${habitList[position].id} "
                )

                val action =
                    HabitListFragmentDirections.actionHabitListFragmentToUpdateHabitItemFragment(
                        habitList[position]
                    )
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HabitListAdapter.MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycle_habit_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HabitListAdapter.MyViewHolder, position: Int) {
        val currentHabit = habitList[position]

        holder.itemView.habitNameTextView.text = "${currentHabit.name}"
        holder.itemView.timeTextView.text = Calc.calcTimeBetweenDates(currentHabit.startTime)
        holder.itemView.startTimeTextView.text = "Start habit: ${currentHabit.startTime}"
    }

    override fun getItemCount(): Int {
        return habitList.size
    }

    fun setData(habit: List<Habit>) {
        this.habitList = habit
        notifyDataSetChanged()

    }
}