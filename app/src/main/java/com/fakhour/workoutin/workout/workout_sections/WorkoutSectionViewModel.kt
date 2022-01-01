package com.fakhour.workoutin.workout.workout_sections

import androidx.lifecycle.ViewModel
import com.fakhour.workoutin.common.repository.WorkoutRepository
import com.fakhour.workoutin.workout.entities.WorkoutSection

class WorkoutSectionViewModel:ViewModel() {

    private val workoutRepository = WorkoutRepository.get()
    val workoutSectionsLiveData = workoutRepository.getAllWorkoutSections()

    fun addWorkoutSection(workoutSection: WorkoutSection) {
        workoutRepository.addWorkoutSection(workoutSection)
    }

}
