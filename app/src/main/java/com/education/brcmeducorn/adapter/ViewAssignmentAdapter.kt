package com.education.brcmeducorn.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.api.apiModels.Data
import java.text.SimpleDateFormat
import java.util.Date

class ViewAssignmentAdapter(private val assignments: List<Data>, val context: Context) : RecyclerView.Adapter<ViewAssignmentAdapter.ViewHolder>() {
    private val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private val outputFormat = SimpleDateFormat("dd/MM/yyyy")
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textViewAssignmentTitle)
        val descriptionTextView: TextView = itemView.findViewById(R.id.textViewAssignmentDescription)
        val dueDateTextView: TextView = itemView.findViewById(R.id.textViewDueDate)
        val feedbackTextView: TextView = itemView.findViewById(R.id.textViewFeedback)
        val gradeTextView: TextView = itemView.findViewById(R.id.textViewGrade)
//        val selectedFileTextView: TextView = itemView.findViewById(R.id.selected_file)
        val statusAssignment: TextView = itemView.findViewById(R.id.statusAssignment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_assignment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val assignment = assignments[position]
        // output date formats
        val date: Date = inputFormat.parse(assignment.dueDate)!!
        val formattedDate: String = outputFormat.format(date)

        holder.titleTextView.text = assignment.title
        holder.descriptionTextView.text = assignment.description
        holder.dueDateTextView.text = formattedDate?:""
//        holder.feedbackTextView.text = " feedback "
        holder.gradeTextView.text = "0"
        holder.statusAssignment.text = assignment.status
        if (assignment.status.equals("submitted", ignoreCase = true)) {
            holder.statusAssignment.setTextColor(ContextCompat.getColor(context, R.color.line_pay_green))
            holder.dueDateTextView.setTextColor(ContextCompat.getColor(context, R.color.line_pay_green))
            holder.gradeTextView.setTextColor(ContextCompat.getColor(context, R.color.line_pay_green))
//            holder.gradeTextView.text = assignment.grades.toString()

        } else if (assignment.status.equals("pending", ignoreCase = true)) {
            holder.statusAssignment.setTextColor(ContextCompat.getColor(context, R.color.com_red))
            holder.dueDateTextView.setTextColor(ContextCompat.getColor(context, R.color.com_red))
            holder.gradeTextView.setTextColor(ContextCompat.getColor(context, R.color.com_red))
            holder.gradeTextView.text =  "0"

        }

//        if (!assignment.attachment.isNullOrBlank()) {
//            holder.selectedFileTextView.visibility = View.VISIBLE
//            holder.selectedFileTextView.text = assignment.attachment
//        } else {
//            holder.selectedFileTextView.visibility = View.GONE
//        }
    }

    override fun getItemCount(): Int {
        return assignments.size
    }
}
