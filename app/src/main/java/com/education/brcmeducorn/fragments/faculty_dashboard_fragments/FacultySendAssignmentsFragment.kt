package com.education.brcmeducorn.fragments.faculty_dashboard_fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.education.brcmeducorn.R
import com.education.brcmeducorn.api.apiModels.CreateAssignmentReq
import com.education.brcmeducorn.api.apiModels.Success
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.AppPreferences
import com.education.brcmeducorn.utils.ApiUtils
import com.education.brcmeducorn.utils.SharedPrefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FacultySendAssignmentsFragment : Fragment() {
    private var editTextAssignmentTitle: EditText? = null
    private var editTextAssignmentDescription: EditText? = null
    private var editTextAssignmentSubject: EditText? = null
    private var textViewAssignmentGivenDate: TextView? = null
    private var textViewAssignmentDueDate: TextView? = null
    private var givenCalendar: Calendar? = null
    private var dueCalendar: Calendar? = null
    private lateinit var prefs: SharedPrefs
    private lateinit var txtSemester: Spinner
    var selectedSemester: String = "sem"

    private var semesterArray = arrayOf(
        "Semester", "Sem1", "Sem2", "Sem3", "Sem4", "Sem5", "Sem6", "Sem7", "Sem8"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =
            inflater.inflate(R.layout.fragment_assignment_faculty_create, container, false)

        editTextAssignmentTitle = view.findViewById(R.id.editTextAssignmentTitle)
        editTextAssignmentDescription = view.findViewById(R.id.editTextAssignmentDescription)
        textViewAssignmentGivenDate = view.findViewById(R.id.textViewAssignmentGivenDate)
        textViewAssignmentDueDate = view.findViewById(R.id.textViewAssignmentDueDate)
        editTextAssignmentSubject = view.findViewById(R.id.editTextAssignmentSubject)
        txtSemester = view.findViewById(R.id.txtSemester)

        prefs = SharedPrefs(requireContext())

        val buttonPickGivenDate = view.findViewById<Button>(R.id.buttonPickGivenDate)
        val buttonPickDueDate = view.findViewById<Button>(R.id.buttonPickDueDate)
        val buttonSubmitAssignment = view.findViewById<Button>(R.id.buttonSubmitAssignment)
        val semAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, semesterArray)
        getItemFromSpinner(semesterArray)
        txtSemester.adapter = semAdapter

        buttonPickGivenDate.setOnClickListener {
            showDatePickerDialog(true)
        }

        buttonPickDueDate.setOnClickListener {
            showDatePickerDialog(false)
        }

        buttonSubmitAssignment.setOnClickListener {
            val title = editTextAssignmentTitle?.text.toString()
            val description = editTextAssignmentDescription?.text.toString()
            val givenDate = textViewAssignmentGivenDate?.text.toString()
            val dueDate = textViewAssignmentDueDate?.text.toString()
            val subject = editTextAssignmentSubject?.text.toString()

            val prefs = AppPreferences(requireContext())

            CoroutineScope(Dispatchers.Main).launch {
                val endpoint = "faculty/assignment"
                val method = "ASSIGNMENT_CREATE"
                val createAssignmentReqBody = CreateAssignmentReq(
                    description = description,
                    dueDate = dueDate,
                    branch = prefs.getBranch(),
                    givenDate = givenDate,
                    grades = 90,
                    lateSubmission = false,
                    priority = "High",
                    semester = selectedSemester,
                    status = "pending",
                    studentName = "No name",
                    studentRollNo = "no roll No",
                    subject = subject,
                    submissionDate = "2023-11-09",
                    tags = listOf("tag1", "tag2", "tag3"),
                    teacherName = prefs.getName(),
                    teacherId = prefs.getRollNo(),
                    title = title,
                    token = prefs.getToken()
                )
                val result = ApiUtils.fetchData(endpoint, method, createAssignmentReqBody)

                if (result is Success) {
                    Log.d("Assignment Create Res", result.toString())
                    if (result.success) {
                        Toast.makeText(
                            requireContext(),
                            "your assignment was created",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "error something",
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

        }

        return view
    }

    private fun showDatePickerDialog(isGivenDate: Boolean) {
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(year, month, dayOfMonth)

                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

                if (isGivenDate) {
                    givenCalendar = selectedCalendar
                    textViewAssignmentGivenDate?.text = sdf.format(selectedCalendar.time)
                } else {
                    dueCalendar = selectedCalendar
                    textViewAssignmentDueDate?.text = sdf.format(selectedCalendar.time)
                }
            }, year, month, day
        )

        datePickerDialog.show()
    }

    private fun getPrefs(): Map<String, String> {
        val token = prefs.getString("token", "")
        val name = prefs.getString("name", "")
        val roll = prefs.getString("roll", "")
        val branch = prefs.getString("branch", "")

        return mapOf(
            "token" to token,
            "name" to name,
            "roll" to roll,
            "branch" to branch
        )
    }

    private fun setDataTextView(textName: TextView, textRollNo: TextView) {
        val userData = getPrefs()
//        val token = userData["token"]
        val name = userData["name"]?.uppercase()
        val rollNo = userData["rollNo"]
        val roll = userData["roll"]?.uppercase()

        textName.text = name
        textRollNo.text = "ID:$rollNo|$roll"
    }

    private fun getItemFromSpinner(semesterArray: Array<String>) {

        txtSemester.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                selectedSemester = semesterArray[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Kuch nahi karna
            }
        }
    }

}