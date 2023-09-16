package com.education.brcmeducorn.fragments.faculty_dashboard_fragments

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
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
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

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

        // setting the day,date,month,year
        DateMonthYearHandler(activity as Context,day,dateAndMonth,curryear).defaultDate()

        // selecting the day,date,month,year as per the user requirement
        selectDate.setOnClickListener {
          DateMonthYearHandler(activity as Context,day,dateAndMonth,curryear).openDateSelectorDialog()
        }
        return view
    }






}