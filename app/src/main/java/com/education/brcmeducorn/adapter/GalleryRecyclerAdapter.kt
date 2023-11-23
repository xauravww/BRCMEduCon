package com.education.brcmeducorn.adapter

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.api.apiModels.DataXXX
import com.squareup.picasso.Picasso

class GalleryRecyclerAdapter(
    val context: Context,
    var list: MutableList<DataXXX>
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
        Picasso.get().load(src.image.url).into(holder.imgGallery)

        holder.imgGallery.setOnClickListener {

            showImageOnFullScreen(src.image.url)
        }
    }

    private fun showImageOnFullScreen(imageResource: String)
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