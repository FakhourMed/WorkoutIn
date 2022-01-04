package com.fakhour.workoutin.workout.running

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
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
import com.fakhour.workoutin.R
import com.fakhour.workoutin.common.api.RetrofitInstance
import com.fakhour.workoutin.common.repository.APP_ID
import com.fakhour.workoutin.databinding.FragmentRunningBinding
import com.fakhour.workoutin.workout.entities.ActivityType
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

const val TOKEN = "package com.fakhour.workoutin.workout.running.TOKEN"

class RunningFragment : Fragment() {

    private var _binding: FragmentRunningBinding? = null
    var state: String? = null
    var code: String? = null
    var scope: String? = null


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
        var randomId: Long? = 0L

        if (arguments?.getString(AUTHENTICATION_CODE) != null) {
            code = arguments?.getString(AUTHENTICATION_CODE)
            _binding?.contentToHide?.visibility = View.VISIBLE
            _binding?.authenticationBtn?.visibility = View.GONE
            runningViewModel.authenticateFirstTime(code!!)

        }
        if (arguments?.getString(AUTHENTICATION_SCOPE) != null) {
            scope = arguments?.getString(AUTHENTICATION_SCOPE)
        }
        if (arguments?.getString(AUTHENTICATION_STATE) != null) {
            state = arguments?.getString(AUTHENTICATION_STATE)
        }

        if (RetrofitInstance.token != null) {
            _binding?.contentToHide?.visibility = View.VISIBLE
            _binding?.authenticationBtn?.visibility = View.GONE
        }
        _binding?.getAthleteBtn?.setOnClickListener {
            runningViewModel.getAthletes()
        }

        _binding?.getRunningActivityBtn?.setOnClickListener {
            if (randomId != null) {
                runningViewModel.getRunningActivity(randomId!!)
                Log.d("TAG", "onViewCreated:"+randomId)

            }

        }


        _binding?.createActivityBtn?.setOnClickListener {
            val tz = TimeZone.getTimeZone("UTC")
            val df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'") // Quoted "Z" to indicate UTC, no timezone offset

            df.setTimeZone(tz)
            val nowAsISO: String = df.format(Date())

            val activityType=when(_binding?.typeRadioGroup?.checkedRadioButtonId){
            R.id.radio_ride->ActivityType.RIDE
            R.id.radio_run->ActivityType.RUN
            R.id.radio_walk->ActivityType.WALK
else->ActivityType.RUN

            }

            runningViewModel.createActivity(_binding?.name?.text?.toString() ?: "",
                activityType,
                nowAsISO,
                if (_binding?.elapsedTime?.text?.toString().isNullOrBlank()) 0 else _binding?.elapsedTime?.text?.toString()?.toFloat()?.toInt() ?: 0,
                _binding?.description?.text?.toString() ?: "",
                if (_binding?.distance?.text?.toString().isNullOrBlank()) 0F else _binding?.distance?.text?.toString()?.toFloat() ?: 0F)
        }

        _binding?.getAthleteActivitiesBtn?.setOnClickListener {
            runningViewModel.getAthleteActivities()
        }

        _binding?.authenticationBtn?.setOnClickListener {
            val intentUri = Uri.parse("https://www.strava.com/oauth/mobile/authorize")
                .buildUpon()
                .appendQueryParameter("client_id", APP_ID)
                .appendQueryParameter("redirect_uri", "com.fakhour.workoutin.workout.running://localhost")
                .appendQueryParameter("response_type", "code")
                .appendQueryParameter("approval_prompt", "auto")
                .appendQueryParameter("scope", "activity:write,activity:read_all,activity:read")
                .build()
            val intent = Intent(Intent.ACTION_VIEW, intentUri)
            startActivity(intent)
        }

        runningViewModel.myResponseAthlete.observe(viewLifecycleOwner, Observer { athlete ->
            if (athlete != null) {
                _binding?.athleteId?.text =  getString(R.string.athlete_id)+ athlete.id
                _binding?.athleteFirstName?.text =  getString(R.string.athlete_firstname)+athlete.firstname
                _binding?.athleteLastName?.text =  getString(R.string.athlete_lastname)+athlete.lastname
                _binding?.athleteWeight?.text =  getString(R.string.athlete_weight)+ athlete.weight.toString()
            }

        })

        runningViewModel.myResponseCreateRunActivity.observe(viewLifecycleOwner, Observer { runActivity ->
            _binding?.name?.text?.clear()
            _binding?.distance?.text?.clear()
            _binding?.description?.text?.clear()
            _binding?.elapsedTime?.text?.clear()
            _binding?.radioRun?.isChecked=true

        })

        runningViewModel.myResponseGetRunActivity   .observe(viewLifecycleOwner, Observer { runActivity ->
            if (runActivity != null) {
                _binding?.getRunningActivityBtn?.visibility=View.GONE
                _binding?.randomActivityCard?.visibility=View.VISIBLE

                _binding?.runActivityName?.text = getString(R.string.name)+": " + runActivity.name
                _binding?.runActivityType?.text = getString(R.string.type)+": " + runActivity.typeEnum?.name
                _binding?.runActivityElapsedTime?.text =  getString(R.string.elapsed_time)+": " + runActivity.elapsedTime
                _binding?.runActivityDescription?.text =  getString(R.string.description)+": "+ runActivity.description
                _binding?.runActivityDistance?.text =  getString(R.string.distance)+": " + runActivity.distance
            }
        })

        runningViewModel.myResponseAthleteActivities.observe(viewLifecycleOwner, Observer { athleteActivitiesList ->
            if (athleteActivitiesList != null) {
                _binding?.randomActivity?.visibility=View.VISIBLE
                _binding?.randomActivityCard?.visibility=View.GONE

                _binding?.getRunningActivityBtn?.visibility=View.VISIBLE

                val temp=getRandomNumber(0, athleteActivitiesList!!.size)
                randomId = athleteActivitiesList[temp].id
            }
        })

        runningViewModel.myResponseToken.observe(viewLifecycleOwner, Observer { token ->
            if (token != null) {
                RetrofitInstance.token = token.accessToken

                val preferences = PreferenceManager.getDefaultSharedPreferences(context)
                val editor = preferences.edit()
                editor.putString(TOKEN, token.accessToken)
                editor.apply()


            }

        })


    }

    fun getRandomNumber(min: Int, max: Int): Int {
        return (Math.random() * (max - min) + min).toInt()
    }

}