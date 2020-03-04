package com.vitaliimalone.shorttermplanner.presentation.screens.hometab.common

import android.graphics.Point
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.shorttermplanner.R
import com.vitaliimalone.shorttermplanner.domain.models.Task
import com.vitaliimalone.shorttermplanner.presentation.utils.DateTimeUtils
import com.vitaliimalone.shorttermplanner.presentation.utils.extensions.setOnLongClickListenerWithPoint
import com.vitaliimalone.shorttermplanner.presentation.utils.extensions.showStrikeThrough
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.tasks_list_item.*

class TasksAdapter(
    private val onTaskClick: ((Task) -> Unit),
    private val onTaskLongClick: ((Task, Point) -> Unit),
    private val onTaskDoneClick: ((Task, Boolean) -> Unit)
) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {
    var tasks = listOf<Task>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tasks_list_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) = holder.bind(tasks[position])

    override fun getItemCount() = tasks.size

    inner class TaskViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(task: Task) {
            titleTextView.text = task.title
            doneCheckBox.isChecked = task.isDone
            titleTextView.showStrikeThrough(task.isDone)
            dueDateTextView.text = DateTimeUtils.getTaskDueDateText(task.dueTo)
            tagsTextView.isVisible = task.tags.isNotEmpty()
            tagsTextView.text = task.tags.joinToString()
            doneCheckBox.setOnCheckedChangeListener { _, isChecked ->
                onTaskDoneClick(task, isChecked)
            }
            containerView.setOnClickListener {
                onTaskClick(task)
            }
            containerView.setOnLongClickListenerWithPoint {
                onTaskLongClick(task, it)
            }
        }
    }
}
