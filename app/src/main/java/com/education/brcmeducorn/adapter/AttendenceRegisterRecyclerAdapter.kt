package com.education.brcmeducorn.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.StudentAttendenceListAsyncTask
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.StudentRegisterEntry
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.StudentSetIsPresent

class AttendenceRegisterRecyclerAdapter(val context: Context,val list:ArrayList<StudentRegisterEntry>,var allPresent:Button,var allAbsent:Button):RecyclerView.Adapter<AttendenceRegisterRecyclerAdapter.AttendenceRegisterViewHolder>() {
    companion object {
        var  studentAttendList= BooleanArray(20)
    }
    class AttendenceRegisterViewHolder(val view:View):RecyclerView.ViewHolder(view)
    {

        val name = view.findViewById<TextView>(R.id.Name)
        val rollNo = view.findViewById<TextView>(R.id.rollNo)
        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)
        val present = view.findViewById<RadioButton>(R.id.present)
        val absent = view.findViewById<RadioButton>(R.id.absent)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AttendenceRegisterViewHolder {
       val view  = LayoutInflater.from(parent.context).inflate(R.layout.attendence_register_single_row, parent, false)
        return AttendenceRegisterViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: AttendenceRegisterViewHolder, position: Int) {
      val item = list[position]
        holder.name.text = item.Sname
        holder.rollNo.text = item.Sroll.toString()


        val attendenceList = StudentAttendenceListAsyncTask(context,3).execute().get()

        if (attendenceList != null && position < attendenceList.size) {
            // Set RadioButton state based on the retrieved data
            holder.present.isChecked = attendenceList[position].isPresent
            holder.absent.isChecked = !attendenceList[position].isPresent
        } else {
            // If there's no attendance data, assume absent
            holder.absent.isChecked = true
        }

        allPresent.setOnClickListener {
            StudentSetIsPresent(context,4,holder.rollNo.text.toString().toInt()).execute().get()
            notifyDataSetChanged()
        }


        allAbsent.setOnClickListener {
            StudentSetIsPresent(context,5,holder.rollNo.text.toString().toInt()).execute().get()
            notifyDataSetChanged()
        }


        holder.present.setOnClickListener {

            StudentSetIsPresent(context,6,holder.rollNo.text.toString().toInt()).execute().get()
            notifyDataSetChanged()
        }

        holder.absent.setOnClickListener {
            StudentSetIsPresent(context,7,holder.rollNo.text.toString().toInt()).execute().get()
            notifyDataSetChanged()

        }

    }

}

