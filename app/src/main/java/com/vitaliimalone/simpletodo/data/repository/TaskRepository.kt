package com.vitaliimalone.simpletodo.data.repository

import com.vitaliimalone.simpletodo.data.repository.local.TaskLocalDataSource
import com.vitaliimalone.simpletodo.data.repository.local.models.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepository(
        private val taskLocalDataSource: TaskLocalDataSource
) {
    suspend fun getAllTasks(): Flow<List<TaskEntity>> {
        return taskLocalDataSource.getAllTasks()
    }

    suspend fun getTasks(isArchived: Boolean): Flow<List<TaskEntity>> {
        return taskLocalDataSource.getTasks(isArchived)
    }

    suspend fun getUnarchivedTasksForPeriod(startDate: String, endDate: String): Flow<List<TaskEntity>> {
        return taskLocalDataSource.getUnarchivedTasksForPeriod(startDate, endDate)
    }

    suspend fun addTask(task: TaskEntity) {
        taskLocalDataSource.addTask(task)
    }

    suspend fun updateTask(task: TaskEntity) {
        taskLocalDataSource.updateTask(task)
    }
}