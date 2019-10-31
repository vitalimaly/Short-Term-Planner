package com.vitaliimalone.simpletodo.presentation.hometab.common

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class TaskTouchHelperCallback(
        private val onSwipe: ((position: Int, direction: Int) -> Unit)
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onSwipe.invoke(viewHolder.adapterPosition, direction)
    }
}