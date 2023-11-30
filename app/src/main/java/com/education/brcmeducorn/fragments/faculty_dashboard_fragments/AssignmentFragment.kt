package com.education.brcmeducorn.fragments.faculty_dashboard_fragments

 import android.os.Bundle
 import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.education.brcmeducorn.R
import com.education.brcmeducorn.adapter.AssignmentPagerAdapter
 import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class AssignmentFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_assignment, container, false)

        viewPager = view.findViewById(R.id.viewPager)
        tabLayout = view.findViewById(R.id.tabLayout)

        viewPager.adapter = AssignmentPagerAdapter(childFragmentManager, lifecycle,listOf(
            FacultySendAssignmentsFragment(), CheckAssignmentsFragment()
        ))

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Send Assignment"
                1 -> tab.text = "Check Assignment"
            }
        }.attach()

        return view
    }
}

