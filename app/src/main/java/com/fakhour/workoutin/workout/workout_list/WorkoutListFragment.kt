package com.fakhour.workoutin.workout.workout_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fakhour.workoutin.R
import com.fakhour.workoutin.databinding.FragmentWorkoutListBinding
import com.fakhour.workoutin.workout.entities.Workout

class WorkoutListFragment : Fragment() {

    private var _binding: FragmentWorkoutListBinding? = null
    private val binding get() = _binding!!

    private lateinit var workoutListAdapter: WorkoutListAdapter
    var workoutArrayList: ArrayList<Workout>? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWorkoutListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        workoutArrayList = arrayListOf(
          Workout(0,"Alternate Heel Touches", "description", R.mipmap.workout_img232 )  ,
          Workout(1,"Abdominal Hip Roll", "description", R.mipmap.workout_img3924 )  ,
          Workout(2,"Alternating Arm Cobra", "description", R.mipmap.workout_img2504 )  ,
          Workout(3,"Arm Circles", "description", R.mipmap.workout_img3468 )  ,
          Workout(4,"Ankle Circles", "description", R.mipmap.workout_img1156 )  ,
          Workout(5,"Back Relaxation", "description", R.mipmap.workout_img2176 )  ,
          Workout(6,"Alternating Reach and Catch", "description", R.mipmap.workout_img3872 )  ,
          Workout(7,"All Fours Squad Stretch", "description", R.mipmap.workout_img1856)  ,
          Workout(8,"Assisted Chest Stretch", "description", R.mipmap.workout_img3340 )  ,
          Workout(9,"Abdominal Pendulum", "description", R.mipmap.workout_img3868 )
        )
        workoutListAdapter = WorkoutListAdapter(requireContext(), workoutArrayList)

        binding.workoutSectionsRecycler.adapter = workoutListAdapter
        binding.workoutSectionsRecycler.layoutManager = LinearLayoutManager(requireContext())

        binding.sectionSummaryLayout.sectionTitle.text="Full Body"
        binding.sectionSummaryLayout.sectionDescription.text="A full-body workouts designed to challenge every muscle in your body. All you need is your body and a few feet of floor space, but you'll definitely feel like you've accomplished something"

        workoutListAdapter.setOnItemClickListener {

            findNavController().navigate(R.id.action_workout_list_to_workout_detail)

        }
    }

    companion object {
        fun newInstance(): WorkoutListFragment {
            return WorkoutListFragment()
        }
    }
}