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
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.Spinner
import android.widget.TextView
import com.applandeo.materialcalendarview.utils.calendar
import com.education.brcmeducorn.R
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.DateMonthYearHandler
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
    lateinit var course:Spinner
    var branchArray = arrayOf("Branch","Cse","Civil","Mechanical","Electrical")
    var semesterArray = arrayOf("Semester","Sem1","Sem2","Sem3","Sem4","Sem5","Sem6","Sem7","Sem8")
    var courseArray = arrayOf("Courses","NN","SPM","ST","FOM","PJ")

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
        markAttendenceBtn = view.findViewById(R.id.markAttendenceBtn)
       course = view.findViewById(R.id.course)

        markAttendenceBtn.setOnClickListener {
            val intent = Intent(activity as Context,AttendenceRegisterActivity::class.java)
            startActivity(intent)
        }
        val branchAdapter = ArrayAdapter(activity as Context,R.layout.spinner_item,branchArray)
        val semAdapter = ArrayAdapter(activity as Context,R.layout.spinner_item,semesterArray)
        val courseAdapter = ArrayAdapter(activity as Context,R.layout.spinner_item,courseArray)

        branch.adapter = branchAdapter
        semester.adapter = semAdapter
        course.adapter = courseAdapter
        // setting the day,date,month,year
        DateMonthYearHandler(activity as Context,day,dateAndMonth,curryear).defaultDate()

        // selecting the day,date,month,year as per the user requirement
        selectDate.setOnClickListener {
          DateMonthYearHandler(activity as Context,day,dateAndMonth,curryear).openDateSelectorDialog()
        }
        return view
    }






}