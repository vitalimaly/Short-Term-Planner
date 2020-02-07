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

    suspend fun addTask(task: TaskEntity) {
        taskLocalDataSource.addTask(task)
    }

    suspend fun updateTask(task: TaskEntity) {
        taskLocalDataSource.updateTask(task)
    }

    suspend fun deleteTask(task: TaskEntity) {
        taskLocalDataSource.deleteTask(task)
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

    suspend fun archiveUnarchivedOverdueTasksForPeriod(startDate: String, endDate: String) {
        taskLocalDataSource.archiveUnarchivedOverdueTasksForPeriod(startDate, endDate)
    }
}