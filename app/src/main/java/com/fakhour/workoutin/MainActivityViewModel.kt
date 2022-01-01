package com.fakhour.workoutin

import androidx.lifecycle.ViewModel
import com.fakhour.workoutin.common.repository.WorkoutRepository
import com.fakhour.workoutin.workout.entities.WorkoutSection
import com.rbddevs.splashy.Splashy

class MainActivityViewModel:ViewModel() {

    private val workoutRepository = WorkoutRepository.get()

    fun setSplashy(activity: MainActivity){


    }
}
