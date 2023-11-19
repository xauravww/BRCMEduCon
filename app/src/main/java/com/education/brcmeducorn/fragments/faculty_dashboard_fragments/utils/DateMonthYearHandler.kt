package com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.widget.TextView
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.MarkAttendanceFragment
import java.util.Locale

class DateMonthYearHandler(
    val context: Context,
    private var day: TextView,
    private var dateAndMonth: TextView,
    private var curryear: TextView
) {
    private lateinit var sDate:String
    private val dayNames =
        arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
    private val shortMonthNames =
        arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

    fun defaultDate() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // Note: Months are 0-based, so add 1
        val date = calendar.get(Calendar.DAY_OF_MONTH)
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        sDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month, date)

        day.text = dayNames[dayOfWeek - 1]
        dateAndMonth.text = "$date ${shortMonthNames[month - 1]}"
        curryear.text = "$year"

    }

    fun openDateSelectorDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        // Create a DatePickerDialog and set the initial date
        val datePickerDialog = DatePickerDialog(
            context,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Handle the selected date
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDay)

                sDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", selectedYear, selectedMonth+1, selectedDay)

//                val formattedDate = dateFormat.format(selectedDate.time)
                // Update your UI elements with the selected date
                day.text = dayNames[selectedDate.get(Calendar.DAY_OF_WEEK) - 1]
                dateAndMonth.text = "$selectedDay ${shortMonthNames[selectedMonth]}"
                curryear.text = "$selectedYear"


            },
            year,
            month,
            dayOfMonth
        )

        // Show the DatePickerDialog
        datePickerDialog.show()
    }

    fun getSelectedDate(): String {
        return sDate
    }

}