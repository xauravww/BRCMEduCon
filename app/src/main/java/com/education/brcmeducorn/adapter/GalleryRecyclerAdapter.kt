package com.education.brcmeducorn.adapter

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog.show
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.fragments.student_dashboard_fragments.GalleryFragment
import com.squareup.picasso.Picasso
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.Random

class GalleryRecyclerAdapter(
    val context: Context,
    var list: List<Int>
):RecyclerView.Adapter<GalleryRecyclerAdapter.GalleryAdapterViewHolder>() {

    class GalleryAdapterViewHolder(val view: View):RecyclerView.ViewHolder(view)
    {

        var imgGallery:ImageView = view.findViewById(R.id.imgGallery)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_gallery_single_row,parent,false)
        return GalleryAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GalleryAdapterViewHolder, position: Int) {
        val src = list[position]
        Picasso.get().load(src).into(holder.imgGallery)

        holder.imgGallery.setOnClickListener {

            showImageOnFullScreen(src)
        }
    }

    private fun showImageOnFullScreen(imageResource:Int)
    {
        val dialog = Dialog(context, android.R.style.Theme_Material_Light_NoActionBar_Fullscreen)
        dialog.setContentView(R.layout.fragment_gallery_full_screen_image)

        val fullScreenImageView = dialog.findViewById<ImageView>(R.id.fullScreenImageView)
        val downloadButton = dialog.findViewById<ImageButton>(R.id.downloadButton)

        Picasso.get().load(imageResource).into(fullScreenImageView)

        downloadButton.setOnClickListener {
            Toast.makeText(context,"clicked",Toast.LENGTH_SHORT).show()
        }

        dialog.show()
    }



}