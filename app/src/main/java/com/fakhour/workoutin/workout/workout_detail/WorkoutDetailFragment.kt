package com.fakhour.workoutin.workout.workout_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fakhour.workoutin.R
import com.fakhour.workoutin.databinding.FragmentWorkoutDetailBinding
import com.fakhour.workoutin.workout.entities.Workout

import android.os.CountDownTimer
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.denzcoskun.imageslider.models.SlideModel
import com.fakhour.workoutin.workout.entities.WorkoutSection
import com.fakhour.workoutin.workout.workout_list.START_WORKOUT
import com.fakhour.workoutin.workout.workout_list.WORKOUT_ID
import com.fakhour.workoutin.workout.workout_list.WorkoutListViewModel
import com.fakhour.workoutin.workout.workout_sections.WORKOUT_SECTION_ID


class WorkoutDetailFragment : Fragment() {

    private var _binding: FragmentWorkoutDetailBinding? = null
    private val NOT_STARTED_STATUS = 0
    private val STARTED_STATUS = 1
    private val FINISHED_STATUS = 2

    var workout: Workout? = null
    var startWorkout: Boolean? = null

    private val workoutDetailViewModel: WorkoutDetailViewModel by lazy {
        ViewModelProvider(this).get(WorkoutDetailViewModel::class.java)
    }

    private var status = NOT_STARTED_STATUS


    private val startTime = (30 * 1000).toLong()
    private val interval = (1 * 1000).toLong()
    private var currentTime = startTime
    val imageList = ArrayList<SlideModel>()

    private var countDownTimer: CountDownTimer? = null


    var workoutArrayList: ArrayList<Workout>? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentWorkoutDetailBinding.inflate(inflater, container, false)

        _binding = binding
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments?.getSerializable(WORKOUT_ID) != null) {
            workout = arguments?.getSerializable(WORKOUT_ID) as Workout?
        }

        if (arguments?.getBoolean(START_WORKOUT) != null) {
            startWorkout = arguments?.getBoolean(START_WORKOUT)
        }

        workout?.let {
            workoutDetailViewModel.loadWorkout(it.id)
        }

        workoutDetailViewModel.workoutLiveData.observe(
            viewLifecycleOwner,
            Observer { workout ->
                workout?.let {
                    _binding?.workoutImg?.setImageList(arrayListOf(SlideModel(workout.firstImg)))
                    _binding?.workoutTitle?.text = workout.title
                    _binding?.workoutDescription?.text = workout.description
                    _binding?.workoutTitleSmall?.text = workout.title

                    if(startWorkout==true){
                        startPauseLogic()
                    }

                }
            })
        setCountDown(startTime, interval)

        _binding?.countDownView?.setOnClickListener {
            startPauseLogic()
        }

        _binding?.btnStart?.setOnClickListener {
            startPauseLogic()
        }

        //  imageList.add(SlideModel(R.mipmap.workout_img232))
        //  imageList.add(SlideModel(R.mipmap.workout_img233))
        // _binding?.workoutImg?.setImageList(imageList)


    }

    private fun startPauseLogic() {
        if (status == NOT_STARTED_STATUS) {
            countDownTimer?.start()
            status = STARTED_STATUS
        } else if (status == STARTED_STATUS) {
            countDownTimer?.cancel()
            setCountDown(currentTime, interval)
            status = NOT_STARTED_STATUS
            _binding?.countDownView?.setText(getString(R.string.paused))
            _binding?.btnStart?.setText(R.string.start)
        } else {
            _binding?.countDownView?.setProgress(30)
            status = NOT_STARTED_STATUS
            _binding?.btnStart?.setText(R.string.start)
        }
    }

    private fun setCountDown(startTime: Long, interval: Long) {
        countDownTimer = object : CountDownTimer(startTime, interval) {
            override fun onTick(millisUntilFinished: Long) {
                _binding?.countDownView?.setProgress(millisUntilFinished.toInt() / 1000)
                _binding?.countDownView?.setText((millisUntilFinished.toInt() / 1000).toString())
                currentTime = millisUntilFinished
            }

            override fun onFinish() {
                _binding?.countDownView?.setProgress(0)
                _binding?.countDownView?.setText(getString(R.string.start))

                status = FINISHED_STATUS
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        fun newInstance(): WorkoutDetailFragment {
            return WorkoutDetailFragment()
        }
    }


}