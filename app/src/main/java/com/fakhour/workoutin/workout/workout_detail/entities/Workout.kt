package com.fakhour.workoutin.workout.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity
data class Workout(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val title: String,
    val description: String,
    val firstImg: Int,
    val secondImg: Int? = null,
    val sectionId: String?= null,
    val muscleGroup: MuscleGroupEnum = MuscleGroupEnum.CHEST,
    val workoutType: WorkoutTypeEnum = WorkoutTypeEnum.CARDIO,
    val difficulty: DifficultyEnum = DifficultyEnum.INTERMEDIATE,
):Serializable

enum class DifficultyEnum {
    BEGINNER, INTERMEDIATE, EXPERT
}

enum class MuscleGroupEnum {
    ABS, ARM_SHOULDERS, LEGS, BACK, CHEST
}

enum class WorkoutTypeEnum {
    STRENGTH, CARDIO, POWER_LIFTING, STRETCHING
}
