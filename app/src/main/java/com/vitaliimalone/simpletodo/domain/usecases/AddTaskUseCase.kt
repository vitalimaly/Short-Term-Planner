package com.vitaliimalone.simpletodo.domain.usecases

import com.vitaliimalone.simpletodo.data.repository.TaskRepository
import com.vitaliimalone.simpletodo.domain.mappers.TaskEntityMapper
import com.vitaliimalone.simpletodo.domain.models.Task

class AddTaskUseCase(
        private val taskRepository: TaskRepository
) {
    suspend fun addTask(task: Task) {
        taskRepository.addTask(TaskEntityMapper.map(task))
    }
}