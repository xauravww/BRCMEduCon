package com.education.brcmeducorn.fragments.faculty_dashboard_fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.education.brcmeducorn.R
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.AppPreferences
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.DateMonthYearHandler
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.StudentAttendenceListAsyncTask
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale

class MarkAttendanceFragment : Fragment() {


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var day: TextView
    private lateinit var dateAndMonth: TextView
    private lateinit var curryear: TextView
    private lateinit var selectDate: Button
    lateinit var branch: Spinner
    lateinit var semester: Spinner
    private lateinit var markAttendenceBtn: Button
    private lateinit var absentStudents: TextView
    private lateinit var presentStudents: TextView
    private lateinit var totalstudents: TextView
    private lateinit var course: Spinner
    private var branchArray = arrayOf<String>()
    private var semesterArray =
        arrayOf("sem", "Sem1", "Sem2", "Sem3", "Sem4", "Sem5", "Sem6", "Sem7", "Sem8")
    private var subjectArray = arrayOf("Sub", "SPM", "FOM", "ST", "Project", "NN")
    private var selectedSemester: String = ""
    private var selectedSubject: String = ""
    private var selectedBranch: String = ""
    private lateinit var dateMonthYearHandler: DateMonthYearHandler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mark_attendance, container, false)

        val addBranch = AppPreferences(requireContext()).getBranch()
        branchArray = branchArray.plus(addBranch)
        day = view.findViewById(R.id.day)
        dateAndMonth = view.findViewById(R.id.dateAndMonth)
        curryear = view.findViewById(R.id.year)
        selectDate = view.findViewById(R.id.selectDate)
        branch = view.findViewById(R.id.branch)
        semester = view.findViewById(R.id.semester)
        course = view.findViewById(R.id.course)
        markAttendenceBtn = view.findViewById(R.id.markAttendenceBtn)
        presentStudents = view.findViewById(R.id.presentStudents)
        absentStudents = view.findViewById(R.id.absentStudents)
        totalstudents = view.findViewById(R.id.totalStudents)
        val subjectAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, subjectArray)
        val semesterAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, semesterArray)
        val branchAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, branchArray)
        course.adapter = subjectAdapter
        semester.adapter = semesterAdapter
        branch.adapter = branchAdapter
        getItemFromSpinner(subjectArray, semesterArray, branchArray)
        dateMonthYearHandler = DateMonthYearHandler(
            requireContext(),
            day,
            dateAndMonth,
            curryear,
        )
        markAttendenceBtn.setOnClickListener {
            val givenDateString = dateMonthYearHandler.getSelectedDate()

            if (isDateNotGreaterThanToday(givenDateString)) {
                println("The given date is not greater than today's date.")
            Log.d("selectedDate", dateMonthYearHandler.getSelectedDate())
            val bundle = Bundle()
            // Add your data to the bundle
            bundle.putString("selectedSemester", selectedSemester)
            bundle.putString("selectedSubject", selectedSubject)
            bundle.putString("selectedDate", dateMonthYearHandler.getSelectedDate())

            val attendanceFragment = AttendanceRegisterFragment()
            attendanceFragment.arguments = bundle

            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.frameLayout, attendanceFragment)
                ?.commit()
        }
            else{
                Toast.makeText(requireContext(), "select a valid date today or before today", Toast.LENGTH_SHORT).show()
            }
        }


        // setting the clicks on the spinners

        // setting the day,date,month,year
        dateMonthYearHandler.defaultDate()

        // selecting the day,date,month,year as per the user requirement
        selectDate.setOnClickListener {
            dateMonthYearHandler.openDateSelectorDialog()
        }
        return view
    }

    private fun isDateNotGreaterThanToday(givenDate: String): Boolean {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return try {
            val givenDateTime = dateFormat.parse(givenDate)
            val today = Calendar.getInstance().time
            !givenDateTime?.after(today)!!
        } catch (e: Exception) {
            false
        }
    }

    // this will help to update the attendence result data when we come back after taking attendence
//    override fun onResume() {
//        super.onResume()
//        setAttendenceResult(requireContext())
//    }


    fun setAttendenceResult(context: Context) {
        var presentiesList = StudentAttendenceListAsyncTask(requireContext(), 8).execute().get()
        var absentiesList = StudentAttendenceListAsyncTask(requireContext(), 9).execute().get()
        absentStudents.text = "Absent students = ${absentiesList.size}"
        presentStudents.text = "Present students = ${presentiesList.size}"
        totalstudents.text = "Total students = ${absentiesList.size + presentiesList.size}"


    }

    private fun getItemFromSpinner(
        subjectArray: Array<String>,
        semesterArray: Array<String>,
        branchArray: Array<String>
    ) {
        course.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                selectedSubject = subjectArray[position]
                Toast.makeText(requireContext(), selectedSubject, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Kuch nahi karna
            }
        }

        semester.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                selectedSemester = semesterArray[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Kuch nahi karna
            }
        }
        branch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                selectedBranch = semesterArray[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Kuch nahi karna
            }
        }
    }

}