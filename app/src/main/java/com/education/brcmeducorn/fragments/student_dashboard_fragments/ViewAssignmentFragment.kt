package com.education.brcmeducorn.fragments.student_dashboard_fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.adapter.ViewAssignmentAdapter
import com.education.brcmeducorn.api.apiModels.Data
import com.education.brcmeducorn.api.apiModels.GetAssignmentReq
import com.education.brcmeducorn.api.apiModels.GetAssignmentRes
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.AppPreferences
import com.education.brcmeducorn.utils.ApiUtils
import com.education.brcmeducorn.utils.CustomProgressDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewAssignmentFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var assignmentAdapter: ViewAssignmentAdapter
    private var customProgressDialog: CustomProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_view_assignment, container, false)

        // Assuming assignments is a list of AssignmentModel objects
        val prefs = AppPreferences(requireContext())
        recyclerView = view.findViewById(R.id.recyclerViewAssignment)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        CoroutineScope(Dispatchers.Main).launch {
            customProgressDialog = CustomProgressDialog(context)
            customProgressDialog!!.setMessage("wait loading data ...")
            customProgressDialog!!.show();
            val endpoint = "assignment"
            val method = "GET_ASSIGNMENTS"
            val studentAssignments =
                GetAssignmentReq(prefs.getSemester(), prefs.getBranch(), prefs.getRollNo())

            val result = ApiUtils.fetchData(endpoint, method, studentAssignments)

            if (result is GetAssignmentRes) {
                val assignmentData = result.data
                val assignments =
                    getCompletedAssignments(assignmentData) // Replace with your data source
                // Initialize the adapter with the valuable data
                assignmentAdapter = ViewAssignmentAdapter(assignments, requireContext())
                recyclerView.adapter = assignmentAdapter
                if (!result.success) {
                    Toast.makeText(
                        requireContext(),
                        "something error",
                        Toast.LENGTH_SHORT
                    ).show()
                    customProgressDialog!!.dismiss()

                }
                customProgressDialog!!.dismiss()

            } else {
                Toast.makeText(
                    requireContext(),
                    "something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
                customProgressDialog!!.dismiss()

            }

        }

        return view
    }

    private fun getCompletedAssignments(assignmentData: List<Data>): List<Data> {
        // Replace this function with your actual data source
        val processedList = assignmentData.map { assignment ->
            val submissions = assignment.submissions

            // Check if there are submissions
            if (submissions.isNotEmpty()) {
                val hasRollNumber = submissions.any { it.studentRollNo != null }
                // Set status based on the presence of a roll number
                assignment.status = if (hasRollNumber) "submitted" else "pending"
            } else {
                // If there are no submissions, set status to 'pending'
                assignment.status = "pending"
            }

            assignment
        }
        Log.d("anmol", processedList.toString())
        return processedList
    }
}
