package com.vitaliimalone.shorttermplanner.presentation.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.shorttermplanner.R
import com.vitaliimalone.shorttermplanner.presentation.utils.Res
import kotlin.math.round

class StripedRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {
    private val paint = Paint().apply {
        color = Res.color(context, R.attr.themeDividerColor)
        strokeWidth = round(Res.dimen(context, R.dimen.default_divider_height))
    }
    private val defaultItemHeight = Res.dimen(context, R.dimen.home_task_item_min_height)
    private val dividerMargin = Res.dimen(context, R.dimen.home_divider_margin)

    override fun onDraw(canvas: Canvas?) {
        var y = computeVerticalScrollRange().toFloat()
        if (childCount == 0) {
            y += defaultItemHeight
        }
        while (y < height) {
            canvas?.let {
                it.drawLine(dividerMargin, y, width - dividerMargin, y, paint)
                y += defaultItemHeight
            }
        }
        super.onDraw(canvas)
    }
}