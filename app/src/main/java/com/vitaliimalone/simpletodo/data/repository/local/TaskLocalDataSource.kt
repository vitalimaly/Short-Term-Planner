package com.vitaliimalone.simpletodo.data.repository.local

import com.vitaliimalone.simpletodo.data.repository.local.dao.TaskDao
import com.vitaliimalone.simpletodo.data.repository.local.models.TaskEntity

class TaskLocalDataSource(
        private val taskDao: TaskDao
) {
    suspend fun getAllTasks(): List<TaskEntity> {
        return taskDao.getAllTasks()
    }

    suspend fun getAllTasks(isArchived: Boolean): List<TaskEntity> {
        return taskDao.getAllTasks(isArchived)
    }

    suspend fun addTask(taskEntity: TaskEntity) {
        taskDao.addTask(taskEntity)
    }

    suspend fun updateTask(taskEntity: TaskEntity) {
        taskDao.updateTask(taskEntity)
    }
}