package com.education.brcmeducorn.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.model.PYQS

class PYQSRecyclerAdapter(val context: Context,val pyqList:List<PYQS>):RecyclerView.Adapter<PYQSRecyclerAdapter.PYQSRecyclerAdapterViewHolder>() {

    class PYQSRecyclerAdapterViewHolder(val view:View):RecyclerView.ViewHolder(view){
        val txtCourseTitle: TextView = view.findViewById(R.id.txtCourseTitle)
        val txtCourseCode: TextView = view.findViewById(R.id.txtCourseCode)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PYQSRecyclerAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_pyqs_single_row,parent,false)

        return PYQSRecyclerAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
       return pyqList.size
    }

    override fun onBindViewHolder(holder: PYQSRecyclerAdapterViewHolder, position: Int) {
        val item = pyqList[position]

        holder.txtCourseTitle.text = item.courseName
        holder.txtCourseCode.text = item.courseCode
    }
}