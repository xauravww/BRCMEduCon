package com.education.brcmeducorn.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.education.brcmeducorn.R
import com.education.brcmeducorn.adapter.AlumniRecyclerAdapter
import com.education.brcmeducorn.model.Alumni


class AlumniMeetFragment : Fragment() {
    private lateinit var spinner: Spinner
    private lateinit var recyclerDashboard: RecyclerView
    private lateinit var layoutManager: LayoutManager
    lateinit var recyclerAdapter:AlumniRecyclerAdapter

    //dummy names of alumni
    val alumniList =arrayListOf(
Alumni("Hans Kumar",R.drawable.human_image,"Entrepreneur"),
Alumni("Saurav",R.drawable.human_image,"Entrepreneur"),
Alumni("Harshit Sharma",R.drawable.human_image,"Placed"),
Alumni("Hemant Singh",R.drawable.human_image,"Placed"),
Alumni("Reason Sharma",R.drawable.human_image,"Placed"),
Alumni("Alok Ranjan",R.drawable.human_image,"Placed"),
Alumni("Pankaj",R.drawable.human_image,"Placed"),

    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    private fun addItemsToSpinner() {
        val items = arrayOf("None", "Placed", "No Info")
        val adapter: ArrayAdapter<String> =
            ArrayAdapter(context as Activity, com.education.brcmeducorn.R.layout.spinner_item, items)
        //here spinner is customized by R.layout.spinner_item
        spinner.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_alumni_meet, container, false)

        //binding the views
        spinner = view.findViewById(R.id.spinner)
        recyclerDashboard = view.findViewById(R.id.recyclerDashboard)
        layoutManager = LinearLayoutManager(activity)

        //adding list items to filter by and also style
        addItemsToSpinner()

        //attaching our adapter
        recyclerAdapter = AlumniRecyclerAdapter(activity as Context ,alumniList)

        recyclerDashboard.adapter = recyclerAdapter
        recyclerDashboard.layoutManager = layoutManager

        return view
    }




}