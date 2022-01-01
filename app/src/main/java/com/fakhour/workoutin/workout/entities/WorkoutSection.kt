package com.fakhour.workoutin.workout.entities

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

data class WorkoutSection(
    val id: UUID = UUID.randomUUID(),
    val sectionTitle: String,
    val sectionDescription: String,
    val sectionImage: Int,
    val workout: ArrayList<Workout>? = null,
):Serializable
