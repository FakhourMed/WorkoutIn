package com.fakhour.workoutin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.fakhour.workoutin.workout.workout_list.WorkoutListViewModel
import com.fakhour.workoutin.workout.workout_sections.WorkoutSectionViewModel
import com.rbddevs.splashy.Splashy
import android.net.Uri
import android.preference.PreferenceManager
import androidx.navigation.findNavController
import com.fakhour.workoutin.common.api.RetrofitInstance
import com.fakhour.workoutin.workout.running.RunningFragment
import com.fakhour.workoutin.workout.running.TOKEN
import java.net.URLDecoder

import java.util.LinkedList

import java.util.LinkedHashMap

import java.io.UnsupportedEncodingException


const val IS_SPLASH_SCREEN_DISPLAYED = "package com.fakhour.workoutin.IS_SPLASH_SCREEN_DISPLAYED"
const val AUTHENTICATION_CODE = "package com.fakhour.workoutin.AUTHENTICATION_CODE"
const val AUTHENTICATION_SCOPE = "package com.fakhour.workoutin.AUTHENTICATION_SCOPE"
const val AUTHENTICATION_STATE = "package com.fakhour.workoutin.AUTHENTICATION_STATE"
var isSplashScreenDisplayed = false

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    private val workoutSectionViewModel: WorkoutSectionViewModel by lazy {
        ViewModelProvider(this).get(WorkoutSectionViewModel::class.java)
    }

    private val workoutListViewModel: WorkoutListViewModel by lazy {
        ViewModelProvider(this).get(WorkoutListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState?.getBoolean(IS_SPLASH_SCREEN_DISPLAYED) == false || savedInstanceState?.getBoolean(
                IS_SPLASH_SCREEN_DISPLAYED
            ) == null
        ) {
            //setSplashy()
        }
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)

        if(preferences.getString(TOKEN,null)!=null){
            RetrofitInstance.token=preferences.getString(TOKEN,null)
        }

        setContentView(R.layout.activity_main)
        mainActivityViewModel.populateDatabase(workoutSectionViewModel, workoutListViewModel)


        if (intent.data != null) {
            val data = intent.data

            var result: Map<String, MutableList<String?>>? = splitQuery(data!!)
            val state = result?.get("state")?.get(0)
            val code = result?.get("code")?.get(0)
            val scope = result?.get("scope")?.get(0)
            var bundle = Bundle()
            bundle.putString(AUTHENTICATION_STATE, state)
            bundle.putString(AUTHENTICATION_CODE, code)
            bundle.putString(AUTHENTICATION_SCOPE, scope)


            findNavController(R.id.my_nav_host_fragment).navigate(R.id.action_workout_section_to_running, bundle)

        }

    }

    @Throws(UnsupportedEncodingException::class)
    fun splitQuery(url: Uri): Map<String, MutableList<String?>>? {
        val query_pairs: MutableMap<String, MutableList<String?>> = LinkedHashMap()
        val pairs = url.query?.split("&".toRegex())?.toTypedArray()
        if (pairs != null) {
            for (pair in pairs) {
                val idx = pair.indexOf("=")
                val key = if (idx > 0) URLDecoder.decode(pair.substring(0, idx), "UTF-8") else pair
                if (!query_pairs.containsKey(key)) {
                    query_pairs[key] = LinkedList()
                }
                val value = if (idx > 0 && pair.length > idx + 1) URLDecoder.decode(
                    pair.substring(idx + 1),
                    "UTF-8"
                ) else null
                query_pairs[key]!!.add(value)
            }
        }
        return query_pairs
    }

    fun setSplashy() {
        Splashy(this)
            .setLogo(R.drawable.ic_logo)
            .setTitle("WorkoutIN")
            .setTitleColor("#FFFFFF")
            .setSubTitle("Home workout made easy")
            .setProgressColor(R.color.white)
            .setBackgroundResource(R.mipmap.background)
            .setFullScreen(true)
            .setDuration(2000)
            .show()

        isSplashScreenDisplayed = true
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putBoolean(IS_SPLASH_SCREEN_DISPLAYED, isSplashScreenDisplayed)

    }
}