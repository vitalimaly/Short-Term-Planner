package com.vitaliimalone.simpletodo.presentation.hometab.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.domain.models.Task
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.tasks_list_item.*
import org.threeten.bp.format.DateTimeFormatter

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
            dueDateTextView.text = task.dueTo.format(DateTimeFormatter.ISO_LOCAL_DATE)
            tagsTextView.text = task.tags.joinToString()
        }
    }
}
