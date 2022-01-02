package com.fakhour.workoutin.workout.entities

import com.google.gson.annotations.SerializedName

data class Athlete(
    val id: Long,
    val username: String?,
    @SerializedName("resource_state")
    val resourceState: Int?,
    val firstname: String?,
    val lastname: String?,
    val city: String?,
    val state: String?,
    val country: String?,
    val sex: String?,
    val premium: Boolean?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("badge_type_id")
    val badgeTypeId: Int?,
    @SerializedName("athlete_type")
    val athleteType: Int?,
    val weight: Double?,
)