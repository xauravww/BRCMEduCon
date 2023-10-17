package com.education.brcmeducorn.fragments.admin_dashboard_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.education.brcmeducorn.R
import com.education.brcmeducorn.fragments.EventsFragment
import com.education.brcmeducorn.fragments.SendEventsFragment

class AdminDashboardFragment : Fragment() {
lateinit var llAddOrRemoveMembers:LinearLayout
lateinit var llSendEvents:LinearLayout
lateinit var llAssignClasses:LinearLayout
lateinit var llFacultyManage:LinearLayout
lateinit var llEditMember:LinearLayout
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
        llAddOrRemoveMembers = view.findViewById(R.id.llAddOrRemoveMembers)
        llSendEvents = view.findViewById(R.id.llSendEvents)
        llAssignClasses = view.findViewById(R.id.llAssignClasses)
        llFacultyManage = view.findViewById(R.id.llFacultyManage)
        llEditMember = view.findViewById(R.id.llEditMember)
        // going from one fragment to another fragment
        handleClickListeners()

        return view
    }

    private fun handleClickListeners() {
        llAddOrRemoveMembers.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, AddOrRemoveMembersFragment())
                ?.commit()


        }
        llSendEvents.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, SendEventsFragment())
                ?.commit()


        }
        llAssignClasses.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, AssignClassesFragment())
                ?.commit()
        }
        llFacultyManage.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, FacultyClassManagementFragment())
                ?.commit()
        }
        llEditMember.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, EditMemberFragment())
                ?.commit()
        }
    }


}