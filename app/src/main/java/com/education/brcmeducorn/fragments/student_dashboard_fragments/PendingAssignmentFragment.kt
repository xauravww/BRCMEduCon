package com.education.brcmeducorn.fragments.student_dashboard_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.adapter.PendingAssignmentAdapter
import com.education.brcmeducorn.model.AssignmentModel

class PendingAssignmentFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var assignmentAdapter: PendingAssignmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_pending_assignment, container, false)

        // Assuming assignments is a list of AssignmentModel objects
        val assignments = getPendingAssignments() // Replace with your data source

        recyclerView = view.findViewById(R.id.recyclerViewPendingAssignments)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize the adapter with the valuable data
        assignmentAdapter = PendingAssignmentAdapter(assignments, this)
        recyclerView.adapter = assignmentAdapter

        return view
    }

    private fun getPendingAssignments(): List<AssignmentModel> {
        // Replace this function with your actual data source
        return listOf(
            AssignmentModel(
                title = "Assignment 1",
                description = "Description for Assignment 1",
                givenDate = "01/10/2023",
                dueDate = "14/10/2023",
                studentName = "John Doe",
                studentRollNo = "A12345",
                teacherName = "Mrs. Smith",
                subject = "Math",
                status = "Pending",
                attachment = "file.pdf",
                feedback = null,
                grades = null,
                submissionDate = null,
                lateSubmission = false,
                priority = "High",
                tags = listOf("Tag1", "Tag2"),
                semester = "Semester 1"
            ),
            AssignmentModel(
                title = "Assignment 2",
                description = "Description for Assignment 2",
                givenDate = "02/10/2023",
                dueDate = "15/10/2023",
                studentName = "Jane Doe",
                studentRollNo = "B12345",
                teacherName = "Mr. Johnson",
                subject = "Science",
                status = "Pending",
                attachment = null,
                feedback = null,
                grades = null,
                submissionDate = null,
                lateSubmission = false,
                priority = "Medium",
                tags = listOf("Tag1", "Tag2", "Tag3"),
                semester = "Semester 1"
            ),
            AssignmentModel(
                title = "Assignment 3",
                description = "Description for Assignment 2",
                givenDate = "02/10/2023",
                dueDate = "15/10/2023",
                studentName = "Jane Doe",
                studentRollNo = "B12345",
                teacherName = "Mr. Johnson",
                subject = "Science",
                status = "Pending",
                attachment = null,
                feedback = null,
                grades = null,
                submissionDate = null,
                lateSubmission = false,
                priority = "Medium",
                tags = listOf("Tag1", "Tag2", "Tag3"),
                semester = "Semester 1"
            ),
            AssignmentModel(
                title = "Assignment 3",
                description = "Description for Assignment 2",
                givenDate = "02/10/2023",
                dueDate = "15/10/2023",
                studentName = "Jane Doe",
                studentRollNo = "B12345",
                teacherName = "Mr. Johnson",
                subject = "Science",
                status = "Pending",
                attachment = null,
                feedback = null,
                grades = null,
                submissionDate = null,
                lateSubmission = false,
                priority = "Medium",
                tags = listOf("Tag1", "Tag2", "Tag3"),
                semester = "Semester 1"
            )
        )
    }
}
