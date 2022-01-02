package com.fakhour.workoutin.common.api



import com.fakhour.workoutin.workout.entities.Athlete
import com.fakhour.workoutin.workout.entities.RunActivity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface StarvaApi {

    @GET("athlete")
    suspend fun getAthletes(): Response<Athlete>

    @GET("activities")
    suspend fun createActivity(
        @Query("name") name: String,
        @Query("type") type: String,
        @Query("start_date_local") start_date_local: Date,
        @Query("elapsed_time") elapsed_time: Int,
        @Query("description") description: String,
        @Query("distance") distance: Float
    ): Response<RunActivity>

    @GET("activities/{id}")
    suspend fun getRunningActivity(@Path("id") id: Long): Response<RunActivity>

/*
    @GET("todos")
    suspend fun getCustomTasks(@Query("userId") userId: Int): Response<List<TaskModel>>

 */
}