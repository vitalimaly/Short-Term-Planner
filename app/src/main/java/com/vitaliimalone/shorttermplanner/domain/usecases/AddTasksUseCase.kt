package com.vitaliimalone.shorttermplanner.domain.usecases

import com.vitaliimalone.shorttermplanner.data.repository.TaskRepository
import com.vitaliimalone.shorttermplanner.domain.mappers.TaskEntityMapper
import com.vitaliimalone.shorttermplanner.domain.models.Task

class AddTasksUseCase(
    private val taskRepository: TaskRepository
) {
    suspend fun addTask(tasks: List<Task>) {
        val taskEntities = tasks.map(TaskEntityMapper::map)
        taskRepository.addTasks(taskEntities)
    }
}