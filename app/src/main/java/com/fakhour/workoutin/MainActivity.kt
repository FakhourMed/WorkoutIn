package com.fakhour.workoutin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rbddevs.splashy.Splashy

const val IS_SPLASH_SCREEN_DISPLAYED = "package com.fakhour.workoutin.IS_SPLASH_SCREEN_DISPLAYED"
var isSplashScreenDisplayed = false

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState?.getBoolean(IS_SPLASH_SCREEN_DISPLAYED) == false || savedInstanceState?.getBoolean(IS_SPLASH_SCREEN_DISPLAYED) == null) {
            setSplashy()
        }

        setContentView(R.layout.activity_main)


    }

    fun setSplashy() {
        Splashy(this)
            .setLogo(R.drawable.ic_logo)
            .setTitle("WorkoutIN")
            .setTitleColor("#FFFFFF")
            .setSubTitle("Home workout made easy")
            .setProgressColor(R.color.white)
            .setBackgroundResource(R.mipmap.background)
            .setFullScreen(true)
            .setDuration(2000)
            .show()

        isSplashScreenDisplayed = true
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putBoolean(IS_SPLASH_SCREEN_DISPLAYED, isSplashScreenDisplayed)

    }
}