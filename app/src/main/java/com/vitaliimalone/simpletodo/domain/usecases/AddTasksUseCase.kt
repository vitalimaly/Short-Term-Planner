package com.vitaliimalone.simpletodo.domain.usecases

import com.vitaliimalone.simpletodo.data.repository.TaskRepository
import com.vitaliimalone.simpletodo.domain.mappers.TaskEntityMapper
import com.vitaliimalone.simpletodo.domain.models.Task

class AddTasksUseCase(
    private val taskRepository: TaskRepository
) {
    suspend fun addTask(tasks: List<Task>) {
        val taskEntities = tasks.map(TaskEntityMapper::map)
        taskRepository.addTasks(taskEntities)
    }
}