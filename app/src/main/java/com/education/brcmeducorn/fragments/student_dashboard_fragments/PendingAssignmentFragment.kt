package com.education.brcmeducorn.fragments.student_dashboard_fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.adapter.PendingAssignmentAdapter
import com.education.brcmeducorn.api.apiModels.Data
import com.education.brcmeducorn.api.apiModels.GetAssignmentReq
import com.education.brcmeducorn.api.apiModels.GetAssignmentRes
import com.education.brcmeducorn.utils.ApiUtils
import com.education.brcmeducorn.utils.RealPathUtil
import com.education.brcmeducorn.utils.SharedPrefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PendingAssignmentFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private var selectedPdf: Uri? = null
    private lateinit var pdfPath: String
    private lateinit var assignmentAdapter: PendingAssignmentAdapter
    private lateinit var prefs: SharedPrefs

    private val pickPdfLauncher: ActivityResultLauncher<Intent> =
        this.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                if (data != null) {
                    selectedPdf = data.data!!
                    pdfPath = RealPathUtil.getRealPath(this.requireContext(), selectedPdf!!).toString()
                    Log.d("path", pdfPath)
                    SharedPrefs(requireContext()).saveString("pdfPath", pdfPath)
                    assignmentAdapter.updateTextView()
                    // Do something with the selected PDF Uri (e.g., save it to the assignment model)
                    // ...
                }
            }
        }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_pending_assignment, container, false)

        // Assuming assignments is a list of AssignmentModel objects
        prefs = SharedPrefs(requireContext())
        recyclerView = view.findViewById(R.id.recyclerViewPendingAssignments)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val userData = getPrefs()


        CoroutineScope(Dispatchers.Main).launch {
            val endpoint = "assignment"
            val method = "GET_ASSIGNMENTS"
            val studentAssignments = GetAssignmentReq(userData["semester"]?:"",userData["branch"]?:"",userData["rollNo"]?:"")
            val result = ApiUtils.fetchData(endpoint, method, studentAssignments)

            if (result is GetAssignmentRes) {
                val assignmentData=result.data
                val assignments = getPendingAssignments(assignmentData) // Replace with your data source

                assignmentAdapter = PendingAssignmentAdapter(assignments,pickPdfLauncher,requireContext())
                recyclerView.adapter = assignmentAdapter
                if (!result.success) {
                    Toast.makeText(
                        requireContext(),
                        "something error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        return view
    }

    private fun getPendingAssignments(assignmentData: List<Data>): List<Data> {
        // Filter assignments with 'pending' status
        return assignmentData.filter { assignment ->
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

            // Return true only if the assignment has 'pending' status
            assignment.status == "pending"
        }
    }
    private fun getPrefs(): Map<String, String> {
        val name = prefs.getString("name", "")
        val rollNo = prefs.getString("rollNo", "")
        val semester = prefs.getString("semester", "")


        return mapOf(
            "name" to name,
            "rollNo" to rollNo,
            "semester" to semester,

        )
    }
}
