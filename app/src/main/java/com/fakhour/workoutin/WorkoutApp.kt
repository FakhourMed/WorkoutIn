package com.fakhour.workoutin

import android.app.Application
import com.fakhour.workoutin.common.repository.WorkoutRepository

class WorkoutApp:Application() {
    override fun onCreate() {
        super.onCreate()
        WorkoutRepository.initialize(this)

    }
}