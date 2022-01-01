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
import com.denzcoskun.imageslider.models.SlideModel


class WorkoutDetailFragment : Fragment() {

    private var _binding: FragmentWorkoutDetailBinding? = null
    private val NOT_STARTED_STATUS = 0
    private val STARTED_STATUS = 1
    private val FINISHED_STATUS = 2


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

        workoutArrayList = arrayListOf(
            Workout(0, "Alternate Heel Touches", "description", R.mipmap.workout_img232),
            Workout(1, "Abdominal Hip Roll", "description", R.mipmap.workout_img3924),
            Workout(2, "Alternating Arm Cobra", "description", R.mipmap.workout_img2504),
            Workout(3, "Arm Circles", "description", R.mipmap.workout_img3468),
            Workout(4, "Ankle Circles", "description", R.mipmap.workout_img1156),
            Workout(5, "Back Relaxation", "description", R.mipmap.workout_img2176),
            Workout(6, "Alternating Reach and Catch", "description", R.mipmap.workout_img3872),
            Workout(7, "All Fours Squad Stretch", "description", R.mipmap.workout_img1856),
            Workout(8, "Assisted Chest Stretch", "description", R.mipmap.workout_img3340),
            Workout(9, "Abdominal Pendulum", "description", R.mipmap.workout_img3868)
        )

        setCountDown(startTime, interval)

        _binding?.countDownView?.setOnClickListener {
            startPauseLogic()
        }

        _binding?.btnStart?.setOnClickListener {
            startPauseLogic()
        }

        imageList.add(SlideModel(R.mipmap.workout_img232))
        imageList.add(SlideModel(R.mipmap.workout_img233))
        _binding?.workoutImg?.setImageList(imageList)


    }

    private fun startPauseLogic() {
        if (status == NOT_STARTED_STATUS) {
            countDownTimer?.start()
            status = STARTED_STATUS
            _binding?.btnStart?.setText(R.string.pause)
        } else if (status == STARTED_STATUS) {
            countDownTimer?.cancel()
            setCountDown(currentTime, interval)
            status = NOT_STARTED_STATUS
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