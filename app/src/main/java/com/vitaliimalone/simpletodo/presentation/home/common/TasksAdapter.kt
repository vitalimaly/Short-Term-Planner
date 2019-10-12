package com.vitaliimalone.simpletodo.presentation.home.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.domain.models.Task
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.home_task_list_item.*

class TasksAdapter(
        private val onNTaskClick: (task: Task) -> Unit,
        private val onDoneClick: (task: Task) -> Unit
) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {
    var tasks = listOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.home_task_list_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    inner class TaskViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView),
            LayoutContainer {
        fun bind(task: Task) {
            titleTextView.text = task.title
            checkImageView.setImageResource(if (task.isDone) R.drawable.ic_check_circle else 0)
            itemView.setOnClickListener { onNTaskClick.invoke(task) }
            checkContainer.setOnClickListener { onDoneClick.invoke(task) }
        }
    }
}
