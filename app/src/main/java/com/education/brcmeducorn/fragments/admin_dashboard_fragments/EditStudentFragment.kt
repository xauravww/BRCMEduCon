package com.education.brcmeducorn.fragments.admin_dashboard_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.adapter.EditStudentAdapter
import com.education.brcmeducorn.model.StudentModel

class EditStudentFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var adapter: EditStudentAdapter
    private val studentList = ArrayList<StudentModel>()
    private val updatedFacultyList: ArrayList<StudentModel> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_student, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        searchView = view.findViewById(R.id.textSearch)

        addStudent()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        adapter = EditStudentAdapter(requireContext(), studentList)
//        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText ?: "")
                return true
            }
        })


        return view
    }

    private fun filter(query: String) {
        updatedFacultyList.clear()
        val lowerCaseQuery = query.lowercase()
        for (faculty in studentList) {
            if (faculty.name.lowercase().contains(lowerCaseQuery)) {
                updatedFacultyList.add(faculty)
            }
        }
        adapter = EditStudentAdapter(requireContext(), updatedFacultyList)
        recyclerView.adapter = adapter
    }

    private fun addStudent() {
        studentList.apply {
            add(
                StudentModel(
                    "Hansh",
                    "jmjgd@example.com",
                    "1234567890",
                    "S001",
                    "Math",
                    "1st Semester",
                    "123 Main St",
                    "ggg",
                    "13/00/0000"
                )
            )
            add(
                StudentModel(
                    "Saurav",
                    "jmjgd@example.com",
                    "1234567890",
                    "S001",
                    "Math",
                    "1st Semester",
                    "123 Main St",
                    "ggg",
                    "13/00/0000"
                )
            )
            add(
                StudentModel(
                    "Hemant",
                    "jmjgd@example.com",
                    "1234567890",
                    "S001",
                    "Math",
                    "1st Semester",
                    "123 Main St",
                    "ggg",
                    "13/00/0000"
                )
            )
            add(
                StudentModel(
                    "harshit",
                    "jmjgd@example.com",
                    "1234567890",
                    "S001",
                    "Math",
                    "1st Semester",
                    "123 Main St",
                    "ggg",
                    "13/00/0000"
                )
            )
            add(
                StudentModel(
                    "Abhishek",
                    "jmjgd@example.com",
                    "1234567890",
                    "S001",
                    "Math",
                    "1st Semester",
                    "123 Main St",
                    "ggg",
                    "13/00/0000"
                )
            )
            add(
                StudentModel(
                    "Aashu",
                    "jmjgd@example.com",
                    "1234567890",
                    "S001",
                    "Math",
                    "1st Semester",
                    "123 Main St",
                    "ggg",
                    "13/00/0000"
                )
            )
            add(
                StudentModel(
                    "Vashu",
                    "jmjgd@example.com",
                    "1234567890",
                    "S001",
                    "Math",
                    "1st Semester",
                    "123 Main St",
                    "ggg",
                    "13/00/0000"
                )
            )

        }
    }
}
