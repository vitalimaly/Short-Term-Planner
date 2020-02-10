package com.vitaliimalone.simpletodo.data.repository

import com.vitaliimalone.simpletodo.data.repository.local.TaskLocalDataSource
import com.vitaliimalone.simpletodo.data.repository.local.models.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepository(
    private val taskLocalDataSource: TaskLocalDataSource
) {
    fun getUnarchivedTasksForPeriod(startDate: String, endDate: String): Flow<List<TaskEntity>> {
        return taskLocalDataSource.getUnarchivedTasksForPeriod(startDate, endDate)
    }

    suspend fun addTasks(taskEntities: List<TaskEntity>) {
        taskLocalDataSource.addTasks(taskEntities)
    }

    suspend fun updateTasks(taskEntities: List<TaskEntity>) {
        taskLocalDataSource.updateTasks(taskEntities)
    }

    suspend fun deleteTasks(taskEntities: List<TaskEntity>) {
        taskLocalDataSource.deleteTasks(taskEntities)
    }

    fun getUnarchivedTasksCountForPeriod(startDate: String, endDate: String): Flow<Int> {
        return taskLocalDataSource.getUnarchivedTasksCountForPeriod(startDate, endDate)
    }

    fun getArchivedTasksCount(): Flow<Int> {
        return taskLocalDataSource.getArchivedTasksCount()
    }

    fun getArchivedTasks(): Flow<List<TaskEntity>> {
        return taskLocalDataSource.getArchivedTasks()
    }

    suspend fun deleteArchivedTasks() {
        taskLocalDataSource.deleteArchivedTasks()
    }
}