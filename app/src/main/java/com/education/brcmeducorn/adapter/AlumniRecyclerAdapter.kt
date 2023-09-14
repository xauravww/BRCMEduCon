package com.education.brcmeducorn.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.model.Alumni

class AlumniRecyclerAdapter(val context: Context, val itemList: ArrayList<Alumni>) :
    RecyclerView.Adapter<AlumniRecyclerAdapter.AlumniViewHolder>() {
    class AlumniViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.txtRecyclerRowItem)
        var imgAlumniProfile: ImageView = view.findViewById(R.id.imgAlumniProfile)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlumniViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.alumni_card_item, parent, false)
        return AlumniViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: AlumniViewHolder, position: Int) {
        val alumni = itemList[position]
        holder.textView.text = alumni.name
        holder.imgAlumniProfile.setImageResource(R.drawable.human_image)
    }


}