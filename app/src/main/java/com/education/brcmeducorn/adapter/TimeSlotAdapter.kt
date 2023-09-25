package com.education.brcmeducorn.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.model.TimeSlotModel

import com.makeramen.roundedimageview.RoundedImageView

class TimeSlotAdapter(private val timeSlots: List<TimeSlotModel>) : RecyclerView.Adapter<TimeSlotAdapter.TimeSlotViewHolder>() {

    class TimeSlotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val personName = itemView.findViewById<TextView>(R.id.person_name)
        val personPosition = itemView.findViewById<TextView>(R.id.person_position)
        val personDescription = itemView.findViewById<TextView>(R.id.person_description)
        val personImg = itemView.findViewById<RoundedImageView>(R.id.person_img)
        val timeStart = itemView.findViewById<TextView>(R.id.time_start)
        val timeEnd = itemView.findViewById<TextView>(R.id.time_end)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeSlotViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.period_item, parent, false)
        return TimeSlotViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TimeSlotViewHolder, position: Int) {
        val currentItem = timeSlots[position]

        holder.personName.text = currentItem.personName
        holder.personPosition.text = currentItem.personPosition
        holder.personDescription.text = currentItem.personDescription
        holder.personImg.setImageResource(currentItem.personImg)
        holder.timeStart.text = currentItem.timeStart
        holder.timeEnd.text = currentItem.timeEnd
    }

    override fun getItemCount() = timeSlots.size
}
