package com.fakhour.workoutin.common.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fakhour.workoutin.R
import com.fakhour.workoutin.workout.entities.Workout
import com.fakhour.workoutin.workout.entities.WorkoutSection
import java.util.*
import kotlin.collections.ArrayList

class WorkoutRepository private constructor(context: Context) {

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
    /*
    private val database : CrimeDatabase = Room.databaseBuilder(
        context.applicationContext,
        CrimeDatabase::class.java,
        DATABASE_NAME
    ).addMigrations(migration_1_2)
        .build()


    private val crimeDao = database.crimeDao()
    private val executor = Executors.newSingleThreadExecutor()
    */

    fun getAllWorkoutSections(): LiveData<ArrayList<WorkoutSection>> {
        //return workoutSectionDao.getWorkoutSection()
        return MutableLiveData(workoutSectionArrayList)
    }

    fun getWorkoutSection(id: UUID): LiveData<WorkoutSection?> {
        //return workoutSectionDao.getWorkoutSection(id)
        return MutableLiveData(workoutSectionArrayList[0])
    }

    fun updateSection(workoutSection: WorkoutSection) {
        /*
        executor.execute {
            crimeDao.updateCrime(crime)
        }

         */
    }

    fun addWorkoutSection(workoutSection: WorkoutSection) {
        /* executor.execute {
             crimeDao.addCrime(crime)
         }

         */
    }

    fun getWorkoutList(workoutSectionId: UUID?): LiveData<ArrayList<Workout>> {
        var _workoutArrayList: ArrayList<Workout> = arrayListOf()
        workoutArrayList.forEach {
            if (it.sectionId == workoutSectionId)
                _workoutArrayList.add(it)
        }
        return MutableLiveData(_workoutArrayList)
    }

    fun getWorkout(workoutId: UUID?): LiveData<Workout?> {
        var _workout:Workout? = null
        workoutArrayList.forEach {
            if (it.id == workoutId)
                 _workout=it
        }
        return MutableLiveData(_workout)
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