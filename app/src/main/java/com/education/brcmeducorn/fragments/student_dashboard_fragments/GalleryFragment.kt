package com.education.brcmeducorn.fragments.student_dashboard_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.education.brcmeducorn.R
import com.education.brcmeducorn.adapter.GalleryAdapter
import com.education.brcmeducorn.model.GalleryModel

class GalleryFragment : Fragment() {
    var galaryList: ArrayList<GalleryModel>? = null
    var clgRecycleView: RecyclerView? = null
    var gridAdapter: GalleryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_gallery, container, false)

        // Initialize galaryList with an empty ArrayList
        galaryList = ArrayList()

        // Add a single item to galaryList
        val imageUrl = "https://example.com/single_image.jpg" // Replace with your actual image URL
        val description = "Description" // Replace with your desired description
        val galleryItem = GalleryModel(
            description,
            R.drawable.brcm_logo_big
        )
        val galleryItem2 = GalleryModel(
            description,
            R.drawable.firstyear_hod
        )
        val galleryItem3 = GalleryModel(
            description,
            R.drawable.civil_hod
        ) // Replace with your image resource
        galaryList!!.add(galleryItem)
        galaryList!!.add(galleryItem2)
        galaryList!!.add(galleryItem)
        galaryList!!.add(galleryItem)
        galaryList!!.add(galleryItem3)
        galaryList !!. add (galleryItem)
        galaryList!!.add(galleryItem2)
        galaryList!!.add(galleryItem)
        galaryList!!.add(galleryItem)
        galaryList!!.add(galleryItem3)
        galaryList!!.add(galleryItem)

        clgRecycleView = view.findViewById(R.id.clg_galary_recycle_view)
        gridAdapter = GalleryAdapter(galaryList, context)

        clgRecycleView?.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        clgRecycleView?.adapter = gridAdapter

        return view
    }
}
