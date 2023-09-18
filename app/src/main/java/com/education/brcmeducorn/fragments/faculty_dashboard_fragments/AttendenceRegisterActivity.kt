package com.education.brcmeducorn.fragments.faculty_dashboard_fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.education.brcmeducorn.R
import com.education.brcmeducorn.adapter.AttendenceRegisterRecyclerAdapter
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.StudentRegisterEntry

class AttendenceRegisterActivity : AppCompatActivity() {


    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager:LayoutManager
    lateinit var reyclerAdapter:AttendenceRegisterRecyclerAdapter
    lateinit var doneBtn:Button
    lateinit var allPresentBtn:Button
    lateinit var allAbsentBtn:Button

    var studentList = arrayListOf<StudentRegisterEntry>(
        StudentRegisterEntry("Student 1", "20-cse-1234"),
        StudentRegisterEntry("Student 2", "20-cse-2345"),
        StudentRegisterEntry("Student 3", "20-cse-3456"),
        StudentRegisterEntry("Student 4", "20-cse-4567"),
        StudentRegisterEntry("Student 5", "20-cse-5678"),
        StudentRegisterEntry("Student 6", "20-cse-6789"),
        StudentRegisterEntry("Student 7", "20-cse-7890"),
        StudentRegisterEntry("Student 8", "20-cse-8901"),
        StudentRegisterEntry("Student 9", "20-cse-9012"),
        StudentRegisterEntry("Student 10", "20-cse-0123"),
        StudentRegisterEntry("Student 11", "20-cse-1111"),
        StudentRegisterEntry("Student 12", "20-cse-2222"),
        StudentRegisterEntry("Student 13", "20-cse-3333"),
        StudentRegisterEntry("Student 14", "20-cse-4444"),
        StudentRegisterEntry("Student 15", "20-cse-5555"),
        StudentRegisterEntry("Student 16", "20-cse-6666"),
        StudentRegisterEntry("Student 17", "20-cse-7777"),
        StudentRegisterEntry("Student 18", "20-cse-8888"),
        StudentRegisterEntry("Student 19", "20-cse-9999"),
        StudentRegisterEntry("Student 20", "20-cse-0000"),
        StudentRegisterEntry("Student 21", "20-cse-1212"),
        StudentRegisterEntry("Student 22", "20-cse-2323"),
        StudentRegisterEntry("Student 23", "20-cse-3434"),
        StudentRegisterEntry("Student 24", "20-cse-4545"),
        StudentRegisterEntry("Student 25", "20-cse-5656"),
        StudentRegisterEntry("Student 26", "20-cse-6767"),
        StudentRegisterEntry("Student 27", "20-cse-7878"),
        StudentRegisterEntry("Student 28", "20-cse-8989"),
        StudentRegisterEntry("Student 29", "20-cse-9090"),
        StudentRegisterEntry("Student 30", "20-cse-0202")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendence_register)

        doneBtn = findViewById(R.id.doneBtn)
        allPresentBtn = findViewById(R.id.allPresentBtn)
        allAbsentBtn  = findViewById(R.id.allAbsentBtn)

        recyclerView = findViewById(R.id.recylerView)
        layoutManager = LinearLayoutManager(this)
        reyclerAdapter  = AttendenceRegisterRecyclerAdapter(this,studentList,doneBtn,allPresentBtn,allAbsentBtn)
        recyclerView.adapter = reyclerAdapter
        recyclerView.layoutManager = layoutManager

    }
}