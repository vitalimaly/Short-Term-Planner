package com.vitaliimalone.simpletodo.data.repository.local

import com.vitaliimalone.simpletodo.data.repository.local.dao.TaskDao
import com.vitaliimalone.simpletodo.data.repository.local.models.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskLocalDataSource(
    private val taskDao: TaskDao
) {
    fun getUnarchivedTasksForPeriod(startDate: String, endDate: String): Flow<List<TaskEntity>> {
        return taskDao.getUnarchivedTasksForPeriod(startDate, endDate)
    }

    suspend fun addTasks(taskEntities: List<TaskEntity>) {
        taskDao.addTasks(taskEntities)
    }

    suspend fun updateTasks(taskEntities: List<TaskEntity>) {
        taskDao.updateTasks(taskEntities)
    }

    suspend fun deleteTasks(taskEntities: List<TaskEntity>) {
        taskDao.deleteTasks(taskEntities)
    }

    fun getUnarchivedTasksCountForPeriod(startDate: String, endDate: String): Flow<Int> {
        return taskDao.getUnarchivedTasksCountForPeriod(startDate, endDate)
    }

    fun getArchivedTasksCount(): Flow<Int> {
        return taskDao.getArchivedTasksCount()
    }

    fun getArchivedTasks(): Flow<List<TaskEntity>> {
        return taskDao.getArchivedTasks()
    }

    suspend fun deleteArchivedTasks() {
        taskDao.deleteArchivedTasks()
    }
}