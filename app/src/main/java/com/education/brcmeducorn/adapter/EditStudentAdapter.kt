package com.education.brcmeducorn.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.model.StudentModel

class EditStudentAdapter(private val context: Context, studentList: ArrayList<StudentModel>) :
    RecyclerView.Adapter<EditStudentAdapter.StudentViewHolder>() {
    private val studentList: ArrayList<StudentModel>

    init {
        this.studentList = studentList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.admin_student_edit_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student: StudentModel = studentList[position]
        holder.editTextName.setText(student.name)
        holder.editTextEmail.setText(student.email)
        holder.editTextPhone.setText(student.phone)
        holder.editTextRollNO.setText(student.rollNo)
        holder.editTextSubject.setText(student.regNo)
        holder.editTextSemester.setText(student.semester)
        holder.editTextAddress.setText(student.address)
        holder.editTextWiFiPassword.setText(student.wifiPassword)
        holder.editTextDOB.setText(student.dob)

        holder.buttonEdit.setOnClickListener {
            // Handle edit button click
        }

        holder.buttonDelete.setOnClickListener {
            // Handle delete button click
        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun updateList(newList: List<StudentModel>) {
        studentList.clear()
        studentList.addAll(newList)
        notifyDataSetChanged()
    }

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var editTextName: EditText
        var editTextEmail: EditText
        var editTextPhone: EditText
        var editTextRollNO: EditText
        var editTextSubject: EditText
        var editTextSemester: EditText
        var editTextAddress: EditText
        var editTextWiFiPassword: EditText
        var editTextDOB: EditText
        var buttonEdit: Button
        var buttonDelete: Button

        init {
            editTextName = itemView.findViewById(R.id.editTextName)
            editTextEmail = itemView.findViewById(R.id.editTextEmail)
            editTextPhone = itemView.findViewById(R.id.editTextPhone)
            editTextRollNO = itemView.findViewById(R.id.editTextRollNO)
            editTextSubject = itemView.findViewById(R.id.editTextSubject)
            editTextSemester = itemView.findViewById(R.id.editTextSemester)
            editTextAddress = itemView.findViewById(R.id.editTextAddress)
            editTextWiFiPassword = itemView.findViewById(R.id.editTextWiFiPassword)
            editTextDOB = itemView.findViewById(R.id.editTextDOB)
            buttonEdit = itemView.findViewById(R.id.buttonEdit)
            buttonDelete = itemView.findViewById(R.id.buttonDelete)
        }
    }

}
