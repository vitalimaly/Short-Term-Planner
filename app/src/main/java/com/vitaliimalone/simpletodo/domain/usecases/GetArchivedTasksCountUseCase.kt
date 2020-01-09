package com.vitaliimalone.simpletodo.domain.usecases

import com.vitaliimalone.simpletodo.data.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetArchivedTasksCountUseCase(
    private val taskRepository: TaskRepository
) {
    fun getArchivedTasksCount(): Flow<Int> {
        return taskRepository.getArchivedTasksCount()
    }
}