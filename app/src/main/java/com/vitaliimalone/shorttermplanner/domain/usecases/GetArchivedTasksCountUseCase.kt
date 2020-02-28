package com.vitaliimalone.shorttermplanner.domain.usecases

import com.vitaliimalone.shorttermplanner.data.repository.TaskRepository

class GetArchivedTasksCountUseCase(
    private val taskRepository: TaskRepository
) {
    fun getArchivedTasksCount() = taskRepository.getArchivedTasksCount()
}