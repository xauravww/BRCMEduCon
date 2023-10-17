package com.education.brcmeducorn.fragments.student_dashboard_fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.education.brcmeducorn.R
import com.squareup.picasso.Picasso

class TimeTable2Fragment : Fragment() {

    lateinit var imgTimeTable:ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_time_table2, container, false)
        imgTimeTable = view.findViewById(R.id.imgTimeTable)

        imgTimeTable.setOnClickListener {
            showImageOnFullScreen(R.drawable.timetable)
        }

        return view
    }

    private fun showImageOnFullScreen(imageResource:Int)
    {
        val dialog = Dialog(activity as Context, android.R.style.Theme_Material_Light_NoActionBar_Fullscreen)
        dialog.setContentView(R.layout.fragment_gallery_full_screen_image)

        val fullScreenImageView = dialog.findViewById<ImageView>(R.id.fullScreenImageView)
        val downloadButton = dialog.findViewById<ImageButton>(R.id.downloadButton)

        Picasso.get().load(imageResource).into(fullScreenImageView)

        downloadButton.setOnClickListener {
            Toast.makeText(activity as Context,"clicked", Toast.LENGTH_SHORT).show()
        }

        dialog.show()
    }


}