package com.fakhour.workoutin.common.repository

import com.fakhour.workoutin.workout.entities.Athlete
import com.fakhour.workoutin.workout.entities.RunActivity
import com.fakhour.workoutin.workout.entities.RunningToken

object Mapper {
    fun toAthleteObject(response: Athlete?): Athlete? {
        if (response == null) return null
        else
            return Athlete(
                id = response.id,
                username = response.username,
                resourceState = response.resourceState,
                firstname = response.firstname,
                lastname = response.lastname,
                city = response.city,
                state = response.state,
                country = response.country,
                sex = response.sex,
                premium = response.premium,
                createdAt = response.createdAt,
                updatedAt = response.updatedAt,
                badgeTypeId = response.badgeTypeId,
                athleteType = response.athleteType,
                weight = response.weight
            )
    }

    fun toRunningActivityObject(response: RunActivity): RunActivity? {
        if (response == null) return null
        else
            return RunActivity(
                id = response.id,
                name = response.name,
                distance = response.distance,
                movingTime = response.movingTime,
                elapsedTime = response.elapsedTime,
                type = response.type,
                startDate = response.startDate,
                workoutType = response.workoutType,
                description = response.description,
                calories = response.calories
            )
    }


    fun toAthleteActivities(response: ArrayList<RunActivity>): ArrayList<RunActivity>? {
        val array:ArrayList<RunActivity> = arrayListOf()
        if (response == null) return null
        else {
            response.forEach { response->
                array.add(RunActivity(
                    id = response.id,
                    name = response.name,
                    distance = response.distance,
                    movingTime = response.movingTime,
                    elapsedTime = response.elapsedTime,
                    type = response.type,
                    startDate = response.startDate,
                    workoutType = response.workoutType,
                    description = response.description,
                    calories = response.calories
                ))
            }
            return array
        }
    }

    fun toTokenObject(response: RunningToken):RunningToken?{
        if (response == null) return null
        else
            return RunningToken(accessToken = response.accessToken,
                expiresAt = response.expiresAt,
                expiresIn = response.expiresIn,
                refreshToken = response.refreshToken,
                tokenType = response.tokenType
            )
    }
}