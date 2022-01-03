package com.fakhour.workoutin.workout.running

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fakhour.workoutin.AUTHENTICATION_CODE
import com.fakhour.workoutin.AUTHENTICATION_SCOPE
import com.fakhour.workoutin.AUTHENTICATION_STATE
import com.fakhour.workoutin.common.api.RetrofitInstance
import com.fakhour.workoutin.common.repository.APP_ID
import com.fakhour.workoutin.databinding.FragmentRunningBinding
import com.fakhour.workoutin.databinding.FragmentWorkoutDetailBinding
import com.fakhour.workoutin.databinding.RunningLayoutBinding
import com.fakhour.workoutin.workout.entities.Workout
import com.fakhour.workoutin.workout.workout_detail.WorkoutDetailViewModel
import com.fakhour.workoutin.workout.workout_list.WORKOUT_ID
import okio.ByteString.Companion.toByteString
import java.util.*


class RunningFragment : Fragment() {

    private var _binding: FragmentRunningBinding? = null
    var state:String?=null
    var code:String?=null
    var scope:String?=null

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

        if (arguments?.getString(AUTHENTICATION_CODE) != null) {
            code = arguments?.getString(AUTHENTICATION_CODE)
            runningViewModel.authenticateFirstTime(code!!)


        }
        if (arguments?.getString(AUTHENTICATION_SCOPE) != null) {
            scope = arguments?.getString(AUTHENTICATION_SCOPE)
        }
        if (arguments?.getString(AUTHENTICATION_STATE) != null) {
            state = arguments?.getString(AUTHENTICATION_STATE)
        }

        _binding?.getAthleteBtn?.setOnClickListener {
            runningViewModel.getAthletes()
        }

        _binding?.getRunningActivityBtn?.setOnClickListener {
            runningViewModel.getRunningActivity(1212121212)

        }

        _binding?.authenticationBtn?.setOnClickListener {
            val intentUri = Uri.parse("https://www.strava.com/oauth/mobile/authorize")
                .buildUpon()
                .appendQueryParameter("client_id", APP_ID)
                .appendQueryParameter("redirect_uri", "com.fakhour.workoutin.workout.running://localhost")
                .appendQueryParameter("response_type", "code")
                .appendQueryParameter("approval_prompt", "auto")
                .appendQueryParameter("scope", "activity:write,read")
                .build()

            val intent = Intent(Intent.ACTION_VIEW, intentUri)
            startActivity(intent)
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
            }else{
                Log.d("TAG", "Athlete Null")

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

        runningViewModel.myResponseToken.observe(viewLifecycleOwner, Observer { token ->
            if (token != null) {
                RetrofitInstance.token=token.accessToken
                _binding?.contentToHide?.visibility=View.GONE
            }else{
                Log.d("TAG", "onViewCreated: ")

            }

        })


    }

}