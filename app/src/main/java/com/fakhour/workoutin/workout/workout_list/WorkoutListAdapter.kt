package com.fakhour.workoutin.workout.workout_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fakhour.workoutin.databinding.WorkoutItemBinding
import com.fakhour.workoutin.workout.entities.Workout


class WorkoutListAdapter(val mContext: Context, var workoutList: ArrayList<Workout>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var _binding: WorkoutItemBinding?= null
    private val binding get() = _binding!!

    private var onItemClickListener: ((position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        _binding = WorkoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (workoutList != null) {
            val workout = workoutList!![position]
            val itemHolder = holder as ItemViewHolder

            itemHolder.itemTitle.text=workout.title
            itemHolder.itemRepetition.text="30 Sec"
            itemHolder.itemImage.setImageResource(workout.firstImg)


            itemHolder.itemView.setOnClickListener {
                onItemClickListener?.invoke(position)
            }

        }
    }

    override fun getItemCount(): Int {
        return workoutList?.size ?: 0
    }

    fun update(_workoutList: List<Workout>?) {

        this.workoutList = ArrayList(_workoutList)

        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: ((position: Int) -> Unit)?) {
        this.onItemClickListener = onItemClickListener
    }

    class ItemViewHolder(binding: WorkoutItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemTitle: TextView = binding.workoutTitle
        val itemRepetition: TextView = binding.workoutRepetition
        val itemImage: ImageView = binding.workoutSectionImg
    }
}
