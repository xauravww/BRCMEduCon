package com.education.brcmeducorn.fragments.student_dashboard_fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.ScaleAnimation
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.education.brcmeducorn.R
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.Reflection.initialize
import com.squareup.picasso.Picasso

class ResultsFragment : Fragment() {

    lateinit var first:CardView
    lateinit var second:CardView
    lateinit var third:CardView
    lateinit var forth:CardView
    lateinit var fifth:CardView
    lateinit var sixth:CardView
    lateinit var seventh:CardView
    lateinit var eighth:CardView

    private var isZoomedIn = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_results, container, false)


        // initialize all card views
        initialize(view)

        // set click listeners on cards
        setClickListerners()

        return view
    }

    private fun initialize(view:View)
    {
        first = view.findViewById(R.id.first)
        second = view.findViewById(R.id.second)
        third = view.findViewById(R.id.third)
        forth = view.findViewById(R.id.forth)
        fifth = view.findViewById(R.id.fifth)
        sixth = view.findViewById(R.id.sixth)
        seventh = view.findViewById(R.id.seventh)
        eighth = view.findViewById(R.id.eightth)
    }

    private fun setClickListerners()
    {
        first.setOnClickListener {

        }

        second.setOnClickListener {


        }

        third.setOnClickListener {
            showImageOnFullScreen(R.drawable.imgsem3)
        }

        forth.setOnClickListener {
            showImageOnFullScreen(R.drawable.imgsem4)
        }

        fifth.setOnClickListener {
            showImageOnFullScreen(R.drawable.imgsem5)
        }

        sixth.setOnClickListener {
            showImageOnFullScreen(R.drawable.imgsem6)
        }


    }

    private fun showImageOnFullScreen(imageResource:Int)
    {
        val dialog = Dialog(activity as Context, android.R.style.Theme_Material_Light_NoActionBar_Fullscreen)
        dialog.setContentView(R.layout.fragment_gallery_full_screen_image)

        val fullScreenImageView = dialog.findViewById<ImageView>(R.id.fullScreenImageView)
        val downloadButton = dialog.findViewById<ImageButton>(R.id.downloadButton)

        Picasso.get().load(imageResource).into(fullScreenImageView)

        downloadButton.setOnClickListener {
            Toast.makeText(context,"clicked", Toast.LENGTH_SHORT).show()
        }

        dialog.show()
    }

}