package com.fakhour.workoutin

import android.preference.PreferenceManager
import androidx.lifecycle.ViewModel
import com.fakhour.workoutin.common.repository.WorkoutRepository
import com.fakhour.workoutin.workout.entities.MuscleGroupEnum
import com.fakhour.workoutin.workout.entities.Workout
import com.fakhour.workoutin.workout.running.TOKEN
import com.fakhour.workoutin.workout.workout_sections.entities.WorkoutSection
import com.fakhour.workoutin.workout.workout_list.WorkoutListViewModel
import com.fakhour.workoutin.workout.workout_sections.WorkoutSectionViewModel

class MainActivityViewModel : ViewModel() {

    private val workoutRepository = WorkoutRepository.get()

    fun populateDatabase(workoutSectionViewModel: WorkoutSectionViewModel, workoutListViewModel: WorkoutListViewModel) {


        populateSections(workoutSectionViewModel)

        populateWorkout(workoutListViewModel)


    }

    private fun populateWorkout(workoutListViewModel: WorkoutListViewModel) {
        workoutListViewModel.deleteAllWorkouts()
        workoutListViewModel.addWorkoutDetail(Workout(title = "Alternate Heel Touches",
            description = "Steps : \n" +
                    "1.) Start off lying on the floor with your knees bent, your feet on the floor at around 18 to 24 inches apart, and your arms extended by your side.\n" +
                    "2.) Once in the starting position, crunch forward over your torso to the right side and touche your right heel.\n" +
                    "3.) Hold this position for a few seconds, squeezing your abs and then return back to the starting position.\n" +
                    "4.) Switch to the other side, crunching over your torso on the left side and touching your left heel, squeezing your abs and holding the contraction for a count.\n" +
                    "5.) Return back to the starting position and alternate sides for the desired about of reps and sets.",
            firstImg = R.mipmap.workout_img232,
            sectionId = MuscleGroupEnum.ABS.name))


        workoutListViewModel.addWorkoutDetail(Workout(title = "Alternating Arm Cobra",
            description = "Steps : \n" +
                    "1.) Start off by laying face down on the floor in a prone position with your arms at your sides.\n" +
                    "2.) Squeeze your glutes and lift your chest off of the floor with your arms alternately touching the floor from side to side while keeping your arms straight.\n" +
                    "3.) Repeat for as many reps and sets desired.",
            firstImg = R.mipmap.workout_img2504,
            sectionId = MuscleGroupEnum.ABS.name))

        workoutListViewModel.addWorkoutDetail(Workout(title = "Arm Circles",
            description = "Steps :\n" +
                    "1.) Start off laying on your back with your knees bent at 90-degrees and keeping your arms at your sides, palms up.\n" +
                    "2.) Maintaining slight pressure on your hands,  extend your legs slowly forward so that you feel a stretch and squeeze on your abdominals.\n" +
                    "3.) Return back to the starting position and repeat for as many reps and sets desired.",
            firstImg = R.mipmap.workout_img3468,
            sectionId = MuscleGroupEnum.ARM_SHOULDERS.name))

        workoutListViewModel.addWorkoutDetail(Workout(title = "Ankle Circles",
            description = "The ankle circles exercise is the most simple workout for the calves to loosen the joints and strengthen and improve flexibility within the muscle.\n" +
                    "Steps :\n" +
                    "1.) Start by standing on one foot near a chair or wall for support.\n" +
                    "2.) Lift your foot off the ground and draw circles clockwise in the air with your toes.\n" +
                    "3.) Reverse the direction (counter-clockwise) and switch feet.",
            firstImg = R.mipmap.workout_img1156,
            sectionId = MuscleGroupEnum.LEGS.name))
        workoutListViewModel.addWorkoutDetail(Workout(title = "Back Relaxation",
            description = "Steps : \n" +
                    "1.) Take an exercise ball and lie face up on it with your knees bent and feet on the floor.\n" +
                    "2.) Extend your body and relax your back on the ball, easing your arms behind your head.\n" +
                    "3.) Hold this position for 5 to 10 minutes to allow your muscles to relax and stretch" +
                    "4.) Return back to the starting position.\"\n",
            firstImg = R.mipmap.workout_img2176,
            sectionId = MuscleGroupEnum.BACK.name))
        workoutListViewModel.addWorkoutDetail(Workout(title = "Alternating Reach and Catch",
            description = "Steps :\n" +
                    "1.) Begin by laying with your back flat on the floor, keeping your legs bent and feet planted on the floor to stabilize your body.\n" +
                    "2.) Then take both of your hands and place them on your right thigh until you reach your knee until you feel a stretch in your abs, then return back to the starting position.\n" +
                    "3.) Repeat for as many reps and sets as desired.",
            firstImg = R.mipmap.workout_img3872,
            sectionId = MuscleGroupEnum.ABS.name))
        workoutListViewModel.addWorkoutDetail(Workout(title = "All Fours Squad Stretch",
            description = "Steps : \n" +
                    "1.) To begin this exercise; start off in a kneeling pushing position and lift your right leg off of the floor and hold onto it with your right hand.\n" +
                    "2.) With your hand hold onto your ankle and stretch out your upper legs holding on for 15 to 30 seconds, then alternate.\n" +
                    "3.) Repeat this exercise for as many repetitions as needed",
            firstImg = R.mipmap.workout_img1856,
            sectionId = MuscleGroupEnum.LEGS.name))
        workoutListViewModel.addWorkoutDetail(Workout(title = "Assisted Chest Stretch",
            description = "Steps :\n" +
                    "1.) In this stretch you will need a partner behind you to assist.\n" +
                    "2.) Start off by sitting upright on the floor with your partner behind you, placing your hands behind your head and legs extended out in front of you with a slight bend in the knees.\n" +
                    "3.) Have your partner hold your elbows, then try and pull your elbows from behind your head for about 15 to 30 seconds then release.\n" +
                    "4.) Then afterwards have your partner pull back on your elbows again but easily this time to help stretch out the chest muscles.",
            firstImg = R.mipmap.workout_img3340,
            sectionId = MuscleGroupEnum.CHEST.name))
        workoutListViewModel.addWorkoutDetail(Workout(title = "Assisted Hyperextension",
            description = "Steps : \n" +
                    "1.) To begin this exercise; have a partner grab your legs as your lay down on your stomach on a flat bench until your pelvis has reached the end of the bench.\n" +
                    "2.) Keep your body straight and cross your arms across your chest and then start to bend forward as far as you can until you can almost touch the floor.\n" +
                    "3.) Return back to the starting position using your torso and inhale on the way up.\n" +
                    "4.) Repeat this exercise for as many repetitions as needed.",
            firstImg = R.mipmap.workout_img3868,
            sectionId = MuscleGroupEnum.BACK.name))
        workoutListViewModel.addWorkoutDetail(Workout(title = "Assisted Cable Seated Shrug",
            description = "Steps :\n" +
                    "1.) Start by setting up a straight or V-Bar on a low pulley cable machine and sit up straight, feet extended out towards the machine and holding the bar at waist level.\n" +

                    "2.) While keeping your body still, slowly shrug the bar up as high as you can, pulling your shoulders and hold for a count.\n" +

                    "3.) Return back to the starting position.\n" +

                    "4.) Repeat for as many reps and sets as desired.",
            firstImg = R.mipmap.workout_img3868,
            sectionId = MuscleGroupEnum.ARM_SHOULDERS.name))
        workoutListViewModel.addWorkoutDetail(Workout(title = "All Fours Squad Stretch",
            description = "Steps : \n" +
                    "1.) To begin this exercise; start off in a kneeling pushing position and lift your right leg off of the floor and hold onto it with your right hand.\n" +
                    "2.) With your hand hold onto your ankle and stretch out your upper legs holding on for 15 to 30 seconds, then alternate.\n" +
                    "3.) Repeat this exercise for as many repetitions as needed",
            firstImg = R.mipmap.workout_img1856,
            sectionId = MuscleGroupEnum.ARM_SHOULDERS.name))
        workoutListViewModel.addWorkoutDetail(Workout(title = "Back Relaxation",
            description = "Steps : \n" +
                    "1.) Take an exercise ball and lie face up on it with your knees bent and feet on the floor.\n" +
                    "2.) Extend your body and relax your back on the ball, easing your arms behind your head.\n" +
                    "3.) Hold this position for 5 to 10 minutes to allow your muscles to relax and stretch" +
                    "4.) Return back to the starting position.\"\n",
            firstImg = R.mipmap.workout_img2176,
            sectionId = MuscleGroupEnum.LEGS.name))

        workoutListViewModel.addWorkoutDetail(Workout(title = "Alternate Heel Touches",
            description = "Steps : \n" +
                    "1.) Start off lying on the floor with your knees bent, your feet on the floor at around 18 to 24 inches apart, and your arms extended by your side.\n" +
                    "2.) Once in the starting position, crunch forward over your torso to the right side and touche your right heel.\n" +
                    "3.) Hold this position for a few seconds, squeezing your abs and then return back to the starting position.\n" +
                    "4.) Switch to the other side, crunching over your torso on the left side and touching your left heel, squeezing your abs and holding the contraction for a count.\n" +
                    "5.) Return back to the starting position and alternate sides for the desired about of reps and sets.",
            firstImg = R.mipmap.workout_img232,
            sectionId = MuscleGroupEnum.ABS.name))
        workoutListViewModel.addWorkoutDetail(Workout(title = "All Fours Squad Stretch",
            description = "Steps : \n" +
                    "1.) To begin this exercise; start off in a kneeling pushing position and lift your right leg off of the floor and hold onto it with your right hand.\n" +
                    "2.) With your hand hold onto your ankle and stretch out your upper legs holding on for 15 to 30 seconds, then alternate.\n" +
                    "3.) Repeat this exercise for as many repetitions as needed",
            firstImg = R.mipmap.workout_img1856,
            sectionId = MuscleGroupEnum.CHEST.name))
        workoutListViewModel.addWorkoutDetail(Workout(title = "Back Relaxation",
            description = "Steps : \n" +
                    "1.) Take an exercise ball and lie face up on it with your knees bent and feet on the floor.\n" +
                    "2.) Extend your body and relax your back on the ball, easing your arms behind your head.\n" +
                    "3.) Hold this position for 5 to 10 minutes to allow your muscles to relax and stretch" +
                    "4.) Return back to the starting position.\"\n",
            firstImg = R.mipmap.workout_img2176,
            sectionId = MuscleGroupEnum.CHEST.name))

        workoutListViewModel.addWorkoutDetail(Workout(title = "Alternate Heel Touches",
            description = "Steps : \n" +
                    "1.) Start off lying on the floor with your knees bent, your feet on the floor at around 18 to 24 inches apart, and your arms extended by your side.\n" +
                    "2.) Once in the starting position, crunch forward over your torso to the right side and touche your right heel.\n" +
                    "3.) Hold this position for a few seconds, squeezing your abs and then return back to the starting position.\n" +
                    "4.) Switch to the other side, crunching over your torso on the left side and touching your left heel, squeezing your abs and holding the contraction for a count.\n" +
                    "5.) Return back to the starting position and alternate sides for the desired about of reps and sets.",
            firstImg = R.mipmap.workout_img232,
            sectionId = MuscleGroupEnum.CHEST.name))


        workoutListViewModel.addWorkoutDetail(Workout(title = "Alternating Arm Cobra",
            description = "Steps : \n" +
                    "1.) Start off by laying face down on the floor in a prone position with your arms at your sides.\n" +
                    "2.) Squeeze your glutes and lift your chest off of the floor with your arms alternately touching the floor from side to side while keeping your arms straight.\n" +
                    "3.) Repeat for as many reps and sets desired.",
            firstImg = R.mipmap.workout_img2504,
            sectionId = MuscleGroupEnum.ABS.name))

        workoutListViewModel.addWorkoutDetail(Workout(title = "Arm Circles",
            description = "Steps :\n" +
                    "1.) Start off laying on your back with your knees bent at 90-degrees and keeping your arms at your sides, palms up.\n" +
                    "2.) Maintaining slight pressure on your hands,  extend your legs slowly forward so that you feel a stretch and squeeze on your abdominals.\n" +
                    "3.) Return back to the starting position and repeat for as many reps and sets desired.",
            firstImg = R.mipmap.workout_img3468,
            sectionId = MuscleGroupEnum.ARM_SHOULDERS.name))

        workoutListViewModel.addWorkoutDetail(Workout(title = "Ankle Circles",
            description = "The ankle circles exercise is the most simple workout for the calves to loosen the joints and strengthen and improve flexibility within the muscle.\n" +
                    "Steps :\n" +
                    "1.) Start by standing on one foot near a chair or wall for support.\n" +
                    "2.) Lift your foot off the ground and draw circles clockwise in the air with your toes.\n" +
                    "3.) Reverse the direction (counter-clockwise) and switch feet.",
            firstImg = R.mipmap.workout_img1156,
            sectionId = MuscleGroupEnum.LEGS.name))
        workoutListViewModel.addWorkoutDetail(Workout(title = "Back Relaxation",
            description = "Steps : \n" +
                    "1.) Take an exercise ball and lie face up on it with your knees bent and feet on the floor.\n" +
                    "2.) Extend your body and relax your back on the ball, easing your arms behind your head.\n" +
                    "3.) Hold this position for 5 to 10 minutes to allow your muscles to relax and stretch" +
                    "4.) Return back to the starting position.\"\n",
            firstImg = R.mipmap.workout_img2176,
            sectionId = MuscleGroupEnum.BACK.name))
        workoutListViewModel.addWorkoutDetail(Workout(title = "Alternating Reach and Catch",
            description = "Steps :\n" +
                    "1.) Begin by laying with your back flat on the floor, keeping your legs bent and feet planted on the floor to stabilize your body.\n" +
                    "2.) Then take both of your hands and place them on your right thigh until you reach your knee until you feel a stretch in your abs, then return back to the starting position.\n" +
                    "3.) Repeat for as many reps and sets as desired.",
            firstImg = R.mipmap.workout_img3872,
            sectionId = MuscleGroupEnum.ABS.name))
        workoutListViewModel.addWorkoutDetail(Workout(title = "All Fours Squad Stretch",
            description = "Steps : \n" +
                    "1.) To begin this exercise; start off in a kneeling pushing position and lift your right leg off of the floor and hold onto it with your right hand.\n" +
                    "2.) With your hand hold onto your ankle and stretch out your upper legs holding on for 15 to 30 seconds, then alternate.\n" +
                    "3.) Repeat this exercise for as many repetitions as needed",
            firstImg = R.mipmap.workout_img1856,
            sectionId = MuscleGroupEnum.LEGS.name))
        workoutListViewModel.addWorkoutDetail(Workout(title = "Assisted Chest Stretch",
            description = "Steps :\n" +
                    "1.) In this stretch you will need a partner behind you to assist.\n" +
                    "2.) Start off by sitting upright on the floor with your partner behind you, placing your hands behind your head and legs extended out in front of you with a slight bend in the knees.\n" +
                    "3.) Have your partner hold your elbows, then try and pull your elbows from behind your head for about 15 to 30 seconds then release.\n" +
                    "4.) Then afterwards have your partner pull back on your elbows again but easily this time to help stretch out the chest muscles.",
            firstImg = R.mipmap.workout_img3340,
            sectionId = MuscleGroupEnum.CHEST.name))
        workoutListViewModel.addWorkoutDetail(Workout(title = "Assisted Hyperextension",
            description = "Steps : \n" +
                    "1.) To begin this exercise; have a partner grab your legs as your lay down on your stomach on a flat bench until your pelvis has reached the end of the bench.\n" +
                    "2.) Keep your body straight and cross your arms across your chest and then start to bend forward as far as you can until you can almost touch the floor.\n" +
                    "3.) Return back to the starting position using your torso and inhale on the way up.\n" +
                    "4.) Repeat this exercise for as many repetitions as needed.",
            firstImg = R.mipmap.workout_img3868,
            sectionId = MuscleGroupEnum.BACK.name))
        workoutListViewModel.addWorkoutDetail(Workout(title = "Assisted Cable Seated Shrug",
            description = "Steps :\n" +
                    "1.) Start by setting up a straight or V-Bar on a low pulley cable machine and sit up straight, feet extended out towards the machine and holding the bar at waist level.\n" +

                    "2.) While keeping your body still, slowly shrug the bar up as high as you can, pulling your shoulders and hold for a count.\n" +

                    "3.) Return back to the starting position.\n" +

                    "4.) Repeat for as many reps and sets as desired.",
            firstImg = R.mipmap.workout_img3868,
            sectionId = MuscleGroupEnum.ARM_SHOULDERS.name))
        workoutListViewModel.addWorkoutDetail(Workout(title = "All Fours Squad Stretch",
            description = "Steps : \n" +
                    "1.) To begin this exercise; start off in a kneeling pushing position and lift your right leg off of the floor and hold onto it with your right hand.\n" +
                    "2.) With your hand hold onto your ankle and stretch out your upper legs holding on for 15 to 30 seconds, then alternate.\n" +
                    "3.) Repeat this exercise for as many repetitions as needed",
            firstImg = R.mipmap.workout_img1856,
            sectionId = MuscleGroupEnum.ARM_SHOULDERS.name))
        workoutListViewModel.addWorkoutDetail(Workout(title = "Back Relaxation",
            description = "Steps : \n" +
                    "1.) Take an exercise ball and lie face up on it with your knees bent and feet on the floor.\n" +
                    "2.) Extend your body and relax your back on the ball, easing your arms behind your head.\n" +
                    "3.) Hold this position for 5 to 10 minutes to allow your muscles to relax and stretch" +
                    "4.) Return back to the starting position.\"\n",
            firstImg = R.mipmap.workout_img2176,
            sectionId = MuscleGroupEnum.LEGS.name))
    }

