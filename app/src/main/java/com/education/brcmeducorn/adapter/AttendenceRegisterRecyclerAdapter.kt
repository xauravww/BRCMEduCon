package com.education.brcmeducorn.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.education.brcmeducorn.R
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.StudentRegisterEntry

class AttendenceRegisterRecyclerAdapter(val context: Context,val list:ArrayList<StudentRegisterEntry>,var doneBtn:Button):RecyclerView.Adapter<AttendenceRegisterRecyclerAdapter.AttendenceRegisterViewHolder>() {
    companion object {
        var sPresent = 0
        var sAbsent = 0
        var attendence = BooleanArray(30)
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
        holder.rollNo.text = item.Sroll


        for(i in attendence)
        {
            if(i)
            {
                holder.present.isChecked = true
                holder.absent.isChecked = false
            }
            else
            {
                holder.present.isChecked = false
                holder.absent.isChecked = true
            }
        }


        holder.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId)
            {
                R.id.present->
                {
                   attendence[position]=true
                }
                R.id.absent->
                {
                    attendence[position]=false
                }
            }
        }
        doneBtn.setOnClickListener {

            var count = 0;
            for(i in attendence)
            {
                if(i==true)
                {
                    count++;
                }
            }
            Toast.makeText(context, "$count", Toast.LENGTH_SHORT).show()
        }

        for(i in attendence)
        {
            if(i)
            {
                holder.present.isChecked = true
                holder.absent.isChecked = false
            }
            else
            {
                holder.present.isChecked = false
                holder.absent.isChecked = true
            }
        }

    }
}

