package com.vitaliimalone.simpletodo.presentation.home.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.domain.models.Task
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.tasks_pager_item.*

class TasksPagerAdapter : RecyclerView.Adapter<TasksPagerAdapter.TasksPagerViewHolder>() {
    var taskPages = TaskPages()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tasks_pager_item, parent, false)
        return TasksPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: TasksPagerViewHolder, position: Int) {
        holder.bind(taskPages.getByPosition(position))
    }

    override fun getItemCount(): Int {
        return taskPages.size()
    }

    class TasksPagerViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {
        fun bind(tasks: List<Task>) {
            var tasksAdapter = TasksAdapter()
            tasksPagerRecyclerView.adapter = tasksAdapter
            tasksAdapter.tasks = tasks
        }
    }
}