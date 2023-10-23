package com.education.brcmeducorn.fragments.faculty_dashboard_fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.education.brcmeducorn.R
import com.education.brcmeducorn.fragments.admin_dashboard_fragments.AssignClassesFragment
import com.education.brcmeducorn.utils.SharedPrefs

class FacultyDashboardFragment : Fragment() {
    lateinit var llClasses: LinearLayout
    lateinit var llMarkAttendance: LinearLayout
    lateinit var llSendEvents: LinearLayout
    lateinit var llProfile: LinearLayout
    lateinit var llIdCard: LinearLayout
    lateinit var llSendAssignments: LinearLayout
    private lateinit var prefs: SharedPrefs
    private lateinit var textIdAndRole: TextView
    private lateinit var textName: TextView


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
        llClasses = view.findViewById(R.id.llClasses)
        llMarkAttendance = view.findViewById(R.id.llMarkAttendance)
        llSendEvents = view.findViewById(R.id.llSendEvents)
        llProfile = view.findViewById(R.id.llProfile)
        llIdCard = view.findViewById(R.id.llIdCard)
        llSendAssignments = view.findViewById(R.id.llSendAssignments)
        textName = view.findViewById(R.id.txtName)
        textIdAndRole = view.findViewById(R.id.txtIdAndRole)
        prefs = SharedPrefs(requireContext())
        //set data from shared prefs
        setDataTextView(textName, textIdAndRole)
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

        llProfile.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, FacultyProfileFragment())
                ?.commit()


        }

        llIdCard.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, FacultyIdCardFragment())
                ?.commit()


        }

        llSendAssignments.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, AssignmentFragment())
                ?.commit()


        }

    }

    private fun getPrefs(): Map<String, String> {
        val token = prefs.getString("token", "")
        val name = prefs.getString("name", "")
        val rollNo = prefs.getString("rollNo", "")
        val roll = prefs.getString("roll", "")

        return mapOf(
            "token" to token,
            "name" to name,
            "rollNo" to rollNo,
            "roll" to roll
        )
    }

    private fun setDataTextView(textName: TextView, textRollNo: TextView) {
        val userData = getPrefs()
//        val token = userData["token"]
        val name = userData["name"]?.uppercase()
        val rollNo = userData["rollNo"]
        val roll = userData["roll"]?.uppercase()

        textName.text = name
        textRollNo.text = "ID:$rollNo|$roll"
    }

}