package com.vitaliimalone.shorttermplanner.presentation.screens.taskdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliimalone.shorttermplanner.domain.models.Task
import com.vitaliimalone.shorttermplanner.domain.usecases.UpdateTaskUseCase
import com.vitaliimalone.shorttermplanner.presentation.utils.SingleLiveEvent
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
