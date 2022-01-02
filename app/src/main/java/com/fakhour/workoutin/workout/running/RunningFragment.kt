package com.fakhour.workoutin.workout.running

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fakhour.workoutin.databinding.FragmentRunningBinding
import com.fakhour.workoutin.databinding.FragmentWorkoutDetailBinding
import com.fakhour.workoutin.databinding.RunningLayoutBinding
import com.fakhour.workoutin.workout.workout_detail.WorkoutDetailViewModel
import okio.ByteString.Companion.toByteString
import java.util.*

class RunningFragment : Fragment() {

    private var _binding: FragmentRunningBinding? = null

    private val runningViewModel: RunningViewModel by lazy {
        ViewModelProvider(this).get(RunningViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentRunningBinding.inflate(inflater, container, false)

        _binding = binding
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.getAthleteBtn?.setOnClickListener {
            runningViewModel.getAthletes()
        }

        _binding?.getRunningActivityBtn?.setOnClickListener {
            runningViewModel.getRunningActivity(1212121212)

        }

        _binding?.createActivityBtn?.setOnClickListener {
            runningViewModel.createActivity("Running Sunday","Running", Date(), 3000, "Nice Run", 1500F)
        }

        runningViewModel.myResponseAthlete.observe(viewLifecycleOwner, Observer { athlete ->
            if (athlete != null) {
                _binding?.athleteId?.text="athleteId: ${athlete.id}"
                _binding?.athleteFirstName?.text=athlete.firstname
                _binding?.athleteLastName?.text=athlete.lastname
                _binding?.athleteWeight?.text="Weight:" + athlete.weight.toString()
                Log.d("TAG", "onViewCreated: ${athlete.firstname} ${athlete.lastname}, ${athlete.createdAt}")
            }

        })

        runningViewModel.myResponseRunActivity.observe(viewLifecycleOwner, Observer { runActivity ->
            if (runActivity != null) {
                Log.d("TAG", "onViewCreated: ${runActivity.id}")
            }else{
                Log.d("TAG", "onViewCreated: ")

            }

        })

        runningViewModel.myResponseRunActivityPost.observe(viewLifecycleOwner, Observer { runActivity ->
            if (runActivity != null) {
                Log.d("TAG", "onViewCreated: ${runActivity.id}")
            }else{
                Log.d("TAG", "onViewCreated: ")

            }

        })
    }

}