    private fun populateSections(workoutSectionViewModel: WorkoutSectionViewModel) {
        workoutSectionViewModel.deleteAllSections()

        workoutSectionViewModel.addWorkoutSection(WorkoutSection(id = MuscleGroupEnum.CHEST.name, sectionTitle = "chest",
            sectionDescription = "Target your chest fat with high intensity workout. No embarrassing man boobs when taking T-shirt off",
            sectionImage = R.mipmap.chest))
        workoutSectionViewModel.addWorkoutSection(WorkoutSection(id = MuscleGroupEnum.ARM_SHOULDERS.name, sectionTitle = "Arm and shoulders",
            sectionDescription = "Get stronger arms and wider shoulders with targeted workouts. Suitable for all fitness levels",
            sectionImage = R.mipmap.biceps))
        workoutSectionViewModel.addWorkoutSection(WorkoutSection(id = MuscleGroupEnum.LEGS.name, sectionTitle = "Legs",
            sectionDescription = "The best workouts for a stronger lower body. Gain muscle mass and increase strength",
            sectionImage = R.mipmap.legs))
        workoutSectionViewModel.addWorkoutSection(WorkoutSection(id = MuscleGroupEnum.ABS.name, sectionTitle = "Abs",
            sectionDescription = "Combined ABS workout with HIIT, you can melt away excess fat fast and make your six-pack visible easily",
            sectionImage = R.mipmap.abs))
        workoutSectionViewModel.addWorkoutSection(WorkoutSection(id = MuscleGroupEnum.BACK.name, sectionTitle = "Back",
            sectionDescription = "Strengthen and mobilize your lower back. Relax back muscles and prevent back injuries",
            sectionImage = R.mipmap.back))
    }

}
