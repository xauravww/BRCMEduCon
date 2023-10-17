package com.education.brcmeducorn.adapter

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.model.Subjects
import com.squareup.picasso.Picasso

class SubjectsRecyclerAdapter(val context: Context,val listofSubjects: List<Subjects>):RecyclerView.Adapter<SubjectsRecyclerAdapter.SubjectsViewHolder>() {

    class SubjectsViewHolder(val view:View):RecyclerView.ViewHolder(view)
    {
        val rl:RelativeLayout = view.findViewById(R.id.rl)
        val txtCourseTitle:TextView = view.findViewById(R.id.txtCourseTitle)
        val txtCourseCode:TextView = view.findViewById(R.id.txtCourseCode)
        val txtCourseCategory:TextView = view.findViewById(R.id.txtCourseCategory)
        val imgSyallabus:ImageView = view.findViewById(R.id.imgSyallabus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectsViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_subjects_single_row,parent,false)
        return SubjectsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listofSubjects.size
    }

    override fun onBindViewHolder(holder: SubjectsViewHolder, position: Int) {
      val item = listofSubjects[position]

        holder.txtCourseTitle.text = item.courseName
        holder.txtCourseCode.text = item.courseCode
        holder.txtCourseCategory.text = item.courseCategory
        holder.imgSyallabus.setImageResource(item.imgSyallabus)

        holder.rl.setOnClickListener{
            showImageOnFullScreen(item.imgSyallabus)
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
            Toast.makeText(context,"clicked", Toast.LENGTH_SHORT).show()
        }

        dialog.show()
    }
}