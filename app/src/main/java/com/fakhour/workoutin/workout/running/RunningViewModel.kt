package com.fakhour.workoutin.workout.running

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhour.workoutin.common.api.authentication.FIRST_GRANT_TYPE
import com.fakhour.workoutin.common.repository.WorkoutRepository
import com.fakhour.workoutin.workout.entities.Athlete
import com.fakhour.workoutin.workout.entities.RunActivity
import com.fakhour.workoutin.workout.entities.RunningToken
import kotlinx.coroutines.launch
import java.util.*

class RunningViewModel: ViewModel()  {
    private val workoutRepository = WorkoutRepository.get()
    val myResponseAthlete: MutableLiveData<Athlete?> = MutableLiveData()
    val myResponseRunActivityPost: MutableLiveData<RunActivity?> = MutableLiveData()
    val myResponseRunActivity: MutableLiveData<RunActivity?> = MutableLiveData()
    val myResponseToken: MutableLiveData<RunningToken?> = MutableLiveData()

    fun getAthletes() {
        viewModelScope.launch {
            val response = workoutRepository.getAthlete()
            myResponseAthlete.value = response
        }
    }

    fun createActivity(name: String, type: String, start_date_local: Date, elapsed_time: Int, description: String, distance: Float){
        viewModelScope.launch {
            val response =workoutRepository.createRunningActivity(name,type,start_date_local,elapsed_time,description,distance)
            myResponseRunActivityPost.value = response
        }
    }

    fun getRunningActivity(id:Long) {
        viewModelScope.launch {
            val response = workoutRepository.getRunningActivity(id)
            myResponseRunActivity.value = response
        }
    }

    fun authenticateFirstTime(code:String){
        viewModelScope.launch {
            val response =workoutRepository.getToken(code, FIRST_GRANT_TYPE)
            myResponseToken.value = response
        }
    }
}