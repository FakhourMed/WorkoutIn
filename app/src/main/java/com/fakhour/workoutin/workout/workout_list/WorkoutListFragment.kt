package com.fakhour.workoutin.workout.workout_list

import android.os.Bundle
import androidx.lifecycle.Observer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fakhour.workoutin.R
import com.fakhour.workoutin.databinding.FragmentWorkoutListBinding
import com.fakhour.workoutin.workout.entities.Workout
import com.fakhour.workoutin.workout.entities.WorkoutSection
import com.fakhour.workoutin.workout.workout_sections.WORKOUT_SECTION_ID
import java.util.*
import kotlin.collections.ArrayList

const val WORKOUT_ID = "package com.fakhour.workoutin.workout.workout_list.WORKOUT_SECTION_ID"
const val START_WORKOUT = "package com.fakhour.workoutin.workout.workout_list.START_WORKOUT"

class WorkoutListFragment : Fragment() {

    private var _binding: FragmentWorkoutListBinding? = null
    private val binding get() = _binding!!

    private val workoutListViewModel: WorkoutListViewModel by lazy {
        ViewModelProvider(this).get(WorkoutListViewModel::class.java)
    }

    private lateinit var workoutListAdapter: WorkoutListAdapter
    var workoutArrayList: ArrayList<Workout>? = null
    var workoutSection: WorkoutSection? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWorkoutListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        workoutListAdapter = WorkoutListAdapter(requireContext(), null)

        workoutSection= arguments?.getSerializable(WORKOUT_SECTION_ID) as WorkoutSection

        workoutSection?.let {
            workoutListViewModel.loadWorkoutList(it.id)
        }

        workoutListViewModel.workoutListLiveData.observe(
            viewLifecycleOwner,
            Observer { workout ->
                workout?.let {
                    workoutArrayList= ArrayList<Workout>(it)
                    workoutListAdapter.update(it)
                }
            })

binding.sectionSummaryLayout.startBtn.setOnClickListener {
    var bundle = Bundle()
    bundle.putSerializable(WORKOUT_ID, workoutArrayList?.get(0))
    bundle.putBoolean(START_WORKOUT, true)
    findNavController().navigate(R.id.action_workout_list_to_workout_detail, bundle)

}

        binding.workoutSectionsRecycler.adapter = workoutListAdapter
        binding.workoutSectionsRecycler.layoutManager = LinearLayoutManager(requireContext())

        binding.sectionSummaryLayout.sectionTitle.text=workoutSection?.sectionTitle
        binding.sectionSummaryLayout.sectionDescription.text=workoutSection?.sectionDescription
        binding.sectionSummaryLayout.imageView.setImageResource(workoutSection?.sectionImage?:-1)

        workoutListAdapter.setOnItemClickListener {
            var bundle = Bundle()
            bundle.putSerializable(WORKOUT_ID, workoutArrayList?.get(it))
            findNavController().navigate(R.id.action_workout_list_to_workout_detail, bundle)

        }
    }

    companion object {
        fun newInstance(): WorkoutListFragment {
            return WorkoutListFragment()
        }
    }
}