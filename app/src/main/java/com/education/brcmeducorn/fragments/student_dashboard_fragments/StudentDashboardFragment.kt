package com.education.brcmeducorn.fragments.student_dashboard_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.education.brcmeducorn.R
import com.education.brcmeducorn.api.ApiService
import com.education.brcmeducorn.fragments.AlumniMeetFragment
import com.education.brcmeducorn.fragments.EventsFragment
import com.education.brcmeducorn.fragments.IDCardFragment
import com.education.brcmeducorn.fragments.TimeTableFragment
import com.education.brcmeducorn.utils.ApiUtils
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StudentDashboardFragment : Fragment() {
    lateinit var llAlumniMeet: LinearLayout
    lateinit var llEvents: LinearLayout
    lateinit var llGallery: LinearLayout
    lateinit var llResults: LinearLayout
    lateinit var llExams: LinearLayout
    lateinit var llIdCard: LinearLayout
    lateinit var llTimeTable: LinearLayout
    lateinit var llAssignment: LinearLayout

    lateinit var llStudentProfile: LinearLayout
    lateinit var llTimeTable2: LinearLayout
    lateinit var llPYQs: LinearLayout
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_student_dashboard,
            container,
            false
        )
//        Linked all the other fragment views in our fragment
        llAlumniMeet = view.findViewById(R.id.llAlumniMeet)
        llEvents = view.findViewById(R.id.llEvents)
        llExams = view.findViewById(R.id.llExams)
        llResults = view.findViewById(R.id.llResults)
        llGallery = view.findViewById(R.id.llGallery)
        llIdCard = view.findViewById(R.id.llIdCard)
        llTimeTable = view.findViewById(R.id.llTimeTable)
        llAssignment = view.findViewById(R.id.llAssignment)
        llStudentProfile = view.findViewById(R.id.llstudentProfile)
        llTimeTable2 = view.findViewById(R.id.llTimeTable2)
        llPYQs = view.findViewById(R.id.llPYQs)
        textView = view.findViewById(R.id.txtEvents)


        CoroutineScope(Dispatchers.Main).launch {
            val endpoint = "categories"
            val method = "GET"
            val requestBody = null
            val result = ApiUtils.fetchData(endpoint, method, requestBody)
            textView.text = result
        }

// going from one fragment to another fragment
        handleClickListeners()

        return view
    }

    private fun handleClickListeners() {

        llEvents.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, EventsFragment())
                ?.commit()


        }
        llGallery.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, GalleryFragment())
                ?.commit()


        }
        llResults.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, ResultsFragment())
                ?.commit()


        }
        llAlumniMeet.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, AlumniMeetFragment())
                ?.commit()


        }
        llExams.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, SubjectsFragment())
                ?.commit()


        }
        llIdCard.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, IDCardFragment())
                ?.commit()


        }
        llTimeTable.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, TimeTableFragment())
                ?.commit()


        }
        llAssignment.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, AssignmentFragment())
                ?.commit()

        }
        llStudentProfile.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, StudentProfile())
                ?.commit()
        }

        llTimeTable2.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, TimeTable2Fragment())
                ?.commit()


        }

        llPYQs.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, FragmentPYQS())
                ?.commit()


        }


    }

}