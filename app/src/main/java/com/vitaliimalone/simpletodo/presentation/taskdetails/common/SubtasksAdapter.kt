package com.vitaliimalone.simpletodo.presentation.taskdetails.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.domain.models.Subtask
import com.vitaliimalone.simpletodo.presentation.utils.Res
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.task_details_subtasks_list_item.*

class SubtasksAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private enum class SubtaskViewType { SUBTASK, ADDNEW }

    var subtasks = mutableListOf<Subtask>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_details_subtasks_list_item, parent, false)
        return when (viewType) {
            SubtaskViewType.SUBTASK.ordinal -> TaskViewHolder(view)
            SubtaskViewType.ADDNEW.ordinal -> AddNewTaskViewHolder(view)
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TaskViewHolder -> holder.bind(subtasks[position], position)
            is AddNewTaskViewHolder -> holder.bind()
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int {
        return if (subtasks.isEmpty()) {
            1
        } else {
            subtasks.size + 1
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            subtasks.size -> SubtaskViewType.ADDNEW.ordinal
            else -> SubtaskViewType.SUBTASK.ordinal
        }
    }

    inner class TaskViewHolder(
            override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(subtask: Subtask, position: Int) {
            taskTitleEt.setText(subtask.title)
            doneCheckBox.isChecked = subtask.isDone

            topLineView.isVisible = true
            horizontalLineView.isVisible = true
            botLineView.isVisible = subtasks.lastIndex != position
        }
    }

    inner class AddNewTaskViewHolder(
            override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind() {
            doneCheckBox.buttonDrawable = Res.drawable(R.drawable.ic_add_checkbox)
            taskTitleEt.text?.clear()
            taskTitleEt.isFocusable = false
            taskTitleEt.isFocusableInTouchMode = false
            doneCheckBox.isChecked = false

            topLineView.isVisible = false
            horizontalLineView.isVisible = false
            botLineView.isVisible = false

            taskTitleEt.setOnClickListener {
                subtasks.add(Subtask())
                notifyDataSetChanged()
            }
        }
    }
}
