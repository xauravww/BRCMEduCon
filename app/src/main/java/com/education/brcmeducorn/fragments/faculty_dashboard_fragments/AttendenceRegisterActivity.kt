package com.education.brcmeducorn.fragments.faculty_dashboard_fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.DecorToolbar
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.education.brcmeducorn.R
import com.education.brcmeducorn.adapter.AttendenceRegisterRecyclerAdapter
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.StudentRegisterEntry
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.SwipeToDeleteCallBack
import java.util.zip.Inflater

class AttendenceRegisterActivity : AppCompatActivity() {


    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager:LayoutManager
    lateinit var reyclerAdapter:AttendenceRegisterRecyclerAdapter
    lateinit var doneBtn:Button
    lateinit var allPresentBtn:Button
    lateinit var allAbsentBtn:Button
    lateinit var toolbar: Toolbar
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
        
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendence_register)

        toolbar = findViewById(R.id.toolbar)
        setToolbar()
        doneBtn = findViewById(R.id.doneBtn)
        allPresentBtn = findViewById(R.id.allPresentBtn)
        allAbsentBtn  = findViewById(R.id.allAbsentBtn)

        recyclerView = findViewById(R.id.recylerView)
        layoutManager = LinearLayoutManager(this)
        reyclerAdapter  = AttendenceRegisterRecyclerAdapter(this,studentList,doneBtn,allPresentBtn,allAbsentBtn)
        recyclerView.adapter = reyclerAdapter
        recyclerView.layoutManager = layoutManager

        // added to delete the item on specific position in recycler view
        val swipeToDeleteCallBack = object :SwipeToDeleteCallBack()
        {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                studentList.removeAt(pos)
                recyclerView.adapter?.notifyItemRemoved(pos)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    fun setToolbar()
    {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Attendence"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

      menuInflater.inflate(R.menu.attendence_register_add_students,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.addStudent->{
                 showAddStudentDialog()
                return true
            }
            android.R.id.home -> {
                // Handle the Up button press
                onBackPressed() // This example simply calls onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAddStudentDialog() {
        val dialogView = layoutInflater.inflate(R.layout.add_student_attendence_register_dialog_box, null)
        var Sname = dialogView.findViewById<EditText>(R.id.editTextName)
        var Srollno= dialogView.findViewById<EditText>(R.id.editTextRollNo)


        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Add Student")
            .setPositiveButton("Add") { _, _ ->
                // Handle adding the student here
                studentList.add(StudentRegisterEntry(Sname.text.toString(),Srollno.text.toString()))
                reyclerAdapter.notifyDataSetChanged()
                // You can now use 'name' and 'rollNo' as needed
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }
}