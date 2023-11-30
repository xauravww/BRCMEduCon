package com.education.brcmeducorn.fragments.admin_dashboard_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.education.brcmeducorn.R
import com.education.brcmeducorn.fragments.SendEventsFragment
import com.education.brcmeducorn.utils.SharedPrefs

class AdminDashboardFragment : Fragment() {
    private lateinit var llAddOrRemoveMembers: LinearLayout
    private lateinit var llSendEvents: LinearLayout
    private lateinit var llAssignClasses: LinearLayout
    private lateinit var llFacultyManage: LinearLayout
    private lateinit var llEditMember: LinearLayout
    private lateinit var llAddGallery: LinearLayout
    private lateinit var prefs: SharedPrefs
    private lateinit var textIdAndRole: TextView
    private lateinit var textName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_admin_dashboard, container, false)

        //        Linked all the other fragment views in our fragment
        llAddOrRemoveMembers = view.findViewById(R.id.llVerifyMembers)
        llSendEvents = view.findViewById(R.id.llSendEvents)
        llAssignClasses = view.findViewById(R.id.llAssignClasses)
        llFacultyManage = view.findViewById(R.id.llFacultyManage)
        llEditMember = view.findViewById(R.id.llEditMember)
        llAddGallery = view.findViewById(R.id.llAddGallery)
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
        llAddOrRemoveMembers.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.frameLayout, VerifyMembersFragment())
                ?.commit()


        }
        llSendEvents.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.frameLayout, SendEventsFragment())
                ?.commit()


        }
        llAssignClasses.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.frameLayout, AssignClassesFragment())
                ?.commit()
        }
        llFacultyManage.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.frameLayout,
                    FacultyClassManagementFragment()
                )
                ?.commit()
        }
        llEditMember.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.frameLayout, EditMemberFragment())
                ?.commit()
        }
        llAddGallery.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.frameLayout, AddGalleryFragment())
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