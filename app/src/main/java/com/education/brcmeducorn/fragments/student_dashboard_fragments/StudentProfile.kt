package com.education.brcmeducorn.fragments.student_dashboard_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.education.brcmeducorn.R
import com.education.brcmeducorn.utils.SharedPrefs

class StudentProfile : Fragment() {
    private lateinit var prefs: SharedPrefs


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_student_profile, container, false)
        prefs = SharedPrefs(requireContext())



        return view
    }

    private fun getPrefs(): Map<String, String> {
        val token = prefs.getString("token", "")
        val name = prefs.getString("name", "")
        val rollNo = prefs.getString("rollNo", "")
        val roll = prefs.getString("roll", "")
        val imageUrl = prefs.getString("imageUrl", "")

        return mapOf(
            "token" to token,
            "name" to name,
            "rollNo" to rollNo,
            "roll" to roll,
            "imageUrl" to imageUrl
        )
    }
}