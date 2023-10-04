package com.education.brcmeducorn.fragments.admin_dashboard_fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.education.brcmeducorn.R
import com.hbb20.CountryCodePicker

class AddOrRemoveMembersFragment : Fragment() {
    lateinit var txtBranch:Spinner
    lateinit var txtSemester:Spinner

    lateinit var edtName:EditText
    lateinit var spinnerBranch:Spinner
    lateinit var spinnerSemester:Spinner
    lateinit var txtBatch:EditText
    lateinit var txtRegistrationNo:EditText
    lateinit var txtRollNo:EditText
    lateinit var txtUserMail:EditText
    lateinit var countryCode:CountryCodePicker
    lateinit var txtPhoneNo:EditText
    lateinit var txtAddress:EditText
    lateinit var btnUpdateDetails:Button


    var branchArray = arrayOf("Branch","Cse","Civil","Mechanical","Electrical")
    var semesterArray = arrayOf("Semester","Sem1","Sem2","Sem3","Sem4","Sem5","Sem6","Sem7","Sem8")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_or_remove_members, container, false)

        txtBranch = view.findViewById(R.id.spinnerBranch)
        txtSemester = view.findViewById(R.id.spinnerSemester)
        edtName = view.findViewById(R.id.edtName)
        spinnerBranch = view.findViewById(R.id.spinnerBranch)
        spinnerSemester = view.findViewById(R.id.spinnerSemester)
        txtBatch = view.findViewById(R.id.txtBatch)
        txtRegistrationNo = view.findViewById(R.id.txtRegistrationNo)
        txtRollNo = view.findViewById(R.id.txtRollNo)
        txtUserMail = view.findViewById(R.id.txtUserMail)
        countryCode = view.findViewById(R.id.countryCode)
        txtPhoneNo = view.findViewById(R.id.txtPhoneNo)
        txtAddress = view.findViewById(R.id.txtAddress)
        btnUpdateDetails = view.findViewById(R.id.btnUpdateDetails)

        val branchAdapter = ArrayAdapter(activity as Context,R.layout.spinner_item,branchArray)
        val semAdapter = ArrayAdapter(activity as Context,R.layout.spinner_item,semesterArray)

        txtBranch.adapter = branchAdapter
        txtSemester.adapter  = semAdapter
        return view
    }


}