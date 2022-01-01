package com.fakhour.workoutin.workout.workout_sections

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fakhour.workoutin.databinding.WorkoutSectionItemBinding
import com.fakhour.workoutin.workout.entities.WorkoutSection


class WorkoutSectionAdapter(val mContext: Context, var workoutSectionList: ArrayList<WorkoutSection>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var _binding: WorkoutSectionItemBinding?= null
    private val binding get() = _binding!!

    private var onItemClickListener: ((position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        _binding = WorkoutSectionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (workoutSectionList != null) {
            val workoutSection = workoutSectionList!![position]
            val itemHolder = holder as ItemViewHolder

            itemHolder.itemTitle.text=workoutSection.sectionTitle
            itemHolder.itemDescription.text=workoutSection.sectionDescription
            itemHolder.itemImage.setImageResource(workoutSection.sectionImage)

            itemHolder.itemView.setOnClickListener {
                onItemClickListener?.invoke(position)
            }

        }
    }

    override fun getItemCount(): Int {
        return workoutSectionList?.size ?: 0
    }

    fun update(_workoutSectionList: ArrayList<WorkoutSection>?) {

        this.workoutSectionList = _workoutSectionList

        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: ((position: Int) -> Unit)?) {
        this.onItemClickListener = onItemClickListener
    }

    class ItemViewHolder(binding: WorkoutSectionItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemTitle: TextView = binding.workoutSectionTitle
        val itemDescription: TextView = binding.workoutSectionDescription
        val itemImage: ImageView = binding.workoutSectionImg
    }
}
