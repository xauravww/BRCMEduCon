package com.education.brcmeducorn.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.api.apiModels.AttendanceData
import com.education.brcmeducorn.api.apiModels.SendStudentAttendanceReq
import com.education.brcmeducorn.api.apiModels.Student

class AttendenceRegisterRecyclerAdapter(
    val context: Context,
    private var studentsAttendance: SendStudentAttendanceReq,
    val list: List<Student>,
    private var allPresent: Button,
    private var allAbsent: Button
) : RecyclerView.Adapter<AttendenceRegisterRecyclerAdapter.AttendenceRegisterViewHolder>() {
    private val present: String = "present"
    private val absent: String = "absent"

    companion object {
        var studentAttendList = BooleanArray(20)
    }

    class AttendenceRegisterViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.findViewById(R.id.Name)
        val rollNo: TextView = view.findViewById(R.id.rollNo)
        val radioGroup: RadioGroup = view.findViewById(R.id.radioGroup)
        val present: RadioButton = view.findViewById(R.id.present)
        val absent: RadioButton = view.findViewById(R.id.absent)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AttendenceRegisterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.attendence_register_single_row, parent, false)
        return AttendenceRegisterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AttendenceRegisterViewHolder, position: Int) {
        val item = list[position]
        holder.name.text = item.name
        holder.rollNo.text = item.rollno

//     it just decide the size of list so i can further update list on click
        if (studentsAttendance.attendanceData.size != list.size) {
            studentsAttendance.attendanceData.add(
                position,
                AttendanceData(item._id, item.name, item.rollno, "absent", "none")
            )
        }
        Log.d("attnd",studentsAttendance.attendanceData[position].status)
        if (studentsAttendance.attendanceData[position].status == present) {
            holder.present.isChecked = true

        }
        if (studentsAttendance.attendanceData[position].status == absent) {
            holder.absent.isChecked = true
        }

//        val attendenceList = StudentAttendenceListAsyncTask(context, 3).execute().get()
//        if (attendenceList != null && position < attendenceList.size) {
//            // Set RadioButton state based on the retrieved data
//            holder.present.isChecked = attendenceList[position].isPresent
//            holder.absent.isChecked = !attendenceList[position].isPresent
//        } else {
//            // If there's no attendance data, assume absent
//            holder.absent.isChecked = false
//        }

//        allPresent.setOnClickListener {
//            StudentSetIsPresent(context, 4, holder.rollNo.text.toString().toInt()).execute().get()
//            notifyDataSetChanged()
//        }
//
//
//        allAbsent.setOnClickListener {
//            StudentSetIsPresent(context, 5, holder.rollNo.text.toString().toInt()).execute().get()
//            notifyDataSetChanged()
//        }


        holder.present.setOnClickListener {
            val attendanceDataPresent =
                AttendanceData(item._id, item.name, item.rollno, "present", "on_time")
            studentsAttendance.attendanceData[position] = attendanceDataPresent
            Log.d("position", position.toString())
//            StudentSetIsPresent(context, 6, holder.rollNo.text.toString().toInt()).execute().get()
//            notifyDataSetChanged()
        }

        holder.absent.setOnClickListener {
//            StudentSetIsPresent(context, 7, holder.rollNo.text.toString().toInt()).execute().get()
            val attendanceDataAbsent =
                AttendanceData(item._id, item.name, item.rollno, "absent", "on_time")
            studentsAttendance.attendanceData[position] = attendanceDataAbsent
//            notifyDataSetChanged()

        }

    }

    fun getStudentAttendanceData(): SendStudentAttendanceReq {
        return studentsAttendance
    }

}

