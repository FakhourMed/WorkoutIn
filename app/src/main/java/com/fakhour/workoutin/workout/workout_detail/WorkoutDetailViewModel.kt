package com.fakhour.workoutin.workout.workout_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.fakhour.workoutin.common.repository.WorkoutRepository
import com.fakhour.workoutin.workout.entities.Workout
import com.fakhour.workoutin.workout.entities.WorkoutSection
import java.util.*
import kotlin.collections.ArrayList

class WorkoutDetailViewModel:ViewModel() {

    private val workoutRepository = WorkoutRepository.get()
    private val workoutIdLiveData = MutableLiveData<UUID>()

    val workoutLiveData: LiveData<Workout> =
        Transformations.switchMap(workoutIdLiveData) { workoutId ->
            workoutRepository.getWorkout(workoutId)
        }

    fun loadWorkout(workoutId: UUID) {
        workoutIdLiveData.value = workoutId
    }



}
