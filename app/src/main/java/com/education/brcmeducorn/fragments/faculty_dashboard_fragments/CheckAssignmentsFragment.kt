package com.education.brcmeducorn.fragments.faculty_dashboard_fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.adapter.CheckAttendanceAdapter
import com.education.brcmeducorn.api.apiModels.Data
import com.education.brcmeducorn.api.apiModels.GetAssignmentReq
import com.education.brcmeducorn.api.apiModels.GetAssignmentRes
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.AppPreferences
import com.education.brcmeducorn.utils.ApiUtils
import com.education.brcmeducorn.utils.CustomProgressDialog
import com.education.brcmeducorn.utils.PublicVar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckAssignmentsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var assignmentTitleSpinner: Spinner
    private lateinit var assignmentAdapter: CheckAttendanceAdapter
    private var customProgressDialog: CustomProgressDialog? = null
    private var assignmentsList = mutableListOf<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_check_attendance, container, false)
        val prefs = AppPreferences(requireContext())
        recyclerView = view.findViewById(R.id.recycleViewAssignments)
        assignmentTitleSpinner = view.findViewById(R.id.assignmentTitleSpinner)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        CoroutineScope(Dispatchers.Main).launch {
            customProgressDialog = CustomProgressDialog(context)
            customProgressDialog!!.setMessage("wait loading data ...")
            customProgressDialog!!.show();
            val endpoint = "assignments/check"
            val method = "GET_ASSIGNMENTS_TO_CHECK"
            val studentAssignments =
                GetAssignmentReq(prefs.getSemester(), prefs.getBranch(), prefs.getRollNo())

            val result = ApiUtils.fetchData(endpoint, method, studentAssignments)

            if (result is GetAssignmentRes) {
                val assignmentData = result.data
                val assignments =
                    getCompletedAssignments(assignmentData) // Replace with your data source
                // Initialize the adapter with the valuable data
                assignmentAdapter = CheckAttendanceAdapter(assignments, requireContext(),activity?.supportFragmentManager?.beginTransaction())
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
            assignmentsList.add(assignment.title)
            assignment
        }
        getItemFromSpinner()
        val titleAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, assignmentsList)
        assignmentTitleSpinner.adapter = titleAdapter
        Log.d("anmol", processedList.toString())
        return processedList
    }
    private fun getItemFromSpinner() {
        assignmentTitleSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    PublicVar.setPosition(position)
                    assignmentAdapter.notifyDataSetChanged()

                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Kuch nahi karna
                }
            }
    }
}