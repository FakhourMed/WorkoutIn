package com.fakhour.workoutin.workout.entities


import com.google.gson.annotations.SerializedName

data class RunActivity(
    val id: Long,
    val name: String?,
    val distance: Int?,
    @SerializedName("moving_time")
    val movingTime: Int?,
    @SerializedName("elapsed_time")
    val elapsedTime: Int?,
    @SerializedName("total_elevation_gain")
    val type: String?,
    @SerializedName("start_date")
    val startDate: String?,
    @SerializedName("workout_type")
    val workoutType: Int?,
    val description: String?,
    val calories: Double?,

)