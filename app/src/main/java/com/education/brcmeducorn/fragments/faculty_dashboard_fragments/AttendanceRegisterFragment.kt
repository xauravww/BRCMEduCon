package com.education.brcmeducorn.fragments.faculty_dashboard_fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.adapter.AttendenceRegisterRecyclerAdapter
import com.education.brcmeducorn.api.apiModels.AllStudentsForAttendanceReq
import com.education.brcmeducorn.api.apiModels.AllStudentsForAttendanceRes
import com.education.brcmeducorn.api.apiModels.AttendanceData
import com.education.brcmeducorn.api.apiModels.GetOldAttendanceReq
import com.education.brcmeducorn.api.apiModels.GetOldAttendanceRes
import com.education.brcmeducorn.api.apiModels.SendStudentAttendanceReq
import com.education.brcmeducorn.api.apiModels.Student
import com.education.brcmeducorn.api.apiModels.Success
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.AppPreferences
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.StudentRegisterEntry
import com.education.brcmeducorn.fragments.faculty_dashboard_fragments.utils.SwipeToDeleteCallBack
import com.education.brcmeducorn.utils.ApiUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AttendanceRegisterFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var reyclerAdapter: AttendenceRegisterRecyclerAdapter
    private lateinit var doneBtn: Button
    private lateinit var allPresentBtn: Button
    private lateinit var allAbsentBtn: Button
    lateinit var toolbar: Toolbar
    private lateinit var studentsList: List<Student>
    private lateinit var studentsAttendance: SendStudentAttendanceReq
    private lateinit var attendanceList: MutableList<AttendanceData>
    private var update: Boolean = true
    private var updateId: String = "none"

    var studentList = arrayListOf(
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
        val prefs = AppPreferences(requireContext())
        // get bundle data by key
        val selectedSemester = arguments?.getString("selectedSemester") ?: ""
        val selectedDate = arguments?.getString("selectedDate") ?: ""
        val selectedSubject = arguments?.getString("selectedSubject") ?: ""
        doneBtn = view.findViewById(R.id.doneBtn)
        allPresentBtn = view.findViewById(R.id.allPresentBtn)
        allAbsentBtn = view.findViewById(R.id.allAbsentBtn)
        recyclerView = view.findViewById(R.id.recylerView)
        layoutManager = LinearLayoutManager(requireContext())
        doneBtn.visibility = View.GONE
        studentsAttendance = SendStudentAttendanceReq(
            mutableListOf(),
            selectedDate,
            AppPreferences(requireContext()).getBranch(),
            selectedSemester,
            AppPreferences(requireContext()).getToken(),selectedSubject, updateId
        )
        CoroutineScope(Dispatchers.Main).launch {
            val endpoint = "faculty/attendance/students"
            val method = "GET_ALL_STUDENTS_FOR_ATTENDANCE"
            val userRequestData = AllStudentsForAttendanceReq(
                selectedSemester,
                prefs.getBranch(),
                prefs.getToken()
            )
            // to take old attendance if(not condition just example) exits add to to the studentAttendance's attendanceData if not we get empty list so further we can take attendance
            val endpoint1 = "faculty/attendance/unique"
            val method1 = "GET_OLD_ATTENDANCE"
            val userRequestData1 = GetOldAttendanceReq(
                prefs.getBranch(),
                selectedDate,
                selectedSemester,
                prefs.getToken(),
                selectedSubject
            )
            val result = ApiUtils.fetchData(endpoint, method, userRequestData)
            val result1 = ApiUtils.fetchData(endpoint1, method1, userRequestData1)
            if (result is AllStudentsForAttendanceRes) {
                studentsList = result.data
                if (result1 is GetOldAttendanceRes) {
                    if (result1.data.isNotEmpty()) {
                        attendanceList = result1.data[0].attendanceData
                        updateId = result1.data[0]._id
                    } else {
                        update = false
                        attendanceList = mutableListOf()
                    }
                    Log.d("olddata", studentsAttendance.toString())
                } else {
                    Toast.makeText(
                        requireContext(),
                        "something went wrong",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                studentsAttendance = SendStudentAttendanceReq(
                    attendanceList,
                    selectedDate,
                    AppPreferences(requireContext()).getBranch(),
                    selectedSemester,
                    AppPreferences(requireContext()).getToken(),
                    selectedSubject,
                    updateId
                )
                reyclerAdapter = AttendenceRegisterRecyclerAdapter(
                    requireContext(),
                    studentsAttendance,
                    studentsList,
                    allPresentBtn,
                    allAbsentBtn
                )
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = reyclerAdapter
                doneBtn.visibility = View.VISIBLE

            } else {
                Toast.makeText(
                    requireContext(),
                    "something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        doneBtn.setOnClickListener {
            if (::studentsAttendance.isInitialized) {
                val data: SendStudentAttendanceReq = reyclerAdapter.getStudentAttendanceData()
                CoroutineScope(Dispatchers.Main).launch {
                    val endpoint = "faculty/attendance"
                    val method = "CREATE_ATTENDANCE"
                    val endpoint1 = "faculty/attendance/update"
                    val method1 = "UPDATE_ATTENDANCE"
                    val result: Any = if (!update) {
                        ApiUtils.fetchData(endpoint, method, data)
                    } else {
                        ApiUtils.fetchData(endpoint1, method1, data)
                    }

                    if (result is Success) {
                        if (result.success) {
                            Toast.makeText(
                                requireContext(),
                                "attendance is successfully stored",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "please try again",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    } else {
                        Toast.makeText(
                            requireContext(),
                            "something went wrong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "all students are loaded",
                    Toast.LENGTH_SHORT
                ).show()
            }
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

//    fun addToDatabase(context: Context) {
//        val list = StudentAttendenceListAsyncTask(context, 3).execute().get()
//        if (list.size == 0) {
//            for (i in studentList) {
//                val studentAttendenceEntity = StudentAttendenceEntity(i.Sname, i.Sroll, false)
//                StudentAttendenceAsyncTask(context, studentAttendenceEntity, 1).execute().get()
//            }
//        }
//    }
}