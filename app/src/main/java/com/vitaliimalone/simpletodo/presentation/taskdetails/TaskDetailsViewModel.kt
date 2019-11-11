package com.vitaliimalone.simpletodo.presentation.taskdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.domain.usecases.DeleteTaskUseCase
import kotlinx.coroutines.launch

class TaskDetailsViewModel(
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            deleteTaskUseCase.deleteTask(task)
        }
    }
}
