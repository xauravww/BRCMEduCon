package com.education.brcmeducorn.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.api.apiModels.Data
import com.education.brcmeducorn.api.apiModels.SubmitAssignmentReq
import com.education.brcmeducorn.api.apiModels.Success
import com.education.brcmeducorn.fragments.PDFViewFragment
import com.education.brcmeducorn.fragments.admin_dashboard_fragments.AssignClassesFragment
import com.education.brcmeducorn.utils.ApiUtils
import com.education.brcmeducorn.utils.CustomProgressDialog
import com.education.brcmeducorn.utils.PublicVar
import com.education.brcmeducorn.utils.SharedPrefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class CheckAttendanceAdapter(
    private val assignments: List<Data>,
    private val context: Context,
    private val beginTransaction: FragmentTransaction?
) : RecyclerView.Adapter<CheckAttendanceAdapter.ViewHolder>() {
    private val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private val outputFormat = SimpleDateFormat("dd/MM/yyyy")
    private lateinit var prefs: SharedPrefs
    private lateinit var attachmentView: TextView

    private var customProgressDialog: CustomProgressDialog? = null
    private val PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 2


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentName: TextView = itemView.findViewById(R.id.textViewName)
        val description: TextView = itemView.findViewById(R.id.textViewAssignmentDescription)
        val submittedDate: TextView = itemView.findViewById(R.id.textViewSubmittedDate)
        val buttonCheckAssignment: Button = itemView.findViewById(R.id.buttonCheckAssignment)
        val attachedFile: TextView = itemView.findViewById(R.id.attachedFile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.check_attendance_item, parent, false)
        prefs = SharedPrefs(context)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val assignment = assignments[PublicVar.getPosition()]
        val date: Date = inputFormat.parse(assignment.submissions[position].submissionDate)!!
        val formattedDate: String = outputFormat.format(date)
        holder.studentName.text = assignment.submissions[position].studentName
        holder.description.text = assignment.description
        holder.submittedDate.text = formattedDate
        holder.buttonCheckAssignment.setOnClickListener {
            customProgressDialog = CustomProgressDialog(context)
            customProgressDialog!!.setMessage("wait uploading ...")
            customProgressDialog!!.show();

            val bundle = Bundle()
            // Add your data to the bundle
            bundle.putString("pdfUrl", assignment.submissions[position].attachment.url)
            bundle.putString("assignmentId", assignment._id)
            bundle.putString("studentRollNo", assignment.submissions[position].studentRollNo)
            bundle.putString("studentName", assignment.submissions[position].studentName)
            val pdfViewFragment = PDFViewFragment()
            pdfViewFragment.arguments = bundle
            beginTransaction?.add(R.id.frameLayout, pdfViewFragment)
                ?.commit()
            customProgressDialog!!.dismiss();

        }
    }

    override fun getItemCount(): Int {
        return assignments[PublicVar.getPosition()].submissions.size
//            return assignments.sumOf { it.submissions.size }
    }

    fun updateTextView(textView: TextView = attachmentView) {
        textView.text = File(prefs.getString("pdfPath", "")).name
    }
}