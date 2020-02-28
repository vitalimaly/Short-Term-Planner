package com.vitaliimalone.shorttermplanner.data.repository

import com.vitaliimalone.shorttermplanner.data.repository.local.TaskLocalDataSource
import com.vitaliimalone.shorttermplanner.data.repository.local.models.TaskEntity

class TaskRepository(
    private val taskLocalDataSource: TaskLocalDataSource
) {
    fun getUnarchivedTasksForPeriod(startDate: String, endDate: String) =
        taskLocalDataSource.getUnarchivedTasksForPeriod(startDate, endDate)

    suspend fun addTasks(taskEntities: List<TaskEntity>) = taskLocalDataSource.addTasks(taskEntities)

    suspend fun updateTasks(taskEntities: List<TaskEntity>) = taskLocalDataSource.updateTasks(taskEntities)

    suspend fun deleteTasks(taskEntities: List<TaskEntity>) = taskLocalDataSource.deleteTasks(taskEntities)

    fun getUnarchivedTasksCountForPeriod(startDate: String, endDate: String) =
        taskLocalDataSource.getUnarchivedTasksCountForPeriod(startDate, endDate)

    fun getArchivedTasksCount() = taskLocalDataSource.getArchivedTasksCount()

    fun getArchivedTasks() = taskLocalDataSource.getArchivedTasks()

    suspend fun deleteArchivedTasks() = taskLocalDataSource.deleteArchivedTasks()
}