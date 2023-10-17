package com.education.brcmeducorn.fragments.student_dashboard_fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.adapter.ViewAssignmentAdapter
import com.education.brcmeducorn.model.AssignmentModel

class ViewAssignmentFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var assignmentAdapter: ViewAssignmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_view_assignment, container, false)

        // Assuming assignments is a list of AssignmentModel objects
        val assignments = getCompletedAssignments() // Replace with your data source

        recyclerView = view.findViewById(R.id.recyclerViewAssignment)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize the adapter with the valuable data
        assignmentAdapter = ViewAssignmentAdapter(assignments,requireContext())
        recyclerView.adapter = assignmentAdapter

        return view
    }

    private fun getCompletedAssignments(): List<AssignmentModel> {
        // Replace this function with your actual data source
        return listOf(
            AssignmentModel(
                title = "Assignment 3",
                description = "Description for Assignment 3",
                givenDate = "03/10/2023",
                dueDate = "16/10/2023",
                studentName = "John Doe",
                studentRollNo = "A12345",
                teacherName = "Mrs. Smith",
                subject = "Math",
                status = "Submitted",
                attachment = "MeTitle.pdf",
                feedback = "Good job!",
                grades = "B+",
                submissionDate = "15/10/2023",
                lateSubmission = false,
                priority = "High",
                tags = listOf("Tag1", "Tag2"),
                semester = "Semester 1"
            ),
            AssignmentModel(
                title = "Assignment 3",
                description = "Description for Assignment 4",
                givenDate = "04/10/2023",
                dueDate = "17/10/2023",
                studentName = "SauravTitle.psf",
                studentRollNo = "B12345",
                teacherName = "Mr. Amit Ranjan",
                subject = "dsa",
                status = "Pending",
                attachment = "file2.pdf",
                feedback = "submit first",
                grades = "A",
                submissionDate = "16/10/2023",
                lateSubmission = false,
                priority = "Medium",
                tags = listOf("Tag1", "Tag2", "Tag3"),
                semester = "Semester 1"
            ),
            AssignmentModel(
                title = "Assignment 4",
                description = "Description for Assignment 4",
                givenDate = "04/10/2023",
                dueDate = "17/10/2023",
                studentName = "Hansh",
                studentRollNo = "B12345",
                teacherName = "Mr. Amit Ranjan",
                subject = "Science",
                status = "Pending",
                attachment = "Hansh_title.pdf",
                feedback = "please submit",
                grades = "A-",
                submissionDate = "16/10/2023",
                lateSubmission = false,
                priority = "Medium",
                tags = listOf("Tag1", "Tag2", "Tag3"),
                semester = "Semester 1"
            ),
            AssignmentModel(
                title = "Assignment 5",
                description = "Description for Assignment 4",
                givenDate = "04/10/2023",
                dueDate = "17/10/2023",
                studentName = "Mr. Amit Ranjan e",
                studentRollNo = "B12345",
                teacherName = "Hemant",
                subject = "Science",
                status = "Submitted",
                attachment = "Hemant_title.pdf",
                feedback = "Well done!",
                grades = "B",
                submissionDate = "16/10/2023",
                lateSubmission = false,
                priority = "Medium",
                tags = listOf("Tag1", "Tag2", "Tag3"),
                semester = "Semester 1"
            )
        )
    }
}
