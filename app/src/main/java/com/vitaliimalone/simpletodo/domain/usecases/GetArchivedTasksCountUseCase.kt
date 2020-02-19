package com.vitaliimalone.simpletodo.domain.usecases

import com.vitaliimalone.simpletodo.data.repository.TaskRepository

class GetArchivedTasksCountUseCase(
    private val taskRepository: TaskRepository
) {
    fun getArchivedTasksCount() = taskRepository.getArchivedTasksCount()
}