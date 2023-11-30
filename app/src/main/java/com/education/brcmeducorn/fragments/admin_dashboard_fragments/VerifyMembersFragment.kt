package com.education.brcmeducorn.fragments.admin_dashboard_fragments

import android.Manifest
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.education.brcmeducorn.R
import com.education.brcmeducorn.activites.AdminDashboardActivity
import com.education.brcmeducorn.activites.FacultyDashboardActivity
import com.education.brcmeducorn.activites.StudentDashboardActivity
import com.education.brcmeducorn.api.apiModels.LoginResponse
import com.education.brcmeducorn.api.apiModels.RegisterRequest
import com.education.brcmeducorn.utils.ApiUtils
import com.education.brcmeducorn.utils.SharedPrefs
import com.github.dhaval2404.imagepicker.ImagePicker
import com.hbb20.CountryCodePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class VerifyMembersFragment : Fragment() {
    private lateinit var txtBranch: Spinner
    private lateinit var txtSemester: Spinner
    private lateinit var imgUploadBtn: Button
    private lateinit var imgStudent: ImageView
    private lateinit var txtName: EditText
    private lateinit var txtbatch: EditText
    private lateinit var txtRegistrationNo: EditText
    private lateinit var txtUserMail: EditText
    private lateinit var txtPhoneNo: EditText
    private lateinit var countryCode: CountryCodePicker
    private lateinit var txtAddress: EditText
    private lateinit var txtPassword: EditText
    private lateinit var txtRollNo: EditText
    private lateinit var txtFather: EditText
    private lateinit var txtDOB: EditText
    private lateinit var btnUpdateDetails: Button
    private var editTextDOB: EditText? = null
    private var dobCalendar: Calendar? = null
    private var branchArray = arrayOf("Branch", "Cse", "Civil", "Mechanical", "Electrical")
    private var semesterArray = arrayOf("Semester", "Sem1", "Sem2", "Sem3", "Sem4", "Sem5", "Sem6", "Sem7", "Sem8")
    lateinit var prefs: SharedPrefs
    private lateinit var selectedImageUri: Uri

    companion object {
        var student_user = 1
        var faculty_user = 0
        var admin_user = 0
        var roll: String = "student"
        var selectedBranch: String = "branch"
        var selectedSemester: String = "sem"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_or_remove_members, container, false)
        val branchAdapter = ArrayAdapter(activity as Context, R.layout.spinner_item, branchArray)
        val semAdapter = ArrayAdapter(activity as Context, R.layout.spinner_item, semesterArray)


        txtBranch.adapter = branchAdapter
        txtSemester.adapter = semAdapter



        return view
    }

    private fun findViewById(view: View) {

        imgUploadBtn = view.findViewById(R.id.imgUploadBtn)
        imgStudent = view.findViewById(R.id.imgStudent)
        txtName = view.findViewById(R.id.txtName)
        txtBranch = view.findViewById(R.id.txtBranch)
        txtSemester = view.findViewById(R.id.txtSemester)
        txtbatch = view.findViewById(R.id.txtbatch)
        txtRegistrationNo = view.findViewById(R.id.txtRegistrationNo)
        txtUserMail = view.findViewById(R.id.txtUserMail)
        txtPhoneNo = view.findViewById(R.id.txtPhoneNo)
        countryCode = view.findViewById(R.id.countryCode)
        txtAddress = view.findViewById(R.id.txtAddress)
        txtFather = view.findViewById(R.id.txtFather)
        txtDOB = view.findViewById(R.id.txtDOB)
        txtPassword = view.findViewById(R.id.txtUserPass)
        txtRollNo = view.findViewById(R.id.txtRollNo)
        btnUpdateDetails = view.findViewById(R.id.btnUpdateDetails)

    }



 }