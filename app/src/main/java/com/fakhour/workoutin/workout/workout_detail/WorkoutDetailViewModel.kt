package com.fakhour.workoutin.workout.workout_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.fakhour.workoutin.common.repository.WorkoutRepository
import com.fakhour.workoutin.workout.entities.Workout
import java.util.*

class WorkoutDetailViewModel:ViewModel() {

    private val workoutRepository = WorkoutRepository.get()
    private val workoutIdLiveData = MutableLiveData<Int>()

    val workoutLiveData: LiveData<Workout> =
        Transformations.switchMap(workoutIdLiveData) { workoutId ->
            workoutRepository.getWorkoutDetail(workoutId)
        }

    fun loadWorkout(workoutId: Int) {
        workoutIdLiveData.value = workoutId
    }



}
