package com.education.brcmeducorn.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.squareup.picasso.Picasso
import java.util.Random

class GalleryRecyclerAdapter(val context: Context,var list: List<Int>):RecyclerView.Adapter<GalleryRecyclerAdapter.GalleryAdapterViewHolder>() {

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
    }
}