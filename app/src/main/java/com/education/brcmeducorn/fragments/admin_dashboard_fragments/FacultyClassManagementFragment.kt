package com.education.brcmeducorn.fragments.admin_dashboard_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.adapter.FacultyManagementAdapter
import com.education.brcmeducorn.model.FreeTeacher
import com.education.brcmeducorn.model.FreeClassAndPeriod
import com.education.brcmeducorn.model.AbsentTeacher
import com.education.brcmeducorn.utils.FacultyClassManagement

class FacultyClassManagementFragment : Fragment() {

    private lateinit var system: FacultyClassManagement
    private lateinit var freeTeachersList: MutableList<FreeTeacher>
    private lateinit var absentTeachersList: MutableList<AbsentTeacher>
    private lateinit var availablePeriodsList: MutableList<FreeClassAndPeriod>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FacultyManagementAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_faculty_class_management, container, false)

        recyclerView = view.findViewById(R.id.recycler_view_faculty)

        freeTeachersList = mutableListOf(
            FreeTeacher("Amit Ranjan"),
            FreeTeacher("Surender")
        )

        absentTeachersList = mutableListOf(
            AbsentTeacher("Dinesh"),
            AbsentTeacher("Neha")
        )

        availablePeriodsList = mutableListOf(
            FreeClassAndPeriod("3st sem", "1st p", "09:00", "10:00"),
            FreeClassAndPeriod("7th sem", "5th p", "11:00", "12:00")
        )

        adapter = FacultyManagementAdapter(freeTeachersList, absentTeachersList, availablePeriodsList,requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        system = FacultyClassManagement()
        system.identifyFreeTeachers()
        system.identifyAbsentTeachers()
        system.checkEmptyPeriods()
        system.assignPeriods()

        return view
    }
}
