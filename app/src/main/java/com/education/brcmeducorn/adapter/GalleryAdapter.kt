package com.education.brcmeducorn.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.model.GalleryModel

class GalleryAdapter(private val galleryList: ArrayList<GalleryModel>?, context: Context?) :
    RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    private val context: Context? = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val galleryItem: GalleryModel = galleryList!![position]
        holder.galaryImg.setImageResource(galleryItem.personImg)
        holder.description.text = galleryItem.personDescription

        holder.itemView.setOnClickListener {
            // Handle item click
            // For example, open a full image activity
//            val intent = Intent(context, FullImageGallaryActivity::class.java)
//            intent.putExtra("image_link_college_galary", galleryItem.personImg)
//            context?.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return galleryList?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var galaryImg: ImageView
        var description: TextView

        init {
            galaryImg = itemView.findViewById(R.id.grid_item_image)
            description = itemView.findViewById(R.id.description)
        }
    }
}




