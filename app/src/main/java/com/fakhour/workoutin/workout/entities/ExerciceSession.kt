package com.fakhour.workoutin.workout.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ExerciceSession(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val workoutId: UUID,
    var date: Date,
    var isDone: Boolean = false,
    var repitition: Int = 10,
    var duration: Int = 30
)