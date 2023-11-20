package com.education.brcmeducorn.adapter

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.api.apiModels.Data
import com.education.brcmeducorn.api.apiModels.SubmitAssignmentReq
import com.education.brcmeducorn.api.apiModels.Success
import com.education.brcmeducorn.utils.ApiUtils
import com.education.brcmeducorn.utils.SharedPrefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class PendingAssignmentAdapter(
    private val assignments: List<Data>,
    private val pickPdfLauncher: ActivityResultLauncher<Intent>,
    private val context: Context
) : RecyclerView.Adapter<PendingAssignmentAdapter.ViewHolder>() {
    private val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private val outputFormat = SimpleDateFormat("dd/MM/yyyy")
    private lateinit var prefs: SharedPrefs
    private lateinit var attachmentView :TextView


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.textViewAssignmentTitle)
        val description: TextView = itemView.findViewById(R.id.textViewAssignmentDescription)
        val dueDate: TextView = itemView.findViewById(R.id.textViewDueDate)
        val uploadButton: Button = itemView.findViewById(R.id.buttonUploadAssignment)
        val fileAttachment: TextView = itemView.findViewById(R.id.attachmentFile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pending_assignment_item, parent, false)
        prefs = SharedPrefs(context)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val assignment = assignments[position]
        val date: Date = inputFormat.parse(assignment.dueDate)!!
        val formattedDate: String = outputFormat.format(date)
        holder.title.text = assignment.title
        holder.description.text = assignment.description
        holder.dueDate.text = formattedDate
        holder.uploadButton.setOnClickListener {
            val pdfPath = prefs.getString("pdfPath", "")
            val studentName = getPrefs()["name"]?:""
            val studentRollNo = getPrefs()["rollNo"]?:""
            val studentToken = getPrefs()["token"]?:""
            CoroutineScope(Dispatchers.Main).launch {
                val endpoint = "submit/assignment/{id}"
                val method = "SUBMIT_ASSIGNMENT"
                val nameRequestBody = studentName.toRequestBody("text/plain".toMediaTypeOrNull())
                val rollNoRequestBody = studentRollNo.toRequestBody("text/plain".toMediaTypeOrNull())
                val tokenRequestBody  = studentToken.toRequestBody("text/plain".toMediaTypeOrNull())
                val id=assignment._id
                val assignmentData = SubmitAssignmentReq(nameRequestBody,rollNoRequestBody,tokenRequestBody,id)
                val result = ApiUtils.register(endpoint, method, assignmentData, pdfPath)

                if (result is Success) {
                    Log.d("result", result.toString())

                    Toast.makeText(
                        context,
                        "your assignment request has been uploaded successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "something went wrong please try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }


        }
        holder.fileAttachment.setOnClickListener {
            prefs.saveString("pdfPath","")
            if (::attachmentView.isInitialized) {
                attachmentView.text = "Select An Attachment"
            }
            try {
                attachmentView=holder.fileAttachment
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.type = "application/pdf"
                pickPdfLauncher.launch(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    context,
                    "File picker not found",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return assignments.size
    }

    private fun getPrefs(): Map<String, String> {
        val token = prefs.getString("token", "")
        val name = prefs.getString("name", "")
        val rollNo = prefs.getString("rollNo", "")


        return mapOf(
            "token" to token,
            "name" to name,
            "rollNo" to rollNo
        )
    }
    fun updateTextView(textView: TextView = attachmentView) {
        textView.text =File(prefs.getString("pdfPath", "")).name
    }
}
