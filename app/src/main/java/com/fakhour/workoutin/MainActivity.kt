package com.fakhour.workoutin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fakhour.workoutin.workout.WorkoutListFragment
import com.rbddevs.splashy.Splashy

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSplashy()

        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = WorkoutListFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    fun setSplashy(){
        Splashy(this) 		 // For JAVA : new Splashy(this)
            .setLogo(R.drawable.ic_logo)
            .setTitle("WorkoutIN")
            .setTitleColor("#FFFFFF")
            .setSubTitle("Home workout made easy")
            .setProgressColor(R.color.white)
            .setBackgroundResource(R.mipmap.background)
            .setFullScreen(true)
            .setDuration(2000)
            .show()
    }
}