package com.education.brcmeducorn.fragments.admin_dashboard_fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.education.brcmeducorn.R

class AddOrRemoveMembersFragment : Fragment() {
    lateinit var txtBranch:Spinner
    lateinit var txtSemester:Spinner
    var branchArray = arrayOf("Branch","Cse","Civil","Mechanical","Electrical")
    var semesterArray = arrayOf("Semester","Sem1","Sem2","Sem3","Sem4","Sem5","Sem6","Sem7","Sem8")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_or_remove_members, container, false)

        txtBranch = view.findViewById(R.id.txtBranch)
        txtSemester = view.findViewById(R.id.txtSemester)

        val branchAdapter = ArrayAdapter(activity as Context,R.layout.spinner_item,branchArray)
        val semAdapter = ArrayAdapter(activity as Context,R.layout.spinner_item,semesterArray)

        txtBranch.adapter = branchAdapter
        txtSemester.adapter  = semAdapter
        return view
    }


}