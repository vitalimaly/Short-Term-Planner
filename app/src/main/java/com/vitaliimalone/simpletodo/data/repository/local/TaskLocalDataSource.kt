package com.vitaliimalone.simpletodo.data.repository.local

import com.vitaliimalone.simpletodo.data.repository.local.dao.TaskDao
import com.vitaliimalone.simpletodo.data.repository.local.models.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskLocalDataSource(
        private val taskDao: TaskDao
) {
    suspend fun getAllTasks(): Flow<List<TaskEntity>> {
        return taskDao.getAllTasks()
    }

    suspend fun getTasks(isArchived: Boolean): Flow<List<TaskEntity>> {
        return taskDao.getTasks(isArchived)
    }

    suspend fun getUnarchivedTasksForPeriod(startDate: String, endDate: String): Flow<List<TaskEntity>> {
        return taskDao.getUnarchivedTasksForPeriod(startDate, endDate)
    }

    suspend fun addTask(taskEntity: TaskEntity) {
        taskDao.addTask(taskEntity)
    }

    suspend fun updateTask(taskEntity: TaskEntity) {
        taskDao.updateTask(taskEntity)
    }

    suspend fun deleteTask(taskEntity: TaskEntity) {
        taskDao.deleteTask(taskEntity)
    }
}