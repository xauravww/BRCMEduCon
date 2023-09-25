package com.education.brcmeducorn.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CustomView : View {
    private var paint: Paint? = null

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?) : super(context) {
        init()
    }

    private fun init() {
        paint = Paint()
        paint!!.color = Color.RED
        paint!!.style = Paint.Style.FILL
        paint!!.strokeWidth = 4f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val startX = 0f
        val startY = height / 2f
        val endX = width.toFloat()
        val endY = height / 2f
        canvas.drawLine(startX, startY, endX, endY, paint!!)

        // Draw the arrowhead
        val arrowSize = 40f // Adjust arrow size as needed
        canvas.drawLine(endX - arrowSize, endY - arrowSize, endX, endY, paint!!)
        canvas.drawLine(endX - arrowSize, endY + arrowSize, endX, endY, paint!!)
    }

    fun setProgress(progress: Int) {
        val newY = height * (progress / 100f)
        translationY = newY
        invalidate()
    }
    fun moveDown() {
        val pixelsToCover = height.toFloat()  // Total height of the view
        val duration = 45 * 60 * 1000  // 45 minutes in milliseconds

        val pixelsPerMillisecond = pixelsToCover / duration
        val newY = translationY + pixelsPerMillisecond * 1000  // Move based on time passed (1000 milliseconds)
        translationY = newY

        if (newY > height) {
            translationY = -height.toFloat()  // Reset position if it's off-screen
        }

        invalidate()
    }


}