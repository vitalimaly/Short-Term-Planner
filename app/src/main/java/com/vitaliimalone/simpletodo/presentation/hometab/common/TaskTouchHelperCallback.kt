package com.vitaliimalone.simpletodo.presentation.hometab.common

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class TaskTouchHelperCallback(
        private val onSwipeLeft: ((position: Int) -> Unit),
        private val onSwipeRight: ((position: Int) -> Unit)
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (direction == ItemTouchHelper.LEFT) {
            onSwipeLeft.invoke(viewHolder.adapterPosition)
        } else if (direction == ItemTouchHelper.RIGHT) {
            onSwipeRight.invoke(viewHolder.adapterPosition)
        }
    }
}