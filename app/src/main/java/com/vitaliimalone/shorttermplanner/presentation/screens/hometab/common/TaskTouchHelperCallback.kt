package com.vitaliimalone.shorttermplanner.presentation.screens.hometab.common

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.text.TextPaint
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.shorttermplanner.R
import com.vitaliimalone.shorttermplanner.presentation.utils.Res

class TaskTouchHelperCallback(
    context: Context,
    private val onSwipe: ((position: Int, direction: Int) -> Unit),
    private val swipeLeftText: String,
    private val swipeRightText: String,
    @ColorInt private val swipeLeftBackgroundColor: Int,
    @ColorInt private val swipeRightBackgroundColor: Int
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
    private val swipeLeftIconId = Res.drawable(context, R.drawable.ic_chevron_left)
    private val swipeRightIconId = Res.drawable(context, R.drawable.ic_chevron_right)
    private val iconHorizontalMargin = Res.dimen(context, R.dimen.home_task_item_min_height).toInt() / 4
    private val textSize = Res.dimen(context, R.dimen.l_text_size)
    private val textFont = Res.font(context, R.font.red_hat_display_regular)
    private val textPaint = TextPaint()
    private val swipeLeftBackgroundColorDrawable = ColorDrawable(swipeLeftBackgroundColor)
    private val swipeRightBackgroundColorDrawable = ColorDrawable(swipeRightBackgroundColor)

    override fun onMove(
        recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ) = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) =
        onSwipe.invoke(viewHolder.adapterPosition, direction)

    override fun onChildDraw(
        c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float,
        dY: Float, actionState: Int, isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        val tintColor = Res.color(recyclerView.context, R.attr.themeTextColorPrimary)
        if (dX > 0) {
            // background
            swipeRightBackgroundColorDrawable.setBounds(
                viewHolder.itemView.left, viewHolder.itemView.top,
                viewHolder.itemView.left + dX.toInt(),
                viewHolder.itemView.bottom
            )
            swipeRightBackgroundColorDrawable.draw(c)
            // background

            // icon
            var iconSize = 0
            swipeRightIconId?.let {
                iconSize = it.intrinsicHeight
                val halfIcon = iconSize / 2
                val top =
                    viewHolder.itemView.top + ((viewHolder.itemView.bottom - viewHolder.itemView.top) / 2 - halfIcon)
                it.setBounds(
                    viewHolder.itemView.left + iconHorizontalMargin, top,
                    viewHolder.itemView.left + iconHorizontalMargin + it.intrinsicWidth,
                    top + it.intrinsicHeight
                )
                it.setTint(tintColor)
                it.draw(c)
            }
            // icon

            // text
            textPaint.isAntiAlias = true
            textPaint.textSize = textSize
            textPaint.color = tintColor
            textPaint.typeface = textFont
            val textTop = (viewHolder.itemView.top.toDouble()
                + (viewHolder.itemView.bottom - viewHolder.itemView.top) / 2.0
                + (textPaint.textSize / 2).toDouble()).toInt() - iconHorizontalMargin / 5
            c.drawText(
                swipeRightText,
                (viewHolder.itemView.left + iconHorizontalMargin + iconSize
                    + if (iconSize > 0) iconHorizontalMargin / 2 else 0).toFloat(),
                textTop.toFloat(), textPaint
            )
            // text
        } else if (dX < 0) {
            // background
            swipeLeftBackgroundColorDrawable.setBounds(
                viewHolder.itemView.right + dX.toInt(), viewHolder.itemView.top,
                viewHolder.itemView.right, viewHolder.itemView.bottom
            )
            swipeLeftBackgroundColorDrawable.draw(c)
            // background

            // icon
            var imgLeft = 0
            swipeLeftIconId?.let {
                val iconSize = it.intrinsicHeight
                val halfIcon = iconSize / 2
                val top =
                    viewHolder.itemView.top + ((viewHolder.itemView.bottom - viewHolder.itemView.top) / 2 - halfIcon)
                imgLeft = viewHolder.itemView.right - iconHorizontalMargin - halfIcon * 2
                it.setBounds(imgLeft, top, viewHolder.itemView.right - iconHorizontalMargin, top + it.intrinsicHeight)
                it.setTint(tintColor)
                it.draw(c)
                // icon
            }

            // text
            textPaint.isAntiAlias = true
            textPaint.textSize = textSize
            textPaint.color = tintColor
            textPaint.typeface = textFont
            val width = textPaint.measureText(swipeLeftText)
            val textTop = (viewHolder.itemView.top.toDouble()
                + (viewHolder.itemView.bottom - viewHolder.itemView.top) / 2.0
                + (textPaint.textSize / 2).toDouble()).toInt() - iconHorizontalMargin / 5
            c.drawText(
                swipeLeftText, imgLeft.toFloat() - width
                    - (if (imgLeft == viewHolder.itemView.right) iconHorizontalMargin else iconHorizontalMargin / 2).toFloat(),
                textTop.toFloat(), textPaint
            )
            // text
        }
    }
}