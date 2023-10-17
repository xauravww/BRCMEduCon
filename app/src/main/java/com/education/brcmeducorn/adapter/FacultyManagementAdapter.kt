package com.education.brcmeducorn.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.model.FreeClassAndPeriod
import com.education.brcmeducorn.model.FreeTeacher
import com.education.brcmeducorn.model.AbsentTeacher

class FacultyManagementAdapter(
    private val freeTeachersList: List<FreeTeacher>,
    private val absentTeachersList: List<AbsentTeacher>,
    private val availablePeriodsList: List<FreeClassAndPeriod>,
    private val context: Context
) : RecyclerView.Adapter<FacultyManagementAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.faculty_management_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val spinnerPeriod = holder.itemView.findViewById<Spinner>(R.id.spinner_period)
        val spinnerClassName = holder.itemView.findViewById<Spinner>(R.id.spinner_class_name)
        val spinnerTeacher = holder.itemView.findViewById<Spinner>(R.id.spinner_teacher)
        val buttonAssign = holder.itemView.findViewById<Button>(R.id.button_assign)

        // Dummy data for spinners
// Dummy data for spinners
        val dummyPeriods = availablePeriodsList.map { it.semester }
        val dummyClassNames = availablePeriodsList.map { it.period }
        val dummyTeachers = freeTeachersList.map { it.name }

        // Use the spinners and button as needed based on position
        if (position < freeTeachersList.size) {
            // Populate spinners with data from freeTeachersList
            val periodAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, dummyPeriods)
            periodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerPeriod.adapter = periodAdapter

            val classNameAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, dummyClassNames)
            classNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerClassName.adapter = classNameAdapter

            val teacherAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, dummyTeachers)
            teacherAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerTeacher.adapter = teacherAdapter
        } else if (position < freeTeachersList.size + absentTeachersList.size) {
            // Populate spinners with data from absentTeachersList
            // ...
        } else {
            // Populate spinners with data from availablePeriodsList
            // ...
        }

        // Set any listeners or other configurations as needed
    }


    override fun getItemCount(): Int {
        return freeTeachersList.size + absentTeachersList.size + availablePeriodsList.size
    }
}
