package com.fakhour.workoutin.workout.entities

import java.io.Serializable
import java.util.*

data class Workout(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    val firstImg: Int,
    val secondImg: Int? = null,
    var repitition: Int = 10,
    var duration: String = "30 Sec",
    val sectionId: UUID?= null,
    val muscleGroup: MuscleGroupEnum = MuscleGroupEnum.CHEST,
    val type: WorkoutTypeEnum = WorkoutTypeEnum.CARDIO,
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
