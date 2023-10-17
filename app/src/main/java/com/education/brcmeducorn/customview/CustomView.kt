package com.education.brcmeducorn.customview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class CustomView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val arrowHeight = 2.dpToPx().toFloat()
    private val arrowPaint = Paint().apply {
        color = 0xFFFF0000.toInt()
    }

    private var totalTravelPixel = 0
    private var totalTimeInMillis = 0
    private val arrowPath = Path()

    init {
        // Ensure the view has a minimum size
        minimumHeight = arrowHeight.toInt()
    }

    fun moveDown(totalPixel: Int, timeInMillis: Int) {
        totalTravelPixel = totalPixel
        totalTimeInMillis = timeInMillis
        val animator = ValueAnimator.ofFloat(0f, totalPixel.toFloat())
        animator.duration = timeInMillis.toLong()
        animator.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Float
            translationY = value
            invalidate()
        }
        animator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Set the paint stroke width for the red line
        arrowPaint.strokeWidth = arrowHeight  // Set the stroke width to the arrow height

        // Draw the line
        val startY = height / 2f
        val endX = width.toFloat()
        val endY = height / 2f
        canvas.drawLine(0f, startY, endX, endY, arrowPaint)

        // Draw the arrowhead
        val arrowSize = arrowHeight * 4 // Adjust arrow size as needed


        arrowPath.moveTo(endX - arrowSize, endY - arrowSize)
        arrowPath.lineTo(endX, endY)
        arrowPath.lineTo(endX - arrowSize, endY + arrowSize)
        arrowPath.close()

        canvas.drawPath(arrowPath, arrowPaint)
    }




    private fun Int.dpToPx(): Int {
        return (this * resources.displayMetrics.density).toInt()
    }
}
