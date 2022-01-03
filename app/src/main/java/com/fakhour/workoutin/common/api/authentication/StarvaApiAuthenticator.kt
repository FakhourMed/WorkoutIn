package com.fakhour.workoutin.common.api.authentication



import com.fakhour.workoutin.workout.entities.Athlete
import com.fakhour.workoutin.workout.entities.RunActivity
import com.fakhour.workoutin.workout.entities.RunningToken
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface StarvaApiAuthenticator {

    @POST("token")
    suspend fun getToken(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("code") code: String,
        @Query("grant_type") grantType: String,
    ): Response<RunningToken>
}