package com.fakhour.workoutin.common.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fakhour.workoutin.workout.entities.ExerciceSession
import com.fakhour.workoutin.workout.entities.Workout
import com.fakhour.workoutin.workout.entities.WorkoutSection
import java.util.*

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workoutsection")
    fun getAllWorkoutSections(): LiveData<List<WorkoutSection>?>

    @Query("SELECT * FROM workoutsection WHERE id=(:id)")
    fun getWorkoutSection(id: String): LiveData<WorkoutSection?>

    @Update
    fun updateWorkoutSection(workoutSection: WorkoutSection)

    @Insert
    fun addWorkoutSection(workoutSection: WorkoutSection)

    @Query("SELECT * FROM workout WHERE sectionId=(:id)")
    fun getSectionWorkouts(id:String): LiveData<List<Workout>?>

    @Query("SELECT * FROM workout WHERE id=(:id)")
    fun getWorkoutDetail(id: Int): LiveData<Workout?>

    @Insert
    fun addWorkoutDetail(workoutDetail: Workout)

    @Insert
    fun addExerciceSession(exerciceSession: ExerciceSession)

    @Update
    fun updateExerciceSession(exerciceSession: ExerciceSession)

    @Query("DELETE FROM workoutsection")
    fun deletteAllSections()

    @Query("DELETE FROM Workout")
    fun deletteAllWorkouts()
}