package com.education.brcmeducorn.fragments.student_dashboard_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Gallery
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.education.brcmeducorn.fragments.AlumniMeetFragment
import com.education.brcmeducorn.fragments.EventsFragment
import com.education.brcmeducorn.fragments.IDCardFragment
import com.education.brcmeducorn.fragments.TimeTableFragment


class StudentDashboardFragment : Fragment() {
    lateinit var llAlumniMeet: LinearLayout
    lateinit var llEvents: LinearLayout
    lateinit var llGallery: LinearLayout
    lateinit var llResults: LinearLayout
    lateinit var llExams: LinearLayout
    lateinit var llIdCard: LinearLayout
    lateinit var llTimeTable: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(com.education.brcmeducorn.R.layout.fragment_student_dashboard, container, false)
//        Linked all the other fragment views in our fragment
        llAlumniMeet = view.findViewById(com.education.brcmeducorn.R.id.llAlumniMeet)
        llEvents = view.findViewById(com.education.brcmeducorn.R.id.llEvents)
        llExams = view.findViewById(com.education.brcmeducorn.R.id.llExams)
        llResults = view.findViewById(com.education.brcmeducorn.R.id.llResults)
        llGallery = view.findViewById(com.education.brcmeducorn.R.id.llGallery)
        llIdCard = view.findViewById(com.education.brcmeducorn.R.id.llIdCard)
        llTimeTable = view.findViewById(com.education.brcmeducorn.R.id.llTimeTable)

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
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, ExamsFragment())
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
    }


}