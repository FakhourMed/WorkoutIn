package com.fakhour.workoutin.workout.running

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhour.workoutin.common.api.authentication.FIRST_GRANT_TYPE
import com.fakhour.workoutin.common.repository.WorkoutRepository
import com.fakhour.workoutin.workout.entities.ActivityType
import com.fakhour.workoutin.workout.running.entities.Athlete
import com.fakhour.workoutin.workout.entities.RunActivity
import com.fakhour.workoutin.workout.running.entities.RunningToken
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

class RunningViewModel: ViewModel()  {
    private val workoutRepository = WorkoutRepository.get()
    val myResponseAthlete: MutableLiveData<Athlete?> = MutableLiveData()
    val myResponseGetRunActivity: MutableLiveData<RunActivity?> = MutableLiveData()
    val myResponseCreateRunActivity: MutableLiveData<RunActivity?> = MutableLiveData()
    val myResponseToken: MutableLiveData<RunningToken?> = MutableLiveData()
    val myResponseAthleteActivities: MutableLiveData<ArrayList<RunActivity>?> = MutableLiveData()


    fun getAthletes() {
        viewModelScope.launch {
            val response = workoutRepository.getAthlete()
            myResponseAthlete.value = response
        }
    }

    fun createActivity(name: String, type: ActivityType, start_date_local: String, elapsed_time: Int, description: String, distance: Float){
        viewModelScope.launch {
            val response =workoutRepository.createRunningActivity(name,type,start_date_local,elapsed_time,description,distance)
            myResponseCreateRunActivity.value = response
        }
    }



    fun getRunningActivity(id:Long) {
        viewModelScope.launch {
            val response = workoutRepository.getRunningActivity(id)
            myResponseGetRunActivity.value = response
        }
    }


    fun getAthleteActivities() {
        viewModelScope.launch {
        val response = workoutRepository.getAthleteActivities()
            myResponseAthleteActivities.value = response
        }
    }

    fun authenticateFirstTime(code:String){
        viewModelScope.launch {
            val response =workoutRepository.getToken(code, FIRST_GRANT_TYPE)
            myResponseToken.value = response
        }
    }
}