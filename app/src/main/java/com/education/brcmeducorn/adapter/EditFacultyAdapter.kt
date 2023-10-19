package com.education.brcmeducorn.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.model.FacultyModel

class EditFacultyAdapter(private val context: Context, facultyList: ArrayList<FacultyModel>) :
    RecyclerView.Adapter<EditFacultyAdapter.FacultyViewHolder>() {
    private val facultyList: ArrayList<FacultyModel>

    init {
        this.facultyList = facultyList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacultyViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.admin_faculty_edit_item, parent, false)
        return FacultyViewHolder(view)
    }

    override fun onBindViewHolder(holder: FacultyViewHolder, position: Int) {
        val faculty: FacultyModel = facultyList[position]
        holder.editTextName.setText(faculty.name)
        holder.editTextEmail.setText(faculty.email)
        holder.editTextPhone.setText(faculty.phone)
        holder.editTextID.setText(faculty.id)
        holder.editTextSubject.setText(faculty.subject)
        holder.editTextExperience.setText(faculty.experience)
        holder.editTextAddress.setText(faculty.address)
        holder.buttonEdit.setOnClickListener {
            // Handle edit button click
        }
        holder.buttonDelete.setOnClickListener {
            // Handle delete button click
        }
    }

    override fun getItemCount(): Int {
        return facultyList.size
    }

    fun updateList(newList: List<FacultyModel>) {
        facultyList.clear()
        facultyList.addAll(newList)
        notifyDataSetChanged()
    }

    class FacultyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var editTextName: EditText
        var editTextEmail: EditText
        var editTextPhone: EditText
        var editTextID: EditText
        var editTextSubject: EditText
        var editTextExperience: EditText
        var editTextAddress: EditText
        var buttonEdit: Button
        var buttonDelete: Button

        init {
            editTextName = itemView.findViewById(R.id.editTextName)
            editTextEmail = itemView.findViewById(R.id.editTextEmail)
            editTextPhone = itemView.findViewById(R.id.editTextPhone)
            editTextID = itemView.findViewById(R.id.editTextID)
            editTextSubject = itemView.findViewById(R.id.editTextSubject)
            editTextExperience = itemView.findViewById(R.id.editTextExperience)
            editTextAddress = itemView.findViewById(R.id.editTextAddress)
            buttonEdit = itemView.findViewById(R.id.buttonEdit)
            buttonDelete = itemView.findViewById(R.id.buttonDelete)
        }
    }
}
