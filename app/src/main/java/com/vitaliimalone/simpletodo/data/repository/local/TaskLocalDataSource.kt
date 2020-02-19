package com.vitaliimalone.simpletodo.data.repository.local

import com.vitaliimalone.simpletodo.data.repository.local.dao.TaskDao
import com.vitaliimalone.simpletodo.data.repository.local.models.TaskEntity

class TaskLocalDataSource(
    private val taskDao: TaskDao
) {
    fun getUnarchivedTasksForPeriod(startDate: String, endDate: String) =
        taskDao.getUnarchivedTasksForPeriod(startDate, endDate)

    suspend fun addTasks(taskEntities: List<TaskEntity>) = taskDao.addTasks(taskEntities)

    suspend fun updateTasks(taskEntities: List<TaskEntity>) = taskDao.updateTasks(taskEntities)

    suspend fun deleteTasks(taskEntities: List<TaskEntity>) = taskDao.deleteTasks(taskEntities)

    fun getUnarchivedTasksCountForPeriod(startDate: String, endDate: String) =
        taskDao.getUnarchivedTasksCountForPeriod(startDate, endDate)

    fun getArchivedTasksCount() = taskDao.getArchivedTasksCount()

    fun getArchivedTasks() = taskDao.getArchivedTasks()

    suspend fun deleteArchivedTasks() = taskDao.deleteArchivedTasks()
}