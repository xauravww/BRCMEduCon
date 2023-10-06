package com.education.brcmeducorn.fragments.student_dashboard_fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.education.brcmeducorn.R
import com.education.brcmeducorn.adapter.GalleryRecyclerAdapter

class GalleryFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: GalleryRecyclerAdapter
    lateinit var layoutManager:LayoutManager
    val imageList = listOf<Int>(R.drawable.gallery1,R.drawable.gallery2,R.drawable.gallery3,R.drawable.gallery4,R.drawable.gallery5,R.drawable.gallery1,R.drawable.gallery2,R.drawable.gallery3,R.drawable.gallery4,R.drawable.gallery5,R.drawable.hk_chaudhary,R.drawable.human_image,R.drawable.hod_cse,R.drawable.imgevent1,R.drawable.imgevent2,R.drawable.firstyear_hod)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)

        recyclerView = view.findViewById(R.id.stGalleryRecyclerView)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerAdapter = GalleryRecyclerAdapter(activity as Context,imageList)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = layoutManager
        return view
    }



}