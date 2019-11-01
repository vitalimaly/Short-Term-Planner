package com.vitaliimalone.simpletodo.presentation.hometab.common

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.text.TextPaint
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.Res


class TaskTouchHelperCallback(
        private val onSwipe: ((position: Int, direction: Int) -> Unit)
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
    private val icon: Drawable = Res.drawable(R.drawable.ic_access_time)
    private val background: ColorDrawable = ColorDrawable(Res.color(R.color.pale_background_dark_color))

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onSwipe.invoke(viewHolder.adapterPosition, direction)
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float,
                             dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        val itemView = viewHolder.itemView
        val iconMargin = Res.dimen(R.dimen.home_task_item_min_height).toInt() / 2
        val iconTop = itemView.top + (itemView.height - icon.intrinsicHeight) / 2
        val iconBottom = iconTop + icon.intrinsicHeight
        when {
            dX > 0 -> { // Swiping to the right
                val iconLeft = itemView.left + iconMargin + icon.intrinsicWidth
                val iconRight = itemView.left + iconMargin
                icon.setBounds(iconRight, iconTop, iconLeft, iconBottom)
                background.setBounds(itemView.left, itemView.top, itemView.left + dX.toInt(), itemView.bottom)
            }
            dX < 0 -> { // Swiping to the left
                val iconLeft = itemView.right - iconMargin - icon.intrinsicWidth
                val iconRight = itemView.right - iconMargin
                icon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                background.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
            }
            else -> { // view is unSwiped
                background.setBounds(0, 0, 0, 0)
            }
        }

        val textPaint = TextPaint()
        textPaint.isAntiAlias = true
        textPaint.textSize = Res.dimen(R.dimen.l_text_size)
        textPaint.color = Res.color(R.color.primary_white_text_color)
//        textPaint.typeface =


        val testText = "Tost toxt O"
        val width = textPaint.measureText(testText)
        val textTop = (viewHolder.itemView.top + (viewHolder.itemView.bottom - viewHolder.itemView.top) / 2.0 + textPaint.textSize / 2).toFloat()


        background.draw(c)
        icon.draw(c)
        c.drawText(testText, viewHolder.itemView.right - width - iconMargin, textTop, textPaint)
    }
}