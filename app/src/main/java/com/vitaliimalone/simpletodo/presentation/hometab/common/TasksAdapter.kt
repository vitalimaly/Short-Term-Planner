package com.vitaliimalone.simpletodo.presentation.hometab.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.presentation.utils.DateTimeUtils
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.tasks_list_item.*

class TasksAdapter : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {
    var tasks = listOf<Task>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tasks_list_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    inner class TaskViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {
        fun bind(task: Task) {
            titleTextView.text = task.title
            doneCheckBox.isChecked = task.isDone
            dueDateTextView.text = DateTimeUtils.getTaskDueDate(task.dueTo)
            tagsTextView.isVisible = task.tags.isNotEmpty()
            tagsTextView.text = task.tags.joinToString()
        }
    }
}
