package com.fakhour.workoutin.common.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.fakhour.workoutin.common.api.RetrofitInstance
import com.fakhour.workoutin.common.api.authentication.RetrofitAuthenticatorInstance
import com.fakhour.workoutin.common.database.WorkoutDatabase
import com.fakhour.workoutin.workout.entities.*
import java.util.*
import java.util.concurrent.Executors
import kotlin.collections.ArrayList

private const val DATABASE_NAME = "workout-database"
const val APP_ID = "75995"
const val CLIENT_SECRET = "05ba47519b5e6951a423726e297a8ea7d482baad"



class WorkoutRepository private constructor(context: Context) {
    /*
        val workoutSectionArrayList = arrayListOf(
            WorkoutSection(sectionTitle = "Full Body",
                sectionDescription = "A full-body workouts designed to challenge every muscle in your body. All you need is your body and a few feet of floor space, but you'll definitely feel like you've accomplished something",
                sectionImage = R.mipmap.all_body),
            WorkoutSection(sectionTitle = "chest",
                sectionDescription = "Target your chest fat with high intensity workout. No embarrassing man boobs when taking T-shirt off",
                sectionImage = R.mipmap.chest),
            WorkoutSection(sectionTitle = "Arm and shoulders",
                sectionDescription = "Get stronger arms and wider shoulders with targeted workouts. Suitable for all fitness levels",
                sectionImage = R.mipmap.biceps),
            WorkoutSection(sectionTitle = "Legs",
                sectionDescription = "The best workouts for a stronger lower body. Gain muscle mass and increase strength",
                sectionImage = R.mipmap.legs),
            WorkoutSection(sectionTitle = "Abs",
                sectionDescription = "Combined ABS workout with HIIT, you can melt away excess fat fast and make your six-pack visible easily",
                sectionImage = R.mipmap.abs),
            WorkoutSection(sectionTitle = "Back",
                sectionDescription = "Strengthen and mobilize your lower back. Relax back muscles and prevent back injuries",
                sectionImage = R.mipmap.back),
        )

        val workoutArrayList = arrayListOf(
            Workout(title = "Alternate Heel Touches",
                description = "description",
                firstImg = R.mipmap.workout_img232,
                sectionId = workoutSectionArrayList[3].id),
            Workout(title = "Abdominal Hip Roll",
                description = "description",
                firstImg = R.mipmap.workout_img3924,
                sectionId = workoutSectionArrayList[3].id),
            Workout(title = "Alternating Arm Cobra",
                description = "description",
                firstImg = R.mipmap.workout_img2504,
                sectionId = workoutSectionArrayList[1].id),
            Workout(title = "Arm Circles", description = "description", firstImg = R.mipmap.workout_img3468, sectionId = workoutSectionArrayList[1].id),
            Workout(title = "Ankle Circles", description = "description", firstImg = R.mipmap.workout_img1156, sectionId = workoutSectionArrayList[1].id),
            Workout(title = "Back Relaxation",
                description = "description",
                firstImg = R.mipmap.workout_img2176,
                sectionId = workoutSectionArrayList[3].id),
            Workout(title = "Alternating Reach and Catch",
                description = "description",
                firstImg = R.mipmap.workout_img3872,
                sectionId = workoutSectionArrayList[3].id),
            Workout(title = "All Fours Squad Stretch",
                description = "description",
                firstImg = R.mipmap.workout_img1856,
                sectionId = workoutSectionArrayList[3].id),
            Workout(title = "Assisted Chest Stretch",
                description = "description",
                firstImg = R.mipmap.workout_img3340,
                sectionId = workoutSectionArrayList[3].id),
            Workout(title = "Abdominal Pendulum",
                description = "description",
                firstImg = R.mipmap.workout_img3868,
                sectionId = workoutSectionArrayList[3].id)
        )
    */
    private val database: WorkoutDatabase = Room.databaseBuilder(
        context.applicationContext,
        WorkoutDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val workoutDao = database.workoutDao()
    private val executor = Executors.newSingleThreadExecutor()


    fun getAllWorkoutSections(): LiveData<List<WorkoutSection>?> {
        return workoutDao.getAllWorkoutSections()
    }

    fun getWorkoutSection(id: String): LiveData<WorkoutSection?> {
        return workoutDao.getWorkoutSection(id)
    }

    fun updateSection(workoutSection: WorkoutSection) {
        executor.execute {
            workoutDao.updateWorkoutSection(workoutSection)
        }
    }

    fun addWorkoutSection(workoutSection: WorkoutSection) {
        executor.execute {
            workoutDao.addWorkoutSection(workoutSection)
        }
    }

    fun getSectionWorkouts(workoutSectionId: String): LiveData<List<Workout>?> {
        return workoutDao.getSectionWorkouts(workoutSectionId)
    }

    fun getWorkoutDetail(workoutId: Int): LiveData<Workout?> {
        return workoutDao.getWorkoutDetail(workoutId)
    }

    fun addWorkoutDetail(workoutDetail: Workout) {
        executor.execute {
            workoutDao.addWorkoutDetail(workoutDetail)
        }
    }

    fun deletteAllSections() {
        executor.execute {
            workoutDao.deletteAllSections()
        }
    }

    fun deleteAllWorkouts() {
        executor.execute {
            workoutDao.deletteAllWorkouts()
        }
    }

    suspend fun getToken(code:String, grantType:String): RunningToken? {
        val response = RetrofitAuthenticatorInstance.apiAuthenticator.getToken(APP_ID, CLIENT_SECRET, code, grantType)
        return if (response.body() != null) {
            Mapper.toTokenObject(response.body()!!)
        } else
            null
    }



    suspend fun getAthlete(): Athlete? {
        val response = RetrofitInstance.api.getAthletes()
        return if (response.body() != null) {
            Mapper.toAthleteObject(response.body())
        } else
            null
    }

    suspend fun createRunningActivity(name: String, type: ActivityType, start_date_local: String, elapsed_time: Int, description: String, distance: Float): RunActivity? {
        val response = RetrofitInstance.api.createActivity(name, type.name, start_date_local, elapsed_time, description, distance)
        Log.d("TAG", "createRunningActivity: ")
        return if (response.body() != null) {
            Mapper.toRunningActivityObject(response.body()!!)
        } else
            null
    }

    suspend fun getRunningActivity(id: Long): RunActivity? {
        val response = RetrofitInstance.api.getRunningActivity(id)
        Log.d("TAG", "getRunningActivity: ")
        return if (response.body() != null) {
            Mapper.toRunningActivityObject(response.body()!!)
        } else
            null

    }

    suspend fun getAthleteActivities():ArrayList<RunActivity>? {
        val response = RetrofitInstance.api.getAthleteActivities()
        Log.d("TAG", "getAthleteActivities: ")
        return if (response.body() != null) {
            Mapper.toAthleteActivities(response.body()!!)
        } else
            null
    }


    companion object {
        private var INSTANCE: WorkoutRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = WorkoutRepository(context)
            }
        }

        fun get(): WorkoutRepository {
            return INSTANCE ?: throw IllegalStateException("WorkRepository must be initialized")
        }
    }
}