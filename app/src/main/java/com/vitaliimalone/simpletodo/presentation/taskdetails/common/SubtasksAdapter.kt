package com.vitaliimalone.simpletodo.presentation.taskdetails.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.domain.models.Subtask
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.task_details_subtasks_list_item.*

class SubtasksAdapter : RecyclerView.Adapter<SubtasksAdapter.TaskViewHolder>() {
    var subtasks = listOf<Subtask>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_details_subtasks_list_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(subtasks[position], position)
    }

    override fun getItemCount(): Int {
        return subtasks.size
    }

    inner class TaskViewHolder(
            override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(subtask: Subtask, position: Int) {
            taskTitleEt.setText(subtask.title)
            doneCheckBox.isChecked = subtask.isDone
            botLineView.isVisible = subtasks.lastIndex != position
        }
    }
}
