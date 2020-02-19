package com.vitaliimalone.simpletodo.presentation.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.InsetDrawable
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R

class DefaultDividerItemDecoration(
    context: Context,
    drawableResourceId: Int? = null,
    marginLeft: Float = 0f,
    marginTop: Float = 0f,
    marginRight: Float = 0f,
    marginBottom: Float = 0f
) : DividerItemDecoration(context, VERTICAL) {

    init {
        val drawable = context.getDrawable(drawableResourceId ?: R.drawable.divider_default)
        val insetDivider = InsetDrawable(
            drawable, marginLeft.toInt(), marginTop.toInt(), marginRight.toInt(),
            marginBottom.toInt()
        )
        setDrawable(insetDivider)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val dividerLeft = parent.paddingLeft
        val dividerRight = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0..childCount - 2) {
            val child: View = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val dividerTop: Int = child.bottom + params.bottomMargin
            drawable?.run {
                setBounds(dividerLeft, dividerTop, dividerRight, dividerTop + intrinsicHeight)
                draw(c)
            }
        }
    }
}