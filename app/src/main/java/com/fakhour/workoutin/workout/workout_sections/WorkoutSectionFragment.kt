package com.fakhour.workoutin.workout.workout_sections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fakhour.workoutin.R
import com.fakhour.workoutin.databinding.FragmentWorkoutSectionBinding
import com.fakhour.workoutin.workout.workout_sections.entities.WorkoutSection
import kotlin.collections.ArrayList

const val WORKOUT_SECTION_ID = "package com.fakhour.workoutin.workout.workout_sections.WORKOUT_SECTION_ID"

class WorkoutSectionFragment : Fragment() {

    private var _binding: FragmentWorkoutSectionBinding? = null
    private val binding get() = _binding!!

    private val workoutSectionViewModel: WorkoutSectionViewModel by lazy {
        ViewModelProvider(this).get(WorkoutSectionViewModel::class.java)
    }

    private lateinit var workoutSectionAdapter: WorkoutSectionAdapter
    var workoutSectionArrayList: ArrayList<WorkoutSection>? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWorkoutSectionBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        workoutSectionViewModel.workoutSectionsLiveData.observe(
            viewLifecycleOwner,
            Observer { workoutSections ->
                workoutSections?.let {
                    workoutSectionArrayList = ArrayList<WorkoutSection>(it)
                    workoutSectionAdapter.update(it)
                }
            }
        )
        binding.runningLayout.startRunBtn.setOnClickListener {
            findNavController().navigate(R.id.action_workout_section_to_running)
        }

        workoutSectionAdapter = WorkoutSectionAdapter(requireContext(), null)

        binding.workoutSectionsRecycler.adapter = workoutSectionAdapter
        binding.workoutSectionsRecycler.layoutManager = LinearLayoutManager(requireContext())

        workoutSectionAdapter.setOnItemClickListener {
            var bundle = Bundle()
            bundle.putSerializable(WORKOUT_SECTION_ID, workoutSectionArrayList?.get(it))
            findNavController().navigate(R.id.action_workout_section_to_workout_list, bundle)
        }
    }

    companion object {
        fun newInstance(): WorkoutSectionFragment {
            return WorkoutSectionFragment()
        }
    }
}