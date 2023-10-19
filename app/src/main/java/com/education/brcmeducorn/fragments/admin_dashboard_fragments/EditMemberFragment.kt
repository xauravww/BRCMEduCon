package com.education.brcmeducorn.fragments.admin_dashboard_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.education.brcmeducorn.R
import com.education.brcmeducorn.adapter.AssignmentPagerAdapter
import com.education.brcmeducorn.fragments.student_dashboard_fragments.PendingAssignmentFragment
import com.education.brcmeducorn.fragments.student_dashboard_fragments.ViewAssignmentFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class EditMemberFragment : Fragment() {
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_member, container, false)

        viewPager = view.findViewById(R.id.viewPager)
        tabLayout = view.findViewById(R.id.tabLayout)

        viewPager.adapter = AssignmentPagerAdapter(childFragmentManager, lifecycle,listOf(EditFacultyFragment(), EditStudentFragment()))

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Edit Faculty"
                1 -> tab.text = "Edit Student"
            }
        }.attach()

        return view  }


}