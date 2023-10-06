package com.education.brcmeducorn.fragments.faculty_dashboard_fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.adapter.AttendenceRegisterRecyclerAdapter
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.StudentAttendenceAsyncTask
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.StudentAttendenceListAsyncTask
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.StudentRegisterEntry
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.SwipeToDeleteCallBack

import com.education.brcmeducorn.studentdatabase.StudentAttendenceEntity


class AttendanceRegisterFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var reyclerAdapter: AttendenceRegisterRecyclerAdapter
    lateinit var doneBtn: Button
    lateinit var allPresentBtn: Button
    lateinit var allAbsentBtn: Button
    lateinit var toolbar: Toolbar


    var studentList = arrayListOf<StudentRegisterEntry>(
        StudentRegisterEntry("Student 1", 10),
        StudentRegisterEntry("Student 2", 20),
        StudentRegisterEntry("Student 3", 30),
        StudentRegisterEntry("Student 4", 40),
        StudentRegisterEntry("Student 5", 50),
        StudentRegisterEntry("Student 6", 60),
        StudentRegisterEntry("Student 7", 70),
        StudentRegisterEntry("Student 8", 80),
        StudentRegisterEntry("Student 9", 90),
        StudentRegisterEntry("Student 10", 100),
        StudentRegisterEntry("Student 11", 110),
        StudentRegisterEntry("Student 12", 120),
        StudentRegisterEntry("Student 13", 130),
        StudentRegisterEntry("Student 14", 140),
        StudentRegisterEntry("Student 15", 150),
        StudentRegisterEntry("Student 16", 160),
        StudentRegisterEntry("Student 17", 170),
        StudentRegisterEntry("Student 18", 180),
        StudentRegisterEntry("Student 19", 190),
        StudentRegisterEntry("Student 20", 200),



        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_attendance_register, container, false)



        doneBtn = view.findViewById(R.id.doneBtn)
        allPresentBtn = view.findViewById(R.id.allPresentBtn)
        allAbsentBtn = view.findViewById(R.id.allAbsentBtn)

        addToDatabase(activity as Context)
        recyclerView = view.findViewById(R.id.recylerView)
        layoutManager = LinearLayoutManager(activity as Context)
        reyclerAdapter = AttendenceRegisterRecyclerAdapter(
            activity as Context,
            studentList,
            allPresentBtn,
            allAbsentBtn
        )
        recyclerView.adapter = reyclerAdapter
        recyclerView.layoutManager = layoutManager

        doneBtn.setOnClickListener {

//           add krr denge baad m
        }

        // added to delete the item on specific position in recycler view
        val swipeToDeleteCallBack = object : SwipeToDeleteCallBack() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                studentList.removeAt(pos)
                recyclerView.adapter?.notifyItemRemoved(pos)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        return view
    }


    private fun showAddStudentDialog() {
        val dialogView =
            layoutInflater.inflate(R.layout.add_student_attendence_register_dialog_box, null)
        var Sname = dialogView.findViewById<EditText>(R.id.editTextName)
        var Srollno = dialogView.findViewById<EditText>(R.id.editTextRollNo)


        val dialog = AlertDialog.Builder(activity as Context)
            .setView(dialogView)
            .setTitle("Add Student")
            .setPositiveButton("Add") { _, _ ->
                // Handle adding the student here
                studentList.add(
                    StudentRegisterEntry(
                        Sname.text.toString(),
                        Srollno.text.toString().toInt()
                    )
                )

                reyclerAdapter.notifyDataSetChanged()

            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }

    fun addToDatabase(context: Context)
    {
        val list = StudentAttendenceListAsyncTask(context,3).execute().get()
        if(list.size==0)
        {
            for(i in studentList)
            {
                val studentAttendenceEntity = StudentAttendenceEntity(i.Sname,i.Sroll,false)
                StudentAttendenceAsyncTask(context,studentAttendenceEntity,1).execute().get()
            }
        }



    }
}