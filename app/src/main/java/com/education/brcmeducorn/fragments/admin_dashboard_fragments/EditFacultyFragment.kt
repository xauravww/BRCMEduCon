package com.education.brcmeducorn.fragments.admin_dashboard_fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.adapter.EditFacultyAdapter
import com.education.brcmeducorn.model.FacultyModel

class EditFacultyFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EditFacultyAdapter
    private lateinit var searchView: SearchView
    private val facultyList: ArrayList<FacultyModel> = ArrayList()
    private val updatedFacultyList: ArrayList<FacultyModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_faculty, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        searchView = view.findViewById(R.id.textSearch)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText ?: "")
                return true
            }
        })

        addSampleFacultyData()

//        adapter = EditFacultyAdapter(requireContext(), updatedFacultyList)
//        recyclerView.adapter = adapter

        return view
    }

    private fun filter(query: String) {
        updatedFacultyList.clear()
        val lowerCaseQuery = query.lowercase()
        for (faculty in facultyList) {
            if (faculty.name.lowercase().contains(lowerCaseQuery)) {
                updatedFacultyList.add(faculty)
            }
        }
        adapter = EditFacultyAdapter(requireContext(), updatedFacultyList)
        recyclerView.adapter = adapter
    }

    private fun addSampleFacultyData() {
        facultyList.apply {
            add(
                FacultyModel(
                    "hansh4358",
                    "a@gmail.com",
                    "123456789",
                    "F001",
                    "Math",
                    "5 years",
                    "123 Main St"
                )
            )
            add(
                FacultyModel(
                    "saurav4362",
                    "amit@gmail.com",
                    "123456789",
                    "F001",
                    "Math",
                    "5 years",
                    "123 Main St"
                )
            )
            add(
                FacultyModel(
                    "hemant4372",
                    "amite@gmail.com",
                    "123456789",
                    "F001",
                    "Math",
                    "5 years",
                    "123 Main St"
                )
            )
            add(
                FacultyModel(
                    "anmol",
                    "amite@gmail.com",
                    "123456789",
                    "F001",
                    "Math",
                    "5 years",
                    "123 Main St"
                )
            )
            add(
                FacultyModel(
                    "yashi",
                    "amite@gmail.com",
                    "123456789",
                    "F001",
                    "Math",
                    "5 years",
                    "123 Main St"
                )
            )
            add(
                FacultyModel(
                    "reason",
                    "amite@gmail.com",
                    "123456789",
                    "F001",
                    "Math",
                    "5 years",
                    "123 Main St"
                )
            )
            add(
                FacultyModel(
                    "Harshit",
                    "amite@gmail.com",
                    "987654321",
                    "F002",
                    "Science",
                    "3 years",
                    "456 Oak St"
                )
            )
        }
    }

}
