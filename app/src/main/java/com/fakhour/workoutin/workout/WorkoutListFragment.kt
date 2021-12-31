package com.fakhour.workoutin.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fakhour.workoutin.R
import com.fakhour.workoutin.databinding.FragmentWorkoutListBinding
import com.fakhour.workoutin.workout.entities.WorkoutSection

class WorkoutListFragment: Fragment() {

    private var _binding: FragmentWorkoutListBinding?= null
    private val binding get() = _binding!!

    private lateinit var workoutListAdapter : WorkoutListAdapter
    var workoutSectionArrayList:ArrayList<WorkoutSection>? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWorkoutListBinding.inflate(inflater,container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

workoutSectionArrayList=arrayListOf(
    WorkoutSection(0, "Full Body","A full-body workouts designed to challenge every muscle in your body. All you need is your body and a few feet of floor space, but you'll definitely feel like you've accomplished something", R.mipmap.all_body ),
    WorkoutSection(1, "chest","Target your chest fat with high intensity workout. No embarrassing man boobs when taking T-shirt off", R.mipmap.chest ),
    WorkoutSection(2, "Arm and shoulders","Get stronger arms and wider shoulders with targeted workouts. Suitable for all fitness levels", R.mipmap.biceps ),
    WorkoutSection(3, "Legs","The best workouts for a stronger lower body. Gain muscle mass and increase strength", R.mipmap.legs ),
    WorkoutSection(4, "Abs","Combined ABS workout with HIIT, you can melt away excess fat fast and make your six-pack visible easily", R.mipmap.abs ),
    WorkoutSection(5, "Back","Strengthen and mobilize your lower back. Relax back muscles and prevent back injuries", R.mipmap.back ),
)
        workoutListAdapter = WorkoutListAdapter(requireContext(), workoutSectionArrayList)

        binding.workoutSectionsRecycler.adapter = workoutListAdapter
        binding.workoutSectionsRecycler.layoutManager = LinearLayoutManager(requireContext())
    }
    companion object {
        fun newInstance(): WorkoutListFragment {
            return WorkoutListFragment()
        }
    }
}