package com.education.brcmeducorn.fragments.faculty_dashboard_fragments

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.applandeo.materialcalendarview.utils.calendar
import com.education.brcmeducorn.R
import com.education.brcmeducorn.fragments.EventsFragment
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.DateMonthYearHandler
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.StudentAttendenceListAsyncTask
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.StudentSetIsPresent
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Locale

class MarkAttendanceFragment : Fragment() {


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var day:TextView
    lateinit var  dateAndMonth:TextView
    lateinit var curryear:TextView
    lateinit var selectDate: Button
    lateinit var branch:Spinner
    lateinit var semester:Spinner
    lateinit var markAttendenceBtn:Button
    lateinit var absentStudents:TextView
    lateinit var presentStudents:TextView
    lateinit var totalstudents:TextView
    lateinit var  course:Spinner
    var branchArray = arrayOf("Branch","Cse","Civil","Mechanical","Electrical")
    var semesterArray = arrayOf("Semester","Sem1","Sem2","Sem3","Sem4","Sem5","Sem6","Sem7","Sem8")
    var subjectArray = arrayOf("Subjects","SPM","FOM","ST","Project","NN")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mark_attendance, container, false)

        day = view.findViewById(R.id.day)
        dateAndMonth = view.findViewById(R.id.dateAndMonth)
        curryear= view.findViewById(R.id.year)
        selectDate = view.findViewById(R.id.selectDate)
        branch = view.findViewById(R.id.branch)
        semester = view.findViewById(R.id.semester)
        course = view.findViewById(R.id.course)
        markAttendenceBtn = view.findViewById(R.id.markAttendenceBtn)
        presentStudents = view.findViewById(R.id.presentStudents)
        absentStudents = view.findViewById(R.id.absentStudents)
        totalstudents = view.findViewById(R.id.totalStudents)

        markAttendenceBtn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(com.education.brcmeducorn.R.id.frameLayout, AttendanceRegisterFragment())
                ?.commit()
        }
        val branchAdapter = ArrayAdapter(activity as Context,R.layout.spinner_item,branchArray)
        val semAdapter = ArrayAdapter(activity as Context,R.layout.spinner_item,semesterArray)
        val courseAdapter = ArrayAdapter(activity as Context,R.layout.spinner_item,subjectArray)
        branch.adapter = branchAdapter
        semester.adapter = semAdapter
        course.adapter = courseAdapter

        // setting the clicks on the spinners
        setListener(branch,semester,course)

        // setting the day,date,month,year
        DateMonthYearHandler(activity as Context,day,dateAndMonth,curryear).defaultDate()

        // selecting the day,date,month,year as per the user requirement
        selectDate.setOnClickListener {
            DateMonthYearHandler(activity as Context,day,dateAndMonth,curryear).openDateSelectorDialog()
        }
        return view
    }

    // this will help to update the attendence result data when we come back after taking attendence
    override fun onResume() {
        super.onResume()
        setAttendenceResult(activity as Context)
    }




    fun setAttendenceResult(context: Context)
    {
        var presentiesList = StudentAttendenceListAsyncTask(activity as Context,8).execute().get()
        var absentiesList = StudentAttendenceListAsyncTask(activity as Context,9).execute().get()
        absentStudents.text ="Absent students = ${absentiesList.size}"
        presentStudents.text ="Present students = ${presentiesList.size}"
        totalstudents.text ="Total students = ${absentiesList.size+presentiesList.size}"


    }

    fun setListener(branch:Spinner,semester:Spinner,subject:Spinner)
    {
        branch.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var item  = parent!!?.getItemAtPosition(position)
                // we can use the item to send the request to the server to fetch data
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        semester.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var item  = parent!!?.getItemAtPosition(position)
                // later we will use this to send request to server


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        subject.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var item  = parent!!?.getItemAtPosition(position)
                // later we will use this to send request to server
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

}