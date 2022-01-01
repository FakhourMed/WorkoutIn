package com.fakhour.workoutin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.fakhour.workoutin.workout.workout_sections.WorkoutSectionViewModel
import com.rbddevs.splashy.Splashy

class MainActivity : AppCompatActivity() {
    private val mainActivityViewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel.setSplashy(this)
    }


}