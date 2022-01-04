package com.fakhour.workoutin.workout.workout_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.fakhour.workoutin.common.repository.WorkoutRepository
import com.fakhour.workoutin.workout.entities.Workout

class WorkoutListViewModel:ViewModel() {

    private val workoutRepository = WorkoutRepository.get()
    private val workoutSectionIdLiveData = MutableLiveData<String>()

    val workoutListLiveData: LiveData<List<Workout>> =
        Transformations.switchMap(workoutSectionIdLiveData) { workoutSectionId ->
            workoutRepository.getSectionWorkouts(workoutSectionId)
        }

    fun loadWorkoutList(workoutSectionId: String) {
        workoutSectionIdLiveData.value = workoutSectionId
    }

    fun addWorkoutDetail(workoutDetail: Workout) {
        workoutRepository.addWorkoutDetail(workoutDetail)
    }

    fun deleteAllWorkouts(){
        workoutRepository.deleteAllWorkouts()
    }



}
