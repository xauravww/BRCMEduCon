package com.education.brcmeducorn.fragments.student_dashboard_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.education.brcmeducorn.R
import com.education.brcmeducorn.fragments.AlumniMeetFragment
import com.education.brcmeducorn.fragments.EventsFragment
import com.education.brcmeducorn.fragments.IDCardFragment
import com.education.brcmeducorn.fragments.TimeTableFragment
import com.education.brcmeducorn.utils.SharedPrefs
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso


class StudentDashboardFragment : Fragment() {
    private lateinit var llAlumniMeet: LinearLayout
    private lateinit var llEvents: LinearLayout
    private lateinit var llGallery: LinearLayout
    private lateinit var llResults: LinearLayout
    private lateinit var llExams: LinearLayout
    private lateinit var llIdCard: LinearLayout
    private lateinit var llTimeTable: LinearLayout
    private lateinit var llAssignment: LinearLayout
    private lateinit var llStudentProfile: LinearLayout
    private lateinit var llTimeTable2: LinearLayout
    private lateinit var llPYQs: LinearLayout
    private lateinit var textView: TextView
    private lateinit var textName: TextView
    private lateinit var textRollNo: TextView
    private lateinit var imgStudent: ShapeableImageView
    private lateinit var prefs: SharedPrefs

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
        imgStudent = view.findViewById(R.id.imgProfile)
        llPYQs = view.findViewById(R.id.llPYQs)
        textView = view.findViewById(R.id.txtEvents)
        textName = view.findViewById(R.id.txtName)
        textRollNo = view.findViewById(R.id.txtIdAndRole)
        prefs = SharedPrefs(requireContext())

        //set data from shared prefs
        setDataView(textName, textRollNo,imgStudent)
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

    private fun getPrefs(): Map<String, String> {
        val token = prefs.getString("token", "")
        val name = prefs.getString("name", "")
        val rollNo = prefs.getString("rollNo", "")
        val roll = prefs.getString("roll", "")
        val imageUrl = prefs.getString("imageUrl", "")

        return mapOf(
            "token" to token,
            "name" to name,
            "rollNo" to rollNo,
            "roll" to roll,
            "imageUrl" to imageUrl
        )
    }

    private fun setDataView(textName: TextView, textRollNo: TextView,imgStudent:ShapeableImageView) {
        val userData = getPrefs()
//        val token = userData["token"]
        val name = userData["name"]?.uppercase()
        val rollNo = userData["rollNo"]
        val roll = userData["roll"]?.uppercase()
        val imageUrl = userData["imageUrl"]

        textName.text = name
        textRollNo.text = "ID:$rollNo|$roll"
        Picasso.get().load(imageUrl).into(imgStudent)

    }
}