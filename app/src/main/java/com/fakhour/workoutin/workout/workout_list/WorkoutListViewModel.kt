package com.fakhour.workoutin.workout.workout_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.fakhour.workoutin.common.repository.WorkoutRepository
import com.fakhour.workoutin.workout.entities.Workout
import com.fakhour.workoutin.workout.entities.WorkoutSection
import java.util.*
import kotlin.collections.ArrayList

class WorkoutListViewModel:ViewModel() {

    private val workoutRepository = WorkoutRepository.get()
    private val workoutSectionIdLiveData = MutableLiveData<UUID>()

    val workoutListLiveData: LiveData<ArrayList<Workout>> =
        Transformations.switchMap(workoutSectionIdLiveData) { workoutSectionId ->
            workoutRepository.getWorkoutList(workoutSectionId)
        }

    fun loadWorkoutList(workoutSectionId: UUID) {
        workoutSectionIdLiveData.value = workoutSectionId
    }



}
