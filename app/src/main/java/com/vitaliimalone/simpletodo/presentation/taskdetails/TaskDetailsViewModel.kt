package com.vitaliimalone.simpletodo.presentation.taskdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.domain.usecases.UpdateTaskUseCase
import com.vitaliimalone.simpletodo.presentation.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class TaskDetailsViewModel(
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel() {
    val taskDeletedEvent = SingleLiveEvent<Unit>()

    fun updateTask(task: Task) {
        viewModelScope.launch {
            updateTaskUseCase.updateTasks(listOf(task))
        }
    }
}
