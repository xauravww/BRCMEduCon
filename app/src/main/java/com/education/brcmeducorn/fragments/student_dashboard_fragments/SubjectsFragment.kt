package com.education.brcmeducorn.fragments.student_dashboard_fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.adapter.GalleryRecyclerAdapter
import com.education.brcmeducorn.adapter.SubjectsRecyclerAdapter
import com.education.brcmeducorn.model.Subjects

class SubjectsFragment : Fragment() {

    lateinit var recyclerViewSubjects: RecyclerView
    lateinit var recyclerAdapter: SubjectsRecyclerAdapter
    lateinit var layoutManager: RecyclerView.LayoutManager

    var listofSubjects = listOf<Subjects>(
        Subjects("Neural Networks","PEC - CSE - 401G","Professional Elective Course",R.drawable.imgsyallabus),
        Subjects("Neural Networks","PEC - CSE - 401G","Professional Elective Course",R.drawable.imgsyallabus),
        Subjects("Neural Networks","PEC - CSE - 401G","Professional Elective Course",R.drawable.imgsyallabus),
        Subjects("Neural Networks","PEC - CSE - 401G","Professional Elective Course",R.drawable.imgsyallabus),
        Subjects("Neural Networks","PEC - CSE - 401G","Professional Elective Course",R.drawable.imgsyallabus),
        Subjects("Neural Networks","PEC - CSE - 401G","Professional Elective Course",R.drawable.imgsyallabus),
        Subjects("Neural Networks","PEC - CSE - 401G","Professional Elective Course",R.drawable.imgsyallabus),
        Subjects("Neural Networks","PEC - CSE - 401G","Professional Elective Course",R.drawable.imgsyallabus),
        Subjects("Neural Networks","PEC - CSE - 401G","Professional Elective Course",R.drawable.imgsyallabus),
        Subjects("Neural Networks","PEC - CSE - 401G","Professional Elective Course",R.drawable.imgsyallabus),
        )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_subjects, container, false)
        recyclerViewSubjects = view.findViewById(R.id.recyclerViewSubjects)

        layoutManager = LinearLayoutManager(activity as Context)

        recyclerAdapter = SubjectsRecyclerAdapter(activity as Context,listofSubjects)

        recyclerViewSubjects.adapter = recyclerAdapter
        recyclerViewSubjects.layoutManager = layoutManager
        return view
    }


}