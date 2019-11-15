package com.vitaliimalone.simpletodo.presentation.taskdetails.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.domain.models.Subtask
import com.vitaliimalone.simpletodo.presentation.utils.showKeyboard
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.task_details_add_new_subtasks_list_item.*
import kotlinx.android.synthetic.main.task_details_subtasks_list_item.*

class SubtasksAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var subtaskListItems = mutableListOf<SubtaskListItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            SubtaskViewType.SUBTASK.ordinal -> {
                TaskViewHolder(inflateView(R.layout.task_details_subtasks_list_item, parent))
            }
            SubtaskViewType.ADDNEW.ordinal -> {
                AddNewTaskViewHolder(inflateView(R.layout.task_details_add_new_subtasks_list_item, parent))
            }
            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TaskViewHolder -> holder.bind(subtaskListItems[position], position)
            is AddNewTaskViewHolder -> holder.bind()
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int {
        return if (subtaskListItems.isEmpty()) {
            1
        } else {
            subtaskListItems.size + 1
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            subtaskListItems.size -> SubtaskViewType.ADDNEW.ordinal
            else -> SubtaskViewType.SUBTASK.ordinal
        }
    }

    private fun inflateView(@LayoutRes resource: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(resource, parent, false)
    }

    private fun addSubtask() {
        subtaskListItems.forEach { it.isFocused = false }
        subtaskListItems.add(SubtaskListItem(Subtask(), true))
        notifyItemInserted(subtaskListItems.lastIndex)
        if (subtaskListItems.lastIndex - 1 >= 0) {
            notifyItemChanged(subtaskListItems.lastIndex - 1)
        }
    }

    inner class TaskViewHolder(
            override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(subtaskListItem: SubtaskListItem, position: Int) {
            taskTitleEt.setText(subtaskListItem.subtask.title)
            if (subtaskListItem.isFocused) {
                taskTitleEt.post {
                    taskTitleEt.requestFocus()
                    taskTitleEt.showKeyboard()
                }
            }
            doneCheckBox.isChecked = subtaskListItem.subtask.isDone
            botLineView.isVisible = subtaskListItems.lastIndex != position

        }
    }

    inner class AddNewTaskViewHolder(
            override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind() {
            addSubtaskContainer.setOnClickListener {
                addSubtask()
            }
        }
    }
}
