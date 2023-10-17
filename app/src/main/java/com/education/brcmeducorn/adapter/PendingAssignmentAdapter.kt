package com.education.brcmeducorn.adapter

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.model.AssignmentModel

class PendingAssignmentAdapter(private val assignments: List<AssignmentModel>, private val fragment: Fragment) : RecyclerView.Adapter<PendingAssignmentAdapter.ViewHolder>() {
    private var selectedPdf: Uri? = null
    private val pickPdfLauncher: ActivityResultLauncher<Intent> = fragment.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            if (data != null) {
                 selectedPdf = data.data!!
                // Do something with the selected PDF Uri (e.g., save it to the assignment model)
                // ...
            }
        }
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.textViewAssignmentTitle)
        val description: TextView = itemView.findViewById(R.id.textViewAssignmentDescription)
        val dueDate: TextView = itemView.findViewById(R.id.textViewDueDate)
        val uploadButton: Button = itemView.findViewById(R.id.buttonUploadAssignment)
        val fileAttachment: TextView = itemView.findViewById(R.id.attachmentFile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pending_assignment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val assignment = assignments[position]
        holder.title.text = assignment.title
        holder.description.text = assignment.description
        holder.dueDate.text = assignment.dueDate

        if (assignment.attachment != null) {
            holder.fileAttachment.text = assignment.attachment

        }


        holder.uploadButton.setOnClickListener {
            //upload selectedpdf roll no semester tiitle teacher currentdate name and other.....
        }
        holder.fileAttachment.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.type = "application/pdf"
                pickPdfLauncher.launch(intent)

            } catch (e: ActivityNotFoundException) {
                Toast.makeText(fragment.requireContext(), "File picker not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return assignments.size
    }
}
