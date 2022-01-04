package com.fakhour.workoutin.workout.running.entities

import com.google.gson.annotations.SerializedName


data class RunningToken(
    @SerializedName("access_token")
    var accessToken: String,
    @SerializedName("expires_at")
    var expiresAt: Int,
    @SerializedName("expires_in")
    var expiresIn: Int,
    @SerializedName("refresh_token")
    var refreshToken: String,
    @SerializedName("token_type")
    var tokenType: String
)