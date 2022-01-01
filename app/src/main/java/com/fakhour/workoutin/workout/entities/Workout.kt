package com.fakhour.workoutin.workout.entities

data class Workout(
    val id: Int,
    val title: String,
    val description: String,
    val firstImg: Int,
    val secondImg: Int?=null,
    var isDone: Boolean=false,
    var repitition:Int = 10,
    var duration:String = "30 Sec",
    val muscleGroup: MuscleGroupEnum=MuscleGroupEnum.CHEST,
    val type: WorkoutTypeEnum=WorkoutTypeEnum.CARDIO,
    val difficulty: DifficultyEnum=DifficultyEnum.INTERMEDIATE,
)

enum class DifficultyEnum {
    BEGINNER, INTERMEDIATE, EXPERT
}

enum class MuscleGroupEnum {
    ABS, ARM_SHOULDERS, LEGS, BACK, CHEST
}

enum class WorkoutTypeEnum {
    STRENGTH, CARDIO, POWER_LIFTING, STRETCHING
}
