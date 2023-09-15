package com.education.brcmeducorn.fragments.faculty_dashboard_fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.education.brcmeducorn.R
import com.education.brcmeducorn.fragments.admin_dashboard_fragments.AssignClassesFragment

class FacultyDashboardFragment : Fragment() {
lateinit var llClasses:LinearLayout
lateinit var llMarkAttendance:LinearLayout
lateinit var llSendEvents:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_faculty_dashboard, container, false)
        //        Linked all the other fragment views in our fragment
        llClasses = view.findViewById(com.education.brcmeducorn.R.id.llClasses)
        llMarkAttendance = view.findViewById(com.education.brcmeducorn.R.id.llMarkAttendance)
        llSendEvents = view.findViewById(com.education.brcmeducorn.R.id.llSendEvents)

        // going from one fragment to another fragment
        handleClickListeners()

        return view
    }

    private fun handleClickListeners() {
        llClasses.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, ClassesFragment())
                ?.commit()


        }
        llMarkAttendance.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, MarkAttendanceFragment())
                ?.commit()


        }
        llSendEvents.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, AssignClassesFragment())
                ?.commit()


        }

    }


}