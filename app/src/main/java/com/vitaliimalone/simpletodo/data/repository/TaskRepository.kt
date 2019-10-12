package com.vitaliimalone.simpletodo.data.repository

import com.vitaliimalone.simpletodo.data.repository.local.TaskLocalDataSource
import com.vitaliimalone.simpletodo.data.repository.local.models.TaskEntity

class TaskRepository(
        private val taskLocalDataSource: TaskLocalDataSource
) {
    suspend fun getAllTasks(): List<TaskEntity> {
        return taskLocalDataSource.getAllTasks()
    }

    suspend fun getAllTasks(isArchived: Boolean): List<TaskEntity> {
        return taskLocalDataSource.getAllTasks(isArchived)
    }

    suspend fun addTask(task: TaskEntity) {
        taskLocalDataSource.addTask(task)
    }

    suspend fun updateTask(task: TaskEntity) {
        taskLocalDataSource.updateTask(task)
    }
}