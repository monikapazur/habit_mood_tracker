package com.example.habit_mood_tracker_v2.ui.fragments.moodlist.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.habit_mood_tracker_v2.R
import com.example.habit_mood_tracker_v2.data.models.Mood
import com.example.habit_mood_tracker_v2.ui.fragments.moodlist.MoodListFragmentDirections
import kotlinx.android.synthetic.main.recycle_mood_item.view.*

class MoodListAdapter : RecyclerView.Adapter<MoodListAdapter.MyViewHolder>() {

    var moodList = emptyList<Mood>()
    val TAG = "MoodListAdapter"

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.moodCardView.setOnClickListener {
                val position = adapterPosition
                Log.d(TAG, "Item clicked at: $position ")// to
                Log.d(
                    TAG,
                    "ID ${moodList[position].id} "
                )

                val action =
                    MoodListFragmentDirections.actionMoodFragmentToUpdateMoodItemFragment(moodList[position])
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoodListAdapter.MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycle_mood_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MoodListAdapter.MyViewHolder, position: Int) {
        val currentMood = moodList[position]

        holder.itemView.moodNameTextView.text = "${currentMood.name}"
        holder.itemView.dateTextView.text = "${currentMood.addDate}"

    }

    override fun getItemCount(): Int {
        return moodList.size
    }

    fun setData(mood: List<Mood>) {
        this.moodList = mood
        notifyDataSetChanged()


    }
}