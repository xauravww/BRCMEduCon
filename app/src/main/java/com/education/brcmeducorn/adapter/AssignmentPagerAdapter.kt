package com.education.brcmeducorn.adapter

import androidx.fragment.app.Fragment
 import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.education.brcmeducorn.fragments.student_dashboard_fragments.ViewAssignmentFragment
import com.education.brcmeducorn.fragments.student_dashboard_fragments.PendingAssignmentFragment

class AssignmentPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ViewAssignmentFragment()
            else -> PendingAssignmentFragment()
        }
    }
}

