package com.fakhour.workoutin.workout.workout_sections.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

@Entity
data class WorkoutSection(
    @PrimaryKey val id: String,
    val sectionTitle: String,
    val sectionDescription: String,
    val sectionImage: Int,
):Serializable
