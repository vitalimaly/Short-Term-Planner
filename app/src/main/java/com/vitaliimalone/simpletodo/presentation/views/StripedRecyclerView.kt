package com.vitaliimalone.simpletodo.presentation.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import kotlin.math.round

class StripedRecyclerView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {
    private val paint = Paint().apply {
        color = resources.getColor(R.color.default_divider_color)
        strokeWidth = round(resources.getDimension(R.dimen.default_divider_height))
    }
    private val defaultItemHeight = (resources.getDimension(R.dimen.home_task_item_min_height))
    private val dividerMargin = resources.getDimension(R.dimen.home_divider_margin)

    override fun onDraw(canvas: Canvas) {
        var y = computeVerticalScrollRange() + defaultItemHeight
        while (y < height) {
            canvas.drawLine(dividerMargin, y, width - dividerMargin, y, paint)
            y += defaultItemHeight
        }
        super.onDraw(canvas)
    }
}