package com.education.brcmeducorn.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.education.brcmeducorn.R
import com.education.brcmeducorn.model.TimeSlotModel
import com.makeramen.roundedimageview.RoundedImageView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TimeSlotAdapter(private val timeSlots: List<TimeSlotModel>) :
    RecyclerView.Adapter<TimeSlotAdapter.TimeSlotViewHolder>() {
   private val timeRanges = listOf(
        Pair(541, 585),   // 9:00 AM to 9:45 AM
        Pair(586, 630),   // 9:45 AM to 10:30 AM
        Pair(631, 675),   // 10:30 AM to 11:15 AM
        Pair(676, 720),   // 11:15 AM to 12:00 PM
        Pair(721, 765),   // 12:00 PM to 12:45 PM
        Pair(766, 810),   // 12:45 PM to 1:30 PM
        Pair(811, 855),   // 1:30 PM to 2:15 PM
        Pair(856, 900),   // 2:15 PM to 3:00 PM
        Pair(900, 1100)    // 2:15 PM to 3:00 PM
    )

    class TimeSlotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val personName = itemView.findViewById<TextView>(R.id.person_name)
        val personPosition = itemView.findViewById<TextView>(R.id.person_position)
        val personDescription = itemView.findViewById<TextView>(R.id.person_description)
        val personImg = itemView.findViewById<RoundedImageView>(R.id.person_img)
        val timeStart = itemView.findViewById<TextView>(R.id.time_start)
        val timeEnd = itemView.findViewById<TextView>(R.id.time_end)
//        val customView = itemView.findViewById<com.education.brcmeducorn.customview.com.education.brcmeducorn.customview.com.education.brcmeducorn.customview.com.education.brcmeducorn.customview.com.education.brcmeducorn.customview.com.education.brcmeducorn.customview.com.education.brcmeducorn.customview.CustomView>(R.id.custom_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeSlotViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.period_item, parent, false)
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

        // Calculate if this time slot is active
        val isActive = isTimeSlotActive()

        // Calculate custom view visibility
        val isCustomViewVisible = calculateCustomViewVisibility(position)
        // Set custom view visibility
//        holder.customView.visibility = if (isCustomViewVisible) View.VISIBLE else View.GONE

        // Move the custom view only if this time slot is active
//        if (isActive && isCustomViewVisible) {
//            holder.
//            holder.customView.moveDown()
//        } else {
//            holder.customView.resetPosition()
//        }
    }

    override fun getItemCount() = timeSlots.size

    private fun isTimeSlotActive(): Boolean {
        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val currentTime = Calendar.getInstance()
        val startTime = Calendar.getInstance()
        val endTime = Calendar.getInstance()

        startTime.set(Calendar.HOUR_OF_DAY, 9)
        endTime.set(Calendar.HOUR_OF_DAY, 15)

        return currentTime.after(startTime) && currentTime.before(endTime)
    }

    private fun calculateCustomViewVisibility(itemPosition: Int): Boolean {

        val currentTime = Calendar.getInstance()
        val currentHour = currentTime.get(Calendar.HOUR_OF_DAY)
        val currentMinute = currentTime.get(Calendar.MINUTE)

        val currentTotalMinute = (currentHour * 60) + currentMinute


        var matchIndex: Int? = null

        for ((index, range) in timeRanges.withIndex()) {
            val (startRange, endRange) = range

            if (currentTotalMinute in startRange..endRange) {
                matchIndex = index
                break
            }
        }

        return matchIndex == itemPosition
    }

}
